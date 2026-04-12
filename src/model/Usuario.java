package model;

import enums.TipoUsuario;

public class Usuario extends Persona {

    private TipoUsuario tipoUsuario;

    public Usuario(String nombre, int id, TipoUsuario tipoUsuario){
        super(nombre,id);
        this.tipoUsuario = tipoUsuario;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    @Override
    public String toString(){
        return super.toString()+ " Tipo de usuario: "+tipoUsuario;
    }
}
