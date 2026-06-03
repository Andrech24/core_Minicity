package com.minicity.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minicity.modelo.Visita;

public interface VisitaRepositorio extends JpaRepository<Visita, Long> {
    List<Visita> findByNinoIdOrderByFechaVisitaDesc(Long ninoId);
    void deleteByNinoId(Long ninoId);
}
