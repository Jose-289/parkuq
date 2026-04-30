package services;

import model.Espacio;
import utilidades.Estado;
import utilidades.EstadoVehiculo;
import utilidades.TipoUsuario;
import model.Pago;
import model.Tarifa;
import model.Vehiculo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class  PagoServices {
    private ArrayList<Pago> pagos;
    private ArrayList<Tarifa> tarifas;
    private ArrayList<Vehiculo> vehiculos;

    Parqueadero p = new Parqueadero();

    public PagoServices(){
        this.pagos = new ArrayList<>();
        this.tarifas = new ArrayList<>();
        this.vehiculos = new ArrayList<>();
    }

    public ArrayList<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(ArrayList<Pago> pagos) {
        this.pagos = pagos;
    }

    public ArrayList<Tarifa> getTarifas() {
        return tarifas;
    }

    public void setTarifas(ArrayList<Tarifa> tarifas) {
        this.tarifas = tarifas;
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }


    public void crearPago(Pago pago){
        if (pago != null){
            pagos.add(pago);
        }
    }
    public String formatearPago(Pago pago){
        pago.getVehiculo().setEstadoVehiculo(EstadoVehiculo.SALIO);
        return "Total a pagar: $"+calcularPago(pago);
    }

    public void eliminarPago(Pago pago){
        if(pago != null){
            pagos.remove(pago);
        }
    }
    public String listarPago (){
        if(pagos.isEmpty()){
            return "No hay pagos registrados";
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < pagos.size(); i++){
            sb.append(i+1).append(". ").append(pagos.get(i)).append("\n");
        }
        return sb.toString();
    }
    public double calcularPago(Pago pago) {

        if(pago != null) {

            pago.getVehiculo().setHoraSalida(LocalDateTime.now());

            Duration d = Duration.between(
                    pago.getVehiculo().getHoraIngreso(),
                    pago.getVehiculo().getHoraSalida()
            );

            long minutos = d.toMinutes();
            long horasCobradas = (minutos + 59) / 60;

            Espacio espacio = pago.getVehiculo().getEspacioAsignado();

            if(espacio != null){
                espacio.setEstado(Estado.DISPONIBLE);
                espacio.setVehiculoAsignado(null);

                pago.getVehiculo().setEspacioAsignado(null);
            }

            if(pago.getVehiculo().getTipoUsuario().equals(TipoUsuario.INVITADO)) {

                return horasCobradas * pago.getTarifa().getValorHora();

            } else {

                return horasCobradas *
                        (pago.getTarifa().getValorHora()
                                - pago.getTarifa().getDescuento());
            }
        }

        return -1;
    }
}
