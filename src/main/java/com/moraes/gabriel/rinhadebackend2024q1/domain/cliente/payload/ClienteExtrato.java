package com.moraes.gabriel.rinhadebackend2024q1.domain.cliente.payload;

import com.moraes.gabriel.rinhadebackend2024q1.domain.transacao.Transacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteExtrato {
    Saldo saldo;
    List<Transacao> ultimas_transacoes;
}
