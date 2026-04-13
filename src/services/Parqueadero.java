package services;

import enums.*;
import model.*;
import javax.swing.*;
import java.time.LocalTime;
import java.util.ArrayList;

public class Parqueadero {
    private ArrayList<Usuario> usuarios;
    private ArrayList<Vehiculo> vehiculos;
    private ArrayList<Espacio> espacios;
    private ArrayList<Operador> operadores;
    private ArrayList<Administrador> administradores;
    private ArrayList<Tarifa> tarifas;

    public Parqueadero() {
        this.usuarios = new ArrayList<>();
        this.vehiculos = new ArrayList<>();
        this.espacios = new ArrayList<>();
        this.operadores = new ArrayList<>();
        this.administradores = new ArrayList<>();
        this.tarifas = new ArrayList<>();
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public ArrayList<Espacio> getEspacios() {
        return espacios;
    }

    public void setEspacios(ArrayList<Espacio> espacios) {
        this.espacios = espacios;
    }

    public ArrayList<Operador> getOperadores() {
        return operadores;
    }

    public void setOperadores(ArrayList<Operador> operadores) {
        this.operadores = operadores;
    }

    public ArrayList<Administrador> getAdministradores() {
        return administradores;
    }

    public void setAdministradores(ArrayList<Administrador> administradores) {
        this.administradores = administradores;
    }

    public ArrayList<Tarifa> getTarifas() {
        return tarifas;
    }

    public void setTarifas(ArrayList<Tarifa> tarifas) {
        this.tarifas = tarifas;
    }

    //CRUD PARA USUARIO

    public Usuario buscarUsuario(int id) {
        for (Usuario s : usuarios) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    public void crearUsuario(Usuario usuario) {
        Usuario u = buscarUsuario(usuario.getId());
        if (u == null) {
            usuarios.add(usuario);
        }
    }

    public void listarUsuario() {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados");
        }
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println((i + 1) + ". " + usuarios.get(i));
        }
    }

    public void eliminarUsuario(int id) {

        if (buscarUsuario(id) == null) {
            System.out.println("El usuario que intenta eliminar no existe");
        } else {
            usuarios.removeIf(usuario -> {
                int buscar = usuario.getId();
                return buscar == id;
            });
            System.out.println("El ususario se elimino con exito");
        }
    }

    public Usuario actualizarUsuario(Usuario s) {
        if (buscarUsuario(s.getId()) != null) {
            String nombre = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre del usuario");
            String idS = JOptionPane.showInputDialog(null, "Ingrese el nuevo id del usuario");
            TipoUsuario t = (TipoUsuario) JOptionPane.showInputDialog(null, "Tipos de usuario",
                    "Elija un tipo de usuario",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    TipoUsuario.values(),
                    TipoUsuario.values()[0]);
            int idI = Integer.parseInt(idS);
            s.setNombre(nombre);
            s.setId(idI);
            s.setTipoUsuario(t);

        }
        return s;
    }

    //CRUD PARA OPERADOR

    public Operador buscarOPerador(int id) {
        for (Operador o : operadores) {
            if (o.getId() == id) {
                return o;
            }
        }
        return null;
    }

    public void crearOperador(Operador operador) {
        Operador o = buscarOPerador(operador.getId());
        if (o == null) {
            operadores.add(operador);
        }
    }

    public void listarOperador() {
        if (operadores.isEmpty()) {
            System.out.println("No hay operadores registrados");
        }
        for (int i = 0; i < operadores.size(); i++) {
            System.out.println((i + 1) + ". " + operadores.get(i));
        }
    }

    public void eliminarOperador(int id) {

        if (buscarOPerador(id) == null) {
            System.out.println("El operario que intenta eliminar no existe");
        } else {
            operadores.removeIf(operador -> {
                int buscar = operador.getId();
                return buscar == id;
            });
            System.out.println("El operador se elimino con exito");
        }
    }

    public Operador actualizarOperador(Operador o) {
        if (buscarOPerador(o.getId()) != null) {
            String nombre = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre del operador");
            String idS = JOptionPane.showInputDialog(null, "Ingrese el nuevo id del operador");
            String codigo = JOptionPane.showInputDialog(null, "Ingrese el nuevo código operador");
            int idI = Integer.parseInt(idS);

            o.setNombre(nombre);
            o.setId(idI);
            o.setCodigo(codigo);

        }
        return o;
    }

    //CRUD PARA ADMINISTRADOR

