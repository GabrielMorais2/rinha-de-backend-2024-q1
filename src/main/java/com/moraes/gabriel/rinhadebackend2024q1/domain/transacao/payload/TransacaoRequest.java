package com.moraes.gabriel.rinhadebackend2024q1.domain.transacao.payload;

import com.moraes.gabriel.rinhadebackend2024q1.domain.transacao.Tipo;

public record TransacaoRequest (long valor, Tipo tipo, String descricao){
}
