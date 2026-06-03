package com.minicity.controlador;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minicity.modelo.TipoEntrada;
import com.minicity.repositorio.TipoEntradaRepositorio;

@RestController
@RequestMapping("/api/tipos-entrada")
@CrossOrigin(origins = "*")
public class TipoEntradaControlador {
    private final TipoEntradaRepositorio tipoEntradaRepositorio;

    public TipoEntradaControlador(TipoEntradaRepositorio tipoEntradaRepositorio) {
        this.tipoEntradaRepositorio = tipoEntradaRepositorio;
    }

    @GetMapping
    public List<TipoEntrada> listar() {
        return tipoEntradaRepositorio.findAllByOrderByIdAsc();
    }
}
