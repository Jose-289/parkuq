package model;

import enums.EstadoVehiculo;
import enums.TipoVehiculo;
import interfaces.Util;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalTime;

public class Vehiculo implements Util {
    private String placa;
    private TipoVehiculo vehiculo;
    private String nombreConductor;
    private LocalTime horaIngreso;
    private LocalTime horaSalida;
    private Espacio espacioAsignado;
    private EstadoVehiculo estadoVehiculo;

    public Vehiculo(String placa, TipoVehiculo vehiculo, String nombreConductor, LocalTime horaIngreso, LocalTime horaSalida, Espacio espacioAsignado, EstadoVehiculo estadoVehiculo) {
        this.placa = placa;
        this.vehiculo = vehiculo;
        this.nombreConductor = nombreConductor;
        this.horaIngreso = horaIngreso;
        this.horaSalida = horaSalida;
        this.espacioAsignado = espacioAsignado;
        espacioAsignado.setVehiculoAsignado(this);
        this.estadoVehiculo = estadoVehiculo;
    }

    @Override
    public String normalizar(String texto){
        return Normalizer.normalize(texto,Normalizer.Form.NFD)
                .replaceAll("\\p{M}","")
                .replaceAll("\\s+","").trim()
                .toLowerCase();
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public TipoVehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(TipoVehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getNombreConductor() {
        return nombreConductor;
    }

    public void setNombreConductor(String nombreConductor) {
        this.nombreConductor = nombreConductor;
    }

    public LocalTime getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(LocalTime horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Espacio getEspacioAsignado() {
        return espacioAsignado;
    }

    public void setEspacioAsignado(Espacio espacioAsignado) {
        this.espacioAsignado = espacioAsignado;
    }

    public EstadoVehiculo getEstadoVehiculo() {
        return estadoVehiculo;
    }

    public void setEstadoVehiculo(EstadoVehiculo estadoVehiculo) {
        this.estadoVehiculo = estadoVehiculo;
    }
    @Override
    public String toString (){
        return " Placa: "+placa+" \n"+
                " Tipo de vehiculo: "+vehiculo+" \n"+
                " Nombre del conductor: "+nombreConductor+" \n"+
                " Hora de ingreso: "+horaIngreso+" \n"+
                " Hora de salida: "+horaSalida+" \n"+
                " Espacio asignado: "+espacioAsignado+" \n"+
                " Estado del vehiculo: "+estadoVehiculo+" \n";
    }
}
