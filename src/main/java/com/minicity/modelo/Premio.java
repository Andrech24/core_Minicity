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
@Table(name = "premios")
public class Premio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nino_id")
    private Nino nino;

    @Column(name = "fecha_premio")
    private LocalDate fechaPremio;

    private String nombre;
    private String tipo;
    private String estado;

    @Column(name = "monedas_canjeadas")
    private int monedasCanjeadas;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Nino getNino() { return nino; }
    public void setNino(Nino nino) { this.nino = nino; }

    public LocalDate getFechaPremio() { return fechaPremio; }
    public void setFechaPremio(LocalDate fechaPremio) { this.fechaPremio = fechaPremio; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public int getMonedasCanjeadas() { return monedasCanjeadas; }
    public void setMonedasCanjeadas(int monedasCanjeadas) { this.monedasCanjeadas = monedasCanjeadas; }
}
