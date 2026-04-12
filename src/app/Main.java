package app;

import enums.TipoUsuario;
import model.Administrador;
import model.Operador;
import model.Usuario;
import services.Parqueadero;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Parqueadero p = new Parqueadero();
        Usuario s = new Usuario("jose",123, TipoUsuario.DOCENTE);
        Operador o = new Operador("juan",212,"1256a");
        Administrador a = new Administrador("luis",145,"1235lk");
        p.crearUsuario(s);
        p.listarUsuario();
        System.out.println(p.buscarUsuario(123));
        p.listarUsuario();
        p.actualizarUsuario(s);
        p.eliminarOperador(146);



    }
}