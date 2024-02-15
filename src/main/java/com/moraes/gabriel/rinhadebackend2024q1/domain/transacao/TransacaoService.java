package com.moraes.gabriel.rinhadebackend2024q1.domain.transacao;

import com.moraes.gabriel.rinhadebackend2024q1.domain.cliente.Cliente;
import com.moraes.gabriel.rinhadebackend2024q1.domain.cliente.ClienteRepository;
import com.moraes.gabriel.rinhadebackend2024q1.domain.cliente.payload.ClienteTransacaoResponse;
import com.moraes.gabriel.rinhadebackend2024q1.domain.transacao.payload.TransacaoRequest;
import com.moraes.gabriel.rinhadebackend2024q1.exception.ClienteNaoEncontradoException;
import com.moraes.gabriel.rinhadebackend2024q1.exception.SaldoInsuficienteException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final ClienteRepository clienteRepository;
    private final ModelMapper mapper;

    public ClienteTransacaoResponse efetuaTransacao(Integer clienteId, TransacaoRequest transacaoRequest) {
        Transacao transacao = mapper.map(transacaoRequest, Transacao.class);

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente not found with id: " + clienteId));

        transacao.setClienteId(cliente.getId());
        transacao.setRealizadaEm(LocalDateTime.now());

        if(transacao.getTipo() == Tipo.c){
            return efetuaDebito(cliente, transacao);
        } else {
            return efetuaCredito(cliente, transacao);
        }
    }


    synchronized public ClienteTransacaoResponse efetuaDebito(Cliente cliente, Transacao transacao) {

        var saldoAtual = cliente.getSaldo();
        var limiteDoCliente = cliente.getLimite();
        var valorTransacao = transacao.getValor();

        if ( (saldoAtual + limiteDoCliente) >= valorTransacao) {
            saldoAtual -= valorTransacao;
        } else {
            throw new SaldoInsuficienteException("Saldo Insuficiente para realizar essa operação");
        }

        cliente.setSaldo(saldoAtual);

        transacaoRepository.save(transacao);
        clienteRepository.save(cliente);

        return new ClienteTransacaoResponse(cliente.getLimite(), saldoAtual);
    }

    synchronized public ClienteTransacaoResponse efetuaCredito(Cliente cliente, Transacao transacao) {

        var saldoAtual = cliente.getSaldo();
        var limiteDoCliente = cliente.getLimite();
        var valorTransacao = transacao.getValor();

        saldoAtual+=valorTransacao;

        cliente.setSaldo(saldoAtual);

        transacaoRepository.save(transacao);
        clienteRepository.save(cliente);

        return new ClienteTransacaoResponse(cliente.getLimite(), saldoAtual);
    }
}
