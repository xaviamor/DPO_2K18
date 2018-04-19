package model;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Tablero del proyecto
 * Created by Marc on 13/3/18.
 */
public class Projecte implements Serializable{

    private String name;
    private ArrayList<Usuari> membres;
    private ArrayList<Columna> columnes;
    private Image background;
    private ArrayList<Etiqueta> etiquetes;
    private Date fecha;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Usuari> getMembres() {
        return membres;
    }

    public void setMembres(ArrayList<Usuari> membres) {
        this.membres = membres;
    }

    public ArrayList<Columna> getColumnes() {
        return columnes;
    }

    public void setColumnes(ArrayList<Columna> columnes) {
        this.columnes = columnes;
    }

    public Image getBackground() {
        return background;
    }

    public void setBackground(Image background) {
        this.background = background;
    }

    public ArrayList<Etiqueta> getEtiquetes() {
        return etiquetes;
    }

    public void setEtiquetes(ArrayList<Etiqueta> etiquetes) {
        this.etiquetes = etiquetes;
    }
}
