package com.minicity.controlador;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AutenticacionControlador {
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> datos) {
        String correo = datos.get("email");
        String clave = datos.get("password");

        Map<String, Object> respuesta = new LinkedHashMap<>();
        boolean correcto = "admin@minicity.com".equalsIgnoreCase(correo) && "admin123".equals(clave);
        respuesta.put("success", correcto);
        respuesta.put("mensaje", correcto ? "Ingreso correcto" : "Correo o clave incorrectos");
        respuesta.put("token", correcto ? "minicity-core" : "");
        return respuesta;
    }
}
