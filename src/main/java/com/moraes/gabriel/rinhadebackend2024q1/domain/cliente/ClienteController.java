package com.moraes.gabriel.rinhadebackend2024q1.domain.cliente;

import com.moraes.gabriel.rinhadebackend2024q1.domain.cliente.payload.ClienteExtrato;
import com.moraes.gabriel.rinhadebackend2024q1.domain.cliente.payload.ClienteTransacaoResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
public class ClienteController {

    private ClienteService clienteService;

    @GetMapping("/{id}/transacoes")
    public ResponseEntity<ClienteExtrato> getExtrato(@PathVariable Integer id) {
        return new ResponseEntity<>(clienteService.getExtrato(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ClienteTransacaoResponse>> getAllClientes() {
        return new ResponseEntity<>(clienteService.getAllClientes(), HttpStatus.OK);
    }


}
