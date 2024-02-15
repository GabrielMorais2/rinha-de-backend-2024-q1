package com.moraes.gabriel.rinhadebackend2024q1.domain.transacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {
    List<Transacao> findFirst10ByClienteIdOrderByRealizadaEmDesc(Integer clienteId);
}
