package app;

import utilidades.*;
import model.*;
import services.PagoServices;
import services.Parqueadero;

import java.time.LocalDateTime;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Parqueadero p = new Parqueadero();
        PagoServices ps = new PagoServices();
        Usuario s = new Usuario("Jose",123,TipoUsuario.ESTUDIANTE);
        Usuario s1 = new Usuario("Jose",1225,TipoUsuario.ESTUDIANTE);
        Espacio espacio = new Espacio("154",TipoVehiculo.MOTO);
        Espacio espacio1 = new Espacio("144",TipoVehiculo.MOTO);
        Espacio espacio2 = new Espacio("143",TipoVehiculo.CARRO);
        Espacio espacio3 = new Espacio("568",TipoVehiculo.BICICLETA);
        Vehiculo v = new Vehiculo("123",TipoVehiculo.MOTO,"juan",LocalDateTime.of(2026,4,22,10,20),TipoUsuario.ESTUDIANTE);
        Vehiculo v0 = new Vehiculo("183",TipoVehiculo.MOTO,"juan",LocalDateTime.of(2026,4,22,10,20),TipoUsuario.ESTUDIANTE);
        Vehiculo v1 = new Vehiculo("153",TipoVehiculo.CARRO,"juan",LocalDateTime.of(2026,4,22,10,20),TipoUsuario.ESTUDIANTE);
        Vehiculo v3 = new Vehiculo(TipoVehiculo.BICICLETA,"juan",LocalDateTime.of(2026,4,22,10,20),TipoUsuario.ESTUDIANTE);
        Tarifa tm = new Tarifa(TipoVehiculo.MOTO,1000,150);
        Tarifa tc = new Tarifa(TipoVehiculo.CARRO,1500,200);
        Tarifa tb = new Tarifa(TipoVehiculo.BICICLETA,900,200);
        p.crearEspacio(espacio);
        p.crearEspacio(espacio1);
        p.crearEspacio(espacio2);
        System.out.println(p.listarEspacio());
        p.crearVehiculo(v);
        p.crearVehiculo(v1);
        System.out.println( p.listarVehiculo());
        Pago pago = new Pago(v,tm);
        ps.crearPago(pago);
        ps.calcularPago(pago);
        System.out.println(ps.formatearPago(pago));
        System.out.println(ps.listarPago());
        System.out.println(p.listarVehiculo());
        System.out.println(p.crearVehiculo(v0));
        System.out.println(p.listarVehiculo());
        p.crearEspacio(espacio3);
        System.out.println(p.listarEspacio());
        p.crearVehiculo(TipoVehiculo.BICICLETA, v3);
        System.out.println(p.listarVehiculo());
    }
}