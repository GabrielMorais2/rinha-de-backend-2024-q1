package com.moraes.gabriel.rinhadebackend2024q1.domain.cliente.payload;

import com.moraes.gabriel.rinhadebackend2024q1.domain.transacao.Transacao;

import java.util.List;

public record ClienteExtrato (Saldo saldo, List<Transacao> ultimas_transacoes){

}
