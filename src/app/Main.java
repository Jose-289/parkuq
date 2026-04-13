package app;

import enums.*;
import model.*;
import services.Parqueadero;
import java.time.LocalTime;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Parqueadero p = new Parqueadero();
        Espacio e = new Espacio("123mil", TipoEspacio.BICICLETA, Estado.DISPONIBLE);
        Vehiculo v = new Vehiculo("123Ton", TipoVehiculo.MOTO,"Juan", LocalTime.now(),LocalTime.of(14,30),e, EstadoVehiculo.ENTRO);
        p.crearEspacio(e);
        System.out.println(p.buscarEspacio("123 M   íl "));
        p.listarEspacio();
        p.crearVehiculo(v);
        p.listarVehiculo();
        p.actualizarEspacio(e,v);
        p.actualizarVehiculo(v,e);
        p.listarVehiculo();
        p.listarEspacio();


    }
}