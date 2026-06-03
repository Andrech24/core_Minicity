package com.minicity.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minicity.modelo.CompraEntrada;

public interface CompraEntradaRepositorio extends JpaRepository<CompraEntrada, Long> {
    List<CompraEntrada> findByNinoIdOrderByFechaCompraDesc(Long ninoId);
    void deleteByNinoId(Long ninoId);
}
