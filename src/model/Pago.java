package model;

public class Pago {
    private Vehiculo vehiculo;
    private Tarifa tarifa;

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

    @Override
    public String toString(){
        return vehiculo+"\n"+tarifa;
    }
}
