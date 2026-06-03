package com.minicity.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minicity.modelo.TipoEntrada;

public interface TipoEntradaRepositorio extends JpaRepository<TipoEntrada, Long> {
    List<TipoEntrada> findAllByOrderByIdAsc();
}
