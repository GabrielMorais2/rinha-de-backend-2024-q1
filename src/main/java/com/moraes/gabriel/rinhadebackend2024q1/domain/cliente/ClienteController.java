package com.moraes.gabriel.rinhadebackend2024q1.domain.cliente;

import com.moraes.gabriel.rinhadebackend2024q1.domain.cliente.payload.ClienteExtrato;
import com.moraes.gabriel.rinhadebackend2024q1.domain.cliente.payload.Saldo;
import com.moraes.gabriel.rinhadebackend2024q1.domain.transacao.TransacaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;
    private final TransacaoRepository transacaoRepository;

    public ClienteController( ClienteRepository clienteRepository, TransacaoRepository transacaoRepository) {
        this.clienteRepository = clienteRepository;
        this.transacaoRepository = transacaoRepository;
    }

    @GetMapping("/{id}/extrato")
    public ResponseEntity<ClienteExtrato> getExtrato(@PathVariable Integer id) {

        if(id<=0||id>=6){
            return ResponseEntity.notFound().build();
        }

        Optional<Cliente> cliente = clienteRepository.findById(id);

        var transacoes = transacaoRepository.findFirst10ByClienteIdOrderByRealizadaEmDesc(id);

        return new ResponseEntity<>(new ClienteExtrato(new Saldo(cliente.get().getSaldo(), LocalDateTime.now(), cliente.get().getLimite()), transacoes), HttpStatus.OK);
    }

}
