package com.minicity.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minicity.modelo.Nino;

public interface NinoRepositorio extends JpaRepository<Nino, Long> {
    List<Nino> findAllByOrderByIdAsc();
}
