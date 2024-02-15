package com.moraes.gabriel.rinhadebackend2024q1.domain.transacao.payload;

import com.moraes.gabriel.rinhadebackend2024q1.domain.transacao.Tipo;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoRequest {

    @NotNull
    private long valor;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @NotBlank
    private String descricao;

}
