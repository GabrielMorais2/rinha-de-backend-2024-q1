package com.moraes.gabriel.rinhadebackend2024q1.domain.transacao;

import com.moraes.gabriel.rinhadebackend2024q1.domain.cliente.Cliente;
import com.moraes.gabriel.rinhadebackend2024q1.domain.cliente.ClienteRepository;
import com.moraes.gabriel.rinhadebackend2024q1.domain.cliente.payload.ClienteTransacaoResponse;
import com.moraes.gabriel.rinhadebackend2024q1.exception.ClienteNaoEncontradoException;
import com.moraes.gabriel.rinhadebackend2024q1.exception.SaldoInsuficienteException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final ClienteRepository clienteRepository;

    public TransacaoService(TransacaoRepository transacaoRepository, ClienteRepository clienteRepository) {
        this.transacaoRepository = transacaoRepository;
        this.clienteRepository = clienteRepository;
    }

    synchronized public ClienteTransacaoResponse efetuaDebito(Integer clienteId, Transacao transacao) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente not found with id: " + clienteId));

        var saldoAtual = cliente.getSaldo();
        var limiteDoCliente = cliente.getLimite();
        var valorTransacao = transacao.getValor();

        if ( (saldoAtual + limiteDoCliente) >= valorTransacao) {
            saldoAtual -= valorTransacao;
        } else {
            throw new SaldoInsuficienteException("Saldo Insuficiente para realizar essa operação");
        }

        transacao.setClienteId(cliente.getId());
        transacao.setRealizadaEm(LocalDateTime.now());

        cliente.setSaldo(saldoAtual);

        transacaoRepository.save(transacao);
        clienteRepository.save(cliente);

        return new ClienteTransacaoResponse(cliente.getLimite(), saldoAtual);
    }

    synchronized public ClienteTransacaoResponse efetuaCredito(Integer clienteId, Transacao transacao) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente not found with id: " + clienteId));

        var saldoAtual = cliente.getSaldo();
        var limiteDoCliente = cliente.getLimite();
        var valorTransacao = transacao.getValor();

        saldoAtual+=valorTransacao;

        cliente.setSaldo(saldoAtual);
        transacao.setClienteId(cliente.getId());
        transacao.setRealizadaEm(LocalDateTime.now());

        transacaoRepository.save(transacao);
        clienteRepository.save(cliente);

        return new ClienteTransacaoResponse(limiteDoCliente, saldoAtual);
    }
}
