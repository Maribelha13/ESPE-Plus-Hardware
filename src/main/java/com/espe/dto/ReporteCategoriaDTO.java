package com.espe.dto;

import com.espe.entity.HardwareEntity;
import java.math.BigDecimal;

public class ReporteCategoriaDTO {
    private BigDecimal valorTotal;
    private double precioPromedio;
    private HardwareEntity equipoMasCaro;

    public ReporteCategoriaDTO(BigDecimal valorTotal, double precioPromedio, HardwareEntity equipoMasCaro) {
        this.valorTotal = valorTotal;
        this.precioPromedio = precioPromedio;
        this.equipoMasCaro = equipoMasCaro;
    }


    // Getters y Setters
    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }

    public double getPrecioPromedio() { return precioPromedio; }
    public void setPrecioPromedio(double precioPromedio) { this.precioPromedio = precioPromedio; }

    public HardwareEntity getEquipoMasCaro() { return equipoMasCaro; }
    public void setEquipoMasCaro(HardwareEntity equipoMasCaro) { this.equipoMasCaro = equipoMasCaro; }
}