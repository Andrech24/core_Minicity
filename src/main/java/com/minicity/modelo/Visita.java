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
@Table(name = "visitas")
public class Visita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nino_id")
    private Nino nino;

    @Column(name = "fecha_visita")
    private LocalDate fechaVisita;

    @Column(name = "tipo_entrada")
    private String tipoEntrada;

    @Column(name = "monedas_ganadas")
    private int monedasGanadas;

    private String observaciones;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Nino getNino() { return nino; }
    public void setNino(Nino nino) { this.nino = nino; }

    public LocalDate getFechaVisita() { return fechaVisita; }
    public void setFechaVisita(LocalDate fechaVisita) { this.fechaVisita = fechaVisita; }

    public String getTipoEntrada() { return tipoEntrada; }
    public void setTipoEntrada(String tipoEntrada) { this.tipoEntrada = tipoEntrada; }

    public int getMonedasGanadas() { return monedasGanadas; }
    public void setMonedasGanadas(int monedasGanadas) { this.monedasGanadas = monedasGanadas; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}
