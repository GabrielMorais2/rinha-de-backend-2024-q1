package com.moraes.gabriel.rinhadebackend2024q1.domain.cliente;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private long limite;
    private long saldo;

    public Cliente(Integer id, long limite, long saldo) {
        this.id = id;
        this.limite = limite;
        this.saldo = saldo;
    }

    public Cliente() {

    }

    public Integer getId() {
        return id;
    }

    public long getLimite() {
        return limite;
    }

    public long getSaldo() {
        return saldo;
    }

    public void setSaldo(long saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", limite=" + limite +
                ", saldo=" + saldo +
                '}';
    }
}
