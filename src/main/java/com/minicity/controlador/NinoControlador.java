package com.minicity.controlador;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minicity.modelo.Nino;
import com.minicity.servicio.NinoServicio;

@RestController
@RequestMapping("/api/ninos")
@CrossOrigin(origins = "*")
public class NinoControlador {
    private final NinoServicio ninoServicio;

    public NinoControlador(NinoServicio ninoServicio) {
        this.ninoServicio = ninoServicio;
    }

    @GetMapping
    public List<Nino> listar() {
        return ninoServicio.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nino> buscar(@PathVariable Long id) {
        Nino nino = ninoServicio.buscar(id);
        return nino != null ? ResponseEntity.ok(nino) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/perfil")
    public Map<String, Object> perfil(@PathVariable Long id) {
        return ninoServicio.perfil(id);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Nino nino) {
        try {
            return ResponseEntity.ok(ninoServicio.guardar(nino));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("mensaje", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Nino nino) {
        try {
            Nino guardado = ninoServicio.actualizar(id, nino);
            return guardado != null ? ResponseEntity.ok(guardado) : ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("mensaje", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public boolean eliminar(@PathVariable Long id) {
        return ninoServicio.eliminar(id);
    }
}