    public Administrador buscarAdministrador(int id) {
        for (Administrador a : administradores) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    public void crearAdministrador(Administrador administrador) {
        Administrador a = buscarAdministrador(administrador.getId());
        if (a == null) {
            administradores.add(administrador);
        }
    }

    public void listarAdministrador() {
        if (administradores.isEmpty()) {
            System.out.println("No hay administradores registrados");
        }
        for (int i = 0; i < administradores.size(); i++) {
            System.out.println((i + 1) + ". " + administradores.get(i));
        }
    }

    public void eliminarAdministrador(int id) {
        if (buscarAdministrador(id) == null) {
            System.out.println("El administrador que intenta eliminar no existe");
        } else {
            administradores.removeIf(administrador -> {
                int buscar = administrador.getId();
                return buscar == id;
            });
            System.out.println("El administrador se elimino con exito");
        }
    }

    public Administrador actualizarAdministrador(Administrador administrador) {
        if (buscarAdministrador(administrador.getId()) != null) {
            String nombre = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre del administrador");
            String idS = JOptionPane.showInputDialog(null, "Ingrese el nuevo id del administrador");
            String codigo = JOptionPane.showInputDialog(null, "Ingrese el nuevo código administrador");
            int idI = Integer.parseInt(idS);

            administrador.setNombre(nombre);
            administrador.setId(idI);
            administrador.setCodigo(codigo);

        }
        return administrador;
    }

    //CRUD PARA ESPACIO

    public Espacio buscarEspacio(String codigo) {
        for (Espacio e : espacios) {
            if (e.normalizar(codigo).equalsIgnoreCase(e.getCodigo())) {
                return e;
            }
        }
        return null;
    }

    public void crearEspacio(Espacio espacio) {
        if (buscarEspacio(espacio.getCodigo()) == null) {
            espacios.add(espacio);
            System.out.println("El espacio se añadio correctamente");
        } else {
            System.out.println("Este espacio ya se encuentra registrado");
        }

    }

    public void listarEspacio() {
        if (espacios.isEmpty()) {
            System.out.println("No hay espacios registrados");
        }
        for (int i = 0; i < espacios.size(); i++) {
            System.out.println((i + 1) + ". " + espacios.get(i));
        }
    }

    public void eliminarEspacio(String codigo) {
        if (buscarEspacio(codigo) == null) {
            System.out.println("El espacio que intenta eliminar no existe");
        } else {
            espacios.removeIf(e -> {
                String buscar = e.getCodigo();
                return buscar.equalsIgnoreCase(e.normalizar(codigo));
            });
            System.out.println("Espacio eliminado correctamente");
        }
    }

    public Espacio actualizarEspacio(Espacio espacio, Vehiculo vehiculo) {
        if (buscarEspacio(espacio.getCodigo()) == null) {
            return null;
        } else {
            String codigo = JOptionPane.showInputDialog(null, "Ingrese el nuevo codigo");
            TipoEspacio tipo = (TipoEspacio) JOptionPane.showInputDialog(null, "Tipos de espacio", "Elija un tipo de espacio",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    TipoEspacio.values(),
                    TipoEspacio.values()[0]);
            Estado estado = (Estado) JOptionPane.showInputDialog(null, "Estados", "Elija un estado", JOptionPane.QUESTION_MESSAGE,
                    null,
                    Estado.values(),
                    Estado.values()[0]);
            espacio.setCodigo(codigo);
            espacio.setTipoEspacio(tipo);
            espacio.setEstado(estado);
            espacio.setVehiculoAsignado(vehiculo);
        }
        return espacio;
    }

    // CRUD PARA VEHICULO

    public Vehiculo buscarVehiculo(String placa) {
        for (Vehiculo v : vehiculos) {
            if (v.normalizar(placa).equalsIgnoreCase(v.getPlaca())) {
                return v;
            }
        }
        return null;
    }

    public void crearVehiculo(Vehiculo vehiculo) {

        if (buscarVehiculo(vehiculo.getPlaca()) == null) {
            vehiculos.add(vehiculo);
            System.out.println("El vehiculo ha sido añadido");
        } else {
            System.out.println("El vehiculo con esta placa ya esta registrado");
        }
    }

    public void listarVehiculo() {
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehiculos aun");
        }
        for (int i = 0; i < vehiculos.size(); i++) {
            System.out.println((i + 1) + ". " + vehiculos.get(i));
        }
    }

    public void eliminarVehiculo(String placa) {
        if (buscarVehiculo(placa) == null) {
            System.out.println("El vehiculo que intenta eliminar no existe");
        } else {
            vehiculos.removeIf(v -> {
                String buscar = v.getPlaca();
                return buscar.equalsIgnoreCase(v.normalizar(placa));
            });
            System.out.println("El vehiculo se elimino correctamente");
        }
    }

    public Vehiculo actualizarVehiculo(Vehiculo vehiculo, Espacio espacio) {
        if (buscarVehiculo(vehiculo.getPlaca()) == null) {
            return null;
        } else {
            String placa = JOptionPane.showInputDialog(null, "Ingrese la nueva placa del vehiculo");
            TipoVehiculo tipo = (TipoVehiculo) JOptionPane.showInputDialog(null, "Tipos de vehiculo", "Elija un tipo de vehiculo", JOptionPane.QUESTION_MESSAGE,
                    null,
                    TipoVehiculo.values(),
                    TipoVehiculo.values()[0]);
            String nombreConductor = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre del conductor");
            String ingreso = JOptionPane.showInputDialog(null, "digite la hora de ingreso en este formato HH:MM");
            LocalTime horaIngreso = LocalTime.parse(ingreso);
            String salida = JOptionPane.showInputDialog(null, "digite la hora de salida en este formato HH:MM");
            LocalTime horaSalida = LocalTime.parse(salida);
            EstadoVehiculo estado = (EstadoVehiculo) JOptionPane.showInputDialog(null, "Estados disponibles ", "Elija un estado", JOptionPane.QUESTION_MESSAGE,
                    null,
                    EstadoVehiculo.values(),
                    EstadoVehiculo.values()[0]);
            vehiculo.setPlaca(placa);
            vehiculo.setVehiculo(tipo);
            vehiculo.setNombreConductor(nombreConductor);
            vehiculo.setHoraIngreso(horaIngreso);
            vehiculo.setHoraSalida(horaSalida);
            vehiculo.setEspacioAsignado(espacio);
            vehiculo.setEstadoVehiculo(estado);

        }
        return vehiculo;
    }

    //CRUD PARA TARIFA

}
