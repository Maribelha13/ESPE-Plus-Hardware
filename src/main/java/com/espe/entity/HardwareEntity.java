package com.espe.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "hardware")
public class HardwareEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelo;

    @Enumerated(EnumType.STRING)
    private CategoriaHardware categoria;

    private BigDecimal precio;

    @Column(name = "fecha_compra")
    private LocalDate fechaCompra;

    @Enumerated(EnumType.STRING)
    private EstadoHardware estado;


    // Constructores
    public HardwareEntity() {}

    public HardwareEntity(Long id, String modelo, CategoriaHardware categoria, BigDecimal precio, LocalDate fechaCompra, EstadoHardware estado) {
        this.id = id;
        this.modelo = modelo;
        this.categoria = categoria;
        this.precio = precio;
        this.fechaCompra = fechaCompra;
        this.estado = estado;
    }


    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public CategoriaHardware getCategoria() { return categoria; }
    public void setCategoria(CategoriaHardware categoria) { this.categoria = categoria; }
    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }
    public LocalDate getFechaCompra() { return fechaCompra; }
    public void setFechaCompra(LocalDate fechaCompra) { this.fechaCompra = fechaCompra; }
    public EstadoHardware getEstado() { return estado; }
    public void setEstado(EstadoHardware estado) { this.estado = estado; }
}