package com.moraes.gabriel.rinhadebackend2024q1.domain.cliente.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Saldo {
    private long total;
    private LocalDateTime data_extrato;
    private long limite;
}
