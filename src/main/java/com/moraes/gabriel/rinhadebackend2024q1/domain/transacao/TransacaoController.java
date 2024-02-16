package com.moraes.gabriel.rinhadebackend2024q1.domain.transacao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moraes.gabriel.rinhadebackend2024q1.domain.cliente.payload.ClienteTransacaoResponse;
import com.moraes.gabriel.rinhadebackend2024q1.domain.transacao.payload.TransacaoRequest;
import com.moraes.gabriel.rinhadebackend2024q1.exception.SaldoInsuficienteException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class TransacaoController {

    private final TransacaoService transacaoService;
    private static final ObjectMapper mapper = new ObjectMapper();

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping("/{id}/transacoes")
    public ResponseEntity<Object> transacoes(@PathVariable Integer id, @RequestBody String body) {
        TransacaoRequest transacaoRequest;

        try {
            transacaoRequest = mapper.readValue(body, TransacaoRequest.class);
            String  valor = body.split(",")[0];
            if (!isValidTransacaoRequest(transacaoRequest) || valor.contains(".") || !(id > 0 && id <=6)) {
                return ResponseEntity.unprocessableEntity().build();
            }
        } catch (JsonProcessingException e) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Transacao transacao = new Transacao(
                transacaoRequest.valor(),
                transacaoRequest.tipo(),
                transacaoRequest.descricao()
        );

        ClienteTransacaoResponse response;
        try {
            if (transacao.getTipo() == Tipo.c) {
                response = transacaoService.efetuaCredito(id, transacao);
            } else {
                response = transacaoService.efetuaDebito(id, transacao);
            }
        } catch (SaldoInsuficienteException ex) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(response);
    }

    private boolean isValidTransacaoRequest(TransacaoRequest transacaoRequest) {
        return transacaoRequest != null
                && (transacaoRequest.tipo() == Tipo.c || transacaoRequest.tipo() == Tipo.d)
                && transacaoRequest.descricao() != null
                && transacaoRequest.descricao().length() <= 10
                && !transacaoRequest.descricao().isEmpty()
                && !(transacaoRequest.valor() < 0);
    }

}
