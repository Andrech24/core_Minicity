package com.minicity.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minicity.modelo.Premio;

public interface PremioRepositorio extends JpaRepository<Premio, Long> {
    List<Premio> findByNinoIdOrderByFechaPremioDesc(Long ninoId);
    void deleteByNinoId(Long ninoId);
}
