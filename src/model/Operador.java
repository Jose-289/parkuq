package model;

public class Operador extends Persona{
    private String codigo;

    public Operador(String nombre, int id, String codigo){
        super(nombre, id);
        this.codigo = codigo;
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
