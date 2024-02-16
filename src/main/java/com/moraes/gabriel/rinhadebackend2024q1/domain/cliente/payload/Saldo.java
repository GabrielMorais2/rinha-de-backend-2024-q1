package com.moraes.gabriel.rinhadebackend2024q1.domain.cliente.payload;

import java.time.LocalDateTime;

public record Saldo (long total, LocalDateTime data_extrato, long limite) {
}
