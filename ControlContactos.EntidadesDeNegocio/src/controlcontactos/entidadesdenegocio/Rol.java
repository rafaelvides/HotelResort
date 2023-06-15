package controlcontactos.entidadesdenegocio;
//importaciones de objetos a ocupar
import java.util.ArrayList;
//definicion de las entidades referentes a la bd
public class Rol {
    private int id;
    private String nombre;
    private int top_aux;
    private ArrayList<Usuario> usuarios;
//constructor basio
    public Rol() {
    }
//constructor lleno 
    public Rol(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
//constructor creado de get y set
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
