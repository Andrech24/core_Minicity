package com.minicity.servicio;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.minicity.modelo.Nino;
import com.minicity.modelo.Reserva;
import com.minicity.modelo.TipoEntrada;
import com.minicity.repositorio.CompraEntradaRepositorio;
import com.minicity.repositorio.NinoRepositorio;
import com.minicity.repositorio.PremioRepositorio;
import com.minicity.repositorio.ReservaRepositorio;
import com.minicity.repositorio.TipoEntradaRepositorio;
import com.minicity.repositorio.VisitaRepositorio;

import jakarta.transaction.Transactional;

@Service
public class NinoServicio {
    private final NinoRepositorio ninoRepositorio;
    private final TipoEntradaRepositorio tipoEntradaRepositorio;
    private final VisitaRepositorio visitaRepositorio;
    private final CompraEntradaRepositorio compraEntradaRepositorio;
    private final PremioRepositorio premioRepositorio;
    private final ReservaRepositorio reservaRepositorio;

    public NinoServicio(
        NinoRepositorio ninoRepositorio,
        TipoEntradaRepositorio tipoEntradaRepositorio,
        VisitaRepositorio visitaRepositorio,
        CompraEntradaRepositorio compraEntradaRepositorio,
        PremioRepositorio premioRepositorio,
        ReservaRepositorio reservaRepositorio
    ) {
        this.ninoRepositorio = ninoRepositorio;
        this.tipoEntradaRepositorio = tipoEntradaRepositorio;
        this.visitaRepositorio = visitaRepositorio;
        this.compraEntradaRepositorio = compraEntradaRepositorio;
        this.premioRepositorio = premioRepositorio;
        this.reservaRepositorio = reservaRepositorio;
    }

    public List<Nino> listar() {
        return ninoRepositorio.findAllByOrderByIdAsc();
    }

    public Nino buscar(Long id) {
        return ninoRepositorio.findById(id).orElse(null);
    }

    public Nino guardar(Nino nino) {
        validar(nino);
        completar(nino);
        return ninoRepositorio.save(nino);
    }

    public Nino actualizar(Long id, Nino datos) {
        validar(datos);
        Nino nino = buscar(id);
        if (nino == null) {
            return null;
        }

        nino.setNombre(datos.getNombre());
        nino.setEdad(datos.getEdad());
        nino.setRepresentante(datos.getRepresentante());
        nino.setCedulaRepresentante(datos.getCedulaRepresentante());
        nino.setTelefono(datos.getTelefono());
        nino.setFechaIngreso(datos.getFechaIngreso());
        nino.setTipoEntrada(datos.getTipoEntrada());
        nino.setDineroVirtual(datos.getDineroVirtual());
        nino.setPuntosAcumulados(datos.getPuntosAcumulados());
        completar(nino);
        return ninoRepositorio.save(nino);
    }

    @Transactional
    public boolean eliminar(Long id) {
        if (!ninoRepositorio.existsById(id)) {
            return false;
        }

        List<Reserva> reservas = reservaRepositorio.findByNinoId(id);
        for (Reserva reserva : reservas) {
            reserva.setNino(null);
        }
        reservaRepositorio.saveAll(reservas);

        premioRepositorio.deleteByNinoId(id);
        compraEntradaRepositorio.deleteByNinoId(id);
        visitaRepositorio.deleteByNinoId(id);
        ninoRepositorio.deleteById(id);
        return true;
    }

    public Map<String, Object> perfil(Long id) {
        Nino nino = buscar(id);
        Map<String, Object> datos = new LinkedHashMap<>();
        datos.put("nino", nino);
        datos.put("visitas", visitaRepositorio.findByNinoIdOrderByFechaVisitaDesc(id));
        datos.put("compras", compraEntradaRepositorio.findByNinoIdOrderByFechaCompraDesc(id));
        datos.put("premios", premioRepositorio.findByNinoIdOrderByFechaPremioDesc(id));
        return datos;
    }

    private void validar(Nino nino) {
        if (nino.getNombre() == null || nino.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (nino.getEdad() <= 0) {
            throw new IllegalArgumentException("La edad debe ser mayor a cero");
        }
        if (nino.getRepresentante() == null || nino.getRepresentante().isBlank()) {
            throw new IllegalArgumentException("El representante es obligatorio");
        }
        if (nino.getCedulaRepresentante() == null || !nino.getCedulaRepresentante().matches("\\d{10}")) {
            throw new IllegalArgumentException("La cedula debe tener 10 numeros");
        }
    }

    private void completar(Nino nino) {
        if (nino.getFechaIngreso() == null) {
            nino.setFechaIngreso(LocalDate.now());
        }

        Long tipoId = nino.getTipoEntradaId() != null ? nino.getTipoEntradaId() : 1L;
        TipoEntrada tipo = tipoEntradaRepositorio.findById(tipoId).orElse(null);
        if (tipo != null) {
            nino.setTipoEntrada(tipo);
            if (nino.getDineroVirtual() <= 0) {
                nino.setDineroVirtual(tipo.getDineroBase());
            }
        }

        if (nino.getPuntosAcumulados() <= 0) {
            int base = tipo != null ? tipo.getDineroBase() : nino.getDineroVirtual();
            nino.setPuntosAcumulados(base + (nino.getEdad() * 10));
        }
    }
}
