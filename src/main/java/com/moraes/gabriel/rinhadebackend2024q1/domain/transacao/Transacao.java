package com.moraes.gabriel.rinhadebackend2024q1.domain.transacao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;

    private long valor;
    private Tipo tipo;
    private String descricao;
    @JsonIgnore
    private Integer clienteId;

    public Transacao() {
    }

    public Transacao(long valor, Tipo tipo, String descricao) {
        this.valor = valor;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public LocalDateTime getRealizadaEm() {
        return realizadaEm;
    }

    private LocalDateTime realizadaEm;

    public long getValor() {
        return valor;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public void setRealizadaEm(LocalDateTime realizadaEm) {
        this.realizadaEm = realizadaEm;
    }
    @Override
    public String toString() {
        return "Transacao{" +
                "valor=" + valor +
                ", tipo=" + tipo +
                ", descricao='" + descricao + '\'' +
                ", realizadaEm=" + realizadaEm +
                '}';
    }

}
