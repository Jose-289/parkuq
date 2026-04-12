package model;

public abstract class Persona {
    protected String nombre;
    protected int id;

    public Persona (String nombre, int id){
        this.nombre = nombre;
        this.id = id;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString (){
        return "Nombre: " +nombre+ " Identificación: "+id;
    }
}
