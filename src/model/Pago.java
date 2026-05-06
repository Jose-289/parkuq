package model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class Pago {
    private Vehiculo vehiculo;
    private Tarifa tarifa;
    private double valorPagar;
    private LocalDateTime fecha;

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;
    }

    public Pago(Vehiculo vehiculo, Tarifa tarifa) {
        this.vehiculo = vehiculo;
        this.tarifa = tarifa;
    }

    public double getValorPagar() {
        return valorPagar;
    }

    public void setValorPagar(double valorPagar) {
        this.valorPagar = valorPagar;
    }

    public String formatearHora(ZonedDateTime fecha){
        DateTimeFormatter formato = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)
                .withLocale(Locale.getDefault());
        return formato.format(fecha);
    }

    @Override
    public String toString(){
        return vehiculo+"\n"+tarifa+"\n"+"Debe pagar $"+ valorPagar+"\n"+"Fecha: "+(fecha != null ? formatearHora(fecha.atZone(ZoneId.systemDefault())) : "Sin registro");
    }
}
