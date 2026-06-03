package com.minicity.modelo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservas")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nino_id")
    private Nino nino;

    @Column(name = "nombre_nino")
    private String nombreNino;

    private String representante;
    private String telefono;

    @Column(name = "fecha_reserva")
    private LocalDate fechaReserva;

    @Column(name = "tipo_entrada")
    private String tipoEntrada;

    private String estado;
    private String observaciones;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Nino getNino() { return nino; }
    public void setNino(Nino nino) { this.nino = nino; }

    public Long getNinoId() { return nino != null ? nino.getId() : null; }
    public void setNinoId(Long ninoId) {
        if (ninoId == null) {
            this.nino = null;
            return;
        }
        Nino nuevoNino = new Nino();
        nuevoNino.setId(ninoId);
        this.nino = nuevoNino;
    }

    public String getNombreNino() { return nombreNino; }
    public void setNombreNino(String nombreNino) { this.nombreNino = nombreNino; }

    public String getRepresentante() { return representante; }
    public void setRepresentante(String representante) { this.representante = representante; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public LocalDate getFechaReserva() { return fechaReserva; }
    public void setFechaReserva(LocalDate fechaReserva) { this.fechaReserva = fechaReserva; }

    public String getTipoEntrada() { return tipoEntrada; }
    public void setTipoEntrada(String tipoEntrada) { this.tipoEntrada = tipoEntrada; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}
