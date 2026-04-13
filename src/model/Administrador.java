package model;

import interfaces.Util;

import java.text.Normalizer;

public class Administrador  extends Persona implements Util {
    private String codigo;

    public Administrador (String nombre, int id, String codigo){
        super(nombre, id);
        this.codigo = codigo;
    }
    @Override
    public  String normalizar(String texto){
        return Normalizer.normalize(texto,Normalizer.Form.NFD)
                .replaceAll("\\p{M}","")
                .toLowerCase();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    @Override

    public String toString(){
        return super.toString()+ " Codigo: "+codigo;
    }
}
