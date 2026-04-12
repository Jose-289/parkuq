package model;

import enums.EstadoVehiculo;
import enums.TipoVehiculo;

import java.time.LocalDate;

public class Vehiculo {
    private String placa;
    private TipoVehiculo vehiculo;
    private String nombreConductor;
    private LocalDate horaIngreso;
    private LocalDate horaSalida;
    private Espacio espacioAsignado;
    private EstadoVehiculo estadoVehiculo;

    public Vehiculo(String placa, TipoVehiculo vehiculo, String nombreConductor, LocalDate horaIngreso, LocalDate horaSalida, Espacio espacioAsignado, EstadoVehiculo estadoVehiculo) {
        this.placa = placa;
        this.vehiculo = vehiculo;
        this.nombreConductor = nombreConductor;
        this.horaIngreso = horaIngreso;
        this.horaSalida = horaSalida;
        this.espacioAsignado = espacioAsignado;
        this.estadoVehiculo = estadoVehiculo;
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

    public LocalDate getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(LocalDate horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public LocalDate getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalDate horaSalida) {
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
