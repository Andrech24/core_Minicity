package com.minicity.controlador;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minicity.servicio.PanelServicio;

@RestController
@RequestMapping("/api/panel")
@CrossOrigin(origins = "*")
public class PanelControlador {
    private final PanelServicio panelServicio;

    public PanelControlador(PanelServicio panelServicio) {
        this.panelServicio = panelServicio;
    }

    @GetMapping("/resumen")
    public Map<String, Object> resumen() {
        return panelServicio.resumen();
    }
}
