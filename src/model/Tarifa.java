package model;

import enums.TipoVehiculo;

import java.time.LocalDate;

public class Tarifa {
    private TipoVehiculo tipoVehiculo;
    private LocalDate valorHora;
    private double descuento;

    public Tarifa (TipoVehiculo tipoVehiculo, LocalDate valorHora, double descuento){
        this.tipoVehiculo = tipoVehiculo;
        this.valorHora = valorHora;
        this.descuento = descuento;
    }

    public TipoVehiculo getTipoVehiculo(){
        return tipoVehiculo;
    }
    public void setTipoVehiculo (TipoVehiculo tipoVehiculo){
        this.tipoVehiculo = tipoVehiculo;
    }

    public LocalDate getValorHora() {
        return valorHora;
    }

    public void setValorHora(LocalDate valorHora) {
        this.valorHora = valorHora;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }
    @Override

    public String toString(){
        return "Tipo de vehiculo: "+tipoVehiculo+ " Valor por hora: "+valorHora+" descuento: "+descuento;
    }
}
