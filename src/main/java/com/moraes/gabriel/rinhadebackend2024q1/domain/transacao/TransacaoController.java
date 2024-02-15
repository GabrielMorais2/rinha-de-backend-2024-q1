package com.moraes.gabriel.rinhadebackend2024q1.domain.transacao;

import com.moraes.gabriel.rinhadebackend2024q1.domain.transacao.payload.TransacaoRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
public class TransacaoController {

    private TransacaoService transacaoService;

    @PostMapping("/{id}/transacoes")
    public ResponseEntity<Object> transacoes(@PathVariable Integer id, @Valid @RequestBody TransacaoRequest transacaoRequest) {
        return new ResponseEntity<>(transacaoService.efetuaTransacao(id, transacaoRequest), HttpStatus.CREATED);
    }

}
