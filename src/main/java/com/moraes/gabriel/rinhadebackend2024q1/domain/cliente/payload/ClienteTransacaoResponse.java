package com.moraes.gabriel.rinhadebackend2024q1.domain.cliente.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteTransacaoResponse {

    private long limite;
    private long saldo;
}
