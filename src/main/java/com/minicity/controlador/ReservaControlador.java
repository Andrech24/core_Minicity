package com.minicity.controlador;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minicity.modelo.Reserva;
import com.minicity.servicio.ReservaServicio;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "*")
public class ReservaControlador {
    private final ReservaServicio reservaServicio;

    public ReservaControlador(ReservaServicio reservaServicio) {
        this.reservaServicio = reservaServicio;
    }

    @GetMapping
    public List<Reserva> listar() {
        return reservaServicio.listar();
    }

    @PostMapping
    public Reserva guardar(@RequestBody Reserva reserva) {
        return reservaServicio.guardar(reserva);
    }
}
