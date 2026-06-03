package com.minicity.modelo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ninos")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Nino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private int edad;
    private String representante;

    @Column(name = "cedula_representante")
    private String cedulaRepresentante;

    private String telefono;

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    @ManyToOne
    @JoinColumn(name = "tipo_entrada_id")
    private TipoEntrada tipoEntrada;

    @Column(name = "dinero_virtual")
    private int dineroVirtual;

    @Column(name = "puntos_acumulados")
    private int puntosAcumulados;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getRepresentante() { return representante; }
    public void setRepresentante(String representante) { this.representante = representante; }

    public String getCedulaRepresentante() { return cedulaRepresentante; }
    public void setCedulaRepresentante(String cedulaRepresentante) { this.cedulaRepresentante = cedulaRepresentante; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(LocalDate fechaIngreso) { this.fechaIngreso = fechaIngreso; }

    public TipoEntrada getTipoEntrada() { return tipoEntrada; }
    public void setTipoEntrada(TipoEntrada tipoEntrada) { this.tipoEntrada = tipoEntrada; }

    public Long getTipoEntradaId() {
        return tipoEntrada != null ? tipoEntrada.getId() : null;
    }

    public void setTipoEntradaId(Long tipoEntradaId) {
        if (tipoEntradaId == null) {
            this.tipoEntrada = null;
            return;
        }
        TipoEntrada tipo = new TipoEntrada();
        tipo.setId(tipoEntradaId);
        this.tipoEntrada = tipo;
    }

    public String getTipoEntradaNombre() {
        return tipoEntrada != null ? tipoEntrada.getNombre() : "";
    }

    public int getDineroVirtual() { return dineroVirtual; }
    public void setDineroVirtual(int dineroVirtual) { this.dineroVirtual = dineroVirtual; }

    public int getPuntosAcumulados() { return puntosAcumulados; }
    public void setPuntosAcumulados(int puntosAcumulados) { this.puntosAcumulados = puntosAcumulados; }
}
