package com.minicity.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minicity.modelo.Reserva;

public interface ReservaRepositorio extends JpaRepository<Reserva, Long> {
    List<Reserva> findAllByOrderByFechaReservaDesc();
    List<Reserva> findByNinoId(Long ninoId);
}
