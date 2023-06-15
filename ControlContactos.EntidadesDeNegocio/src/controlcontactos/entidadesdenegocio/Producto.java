package controlcontactos.entidadesdenegocio;

import java.util.ArrayList;

public class Producto {
    private int id;
    private String nproducto;
    private String caracteristica;
    private  String presio;
    private int top_aux;

    public Producto() {
    }

    public Producto(int id, String nproducto, String caracteristica, String presio) {
        this.id = id;
        this.nproducto = nproducto;
        this.caracteristica = caracteristica;
        this.presio = presio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNproducto() {
        return nproducto;
    }

    public void setNproducto(String nproducto) {
        this.nproducto = nproducto;
    }

    public String getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(String caracteristica) {
        this.caracteristica = caracteristica;
    }

    public String getPresio() {
        return presio;
    }

    public void setPresio(String presio) {
        this.presio = presio;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }
    
    
}
