package model;

import utilidades.TipoVehiculo;

public class Tarifa {
    private TipoVehiculo tipoVehiculo;
    private double valorHora;
    private double descuento;

    public Tarifa (TipoVehiculo tipoVehiculo, double valorHora, double descuento){
        this.tipoVehiculo = tipoVehiculo;
        this.valorHora = valorHora;
        this.descuento = descuento;
    }
    public Tarifa (TipoVehiculo tipoVehiculo, double valorHora){
        this.tipoVehiculo = tipoVehiculo;
        this.valorHora = valorHora;
    }

    public TipoVehiculo getTipoVehiculo(){
        return tipoVehiculo;
    }
    public void setTipoVehiculo (TipoVehiculo tipoVehiculo){
        this.tipoVehiculo = tipoVehiculo;
    }

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
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
