package com.moraes.gabriel.rinhadebackend2024q1.domain.cliente;

import com.moraes.gabriel.rinhadebackend2024q1.domain.cliente.payload.ClienteExtrato;
import com.moraes.gabriel.rinhadebackend2024q1.domain.cliente.payload.ClienteTransacaoResponse;
import com.moraes.gabriel.rinhadebackend2024q1.domain.cliente.payload.Saldo;
import com.moraes.gabriel.rinhadebackend2024q1.domain.transacao.TransacaoRepository;
import com.moraes.gabriel.rinhadebackend2024q1.exception.ClienteNaoEncontradoException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final TransacaoRepository transacaoRepository;
    private final ModelMapper mapper;

    public List<ClienteTransacaoResponse> getAllClientes() {
        List<Cliente> cars = clienteRepository.findAll();
        return cars.stream()
                .map(car -> mapper.map(car, ClienteTransacaoResponse.class))
                .collect(Collectors.toList());

    }

    public ClienteExtrato getExtrato(Integer clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente not found with id: " + clienteId));

        var transacoes = transacaoRepository.findFirst10ByClienteIdOrderByRealizadaEmDesc(clienteId);

        return new ClienteExtrato(new Saldo(cliente.getSaldo(), LocalDateTime.now(), cliente.getLimite()), transacoes);
    }
}
