package com.minicity.modelo;

import java.math.BigDecimal;
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
@Table(name = "compras_entradas")
public class CompraEntrada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nino_id")
    private Nino nino;

    @Column(name = "fecha_compra")
    private LocalDate fechaCompra;

    @Column(name = "tipo_entrada")
    private String tipoEntrada;

    private int cantidad;
    private BigDecimal total;

    @Column(name = "monedas_ganadas")
    private int monedasGanadas;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Nino getNino() { return nino; }
    public void setNino(Nino nino) { this.nino = nino; }

    public LocalDate getFechaCompra() { return fechaCompra; }
    public void setFechaCompra(LocalDate fechaCompra) { this.fechaCompra = fechaCompra; }

    public String getTipoEntrada() { return tipoEntrada; }
    public void setTipoEntrada(String tipoEntrada) { this.tipoEntrada = tipoEntrada; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public int getMonedasGanadas() { return monedasGanadas; }
    public void setMonedasGanadas(int monedasGanadas) { this.monedasGanadas = monedasGanadas; }
}
