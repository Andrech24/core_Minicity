package com.minicity.servicio;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.minicity.modelo.Nino;
import com.minicity.modelo.Reserva;
import com.minicity.repositorio.NinoRepositorio;
import com.minicity.repositorio.ReservaRepositorio;

@Service
public class ReservaServicio {
    private final ReservaRepositorio reservaRepositorio;
    private final NinoRepositorio ninoRepositorio;

    public ReservaServicio(ReservaRepositorio reservaRepositorio, NinoRepositorio ninoRepositorio) {
        this.reservaRepositorio = reservaRepositorio;
        this.ninoRepositorio = ninoRepositorio;
    }

    public List<Reserva> listar() {
        return reservaRepositorio.findAllByOrderByFechaReservaDesc();
    }

    public Reserva guardar(Reserva reserva) {
        if (reserva.getFechaReserva() == null) {
            reserva.setFechaReserva(LocalDate.now());
        }
        if (reserva.getEstado() == null || reserva.getEstado().isBlank()) {
            reserva.setEstado("pendiente");
        }
        if (reserva.getNinoId() != null) {
            Nino nino = ninoRepositorio.findById(reserva.getNinoId()).orElse(null);
            reserva.setNino(nino);
        }
        return reservaRepositorio.save(reserva);
    }
}
