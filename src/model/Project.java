package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Tablero del proyecto
 * Created by Marc on 13/3/18.
 */
public class Project implements Serializable{

    private String name;
    private ArrayList<Usuari> membres;
    private ArrayList<Columna> columnes;
    private String background;
    private ArrayList<Etiqueta> etiquetes;
    private int day;
    private int month;
    private int year;


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

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public ArrayList<Etiqueta> getEtiquetes() {
        return etiquetes;
    }

    public void setEtiquetes(ArrayList<Etiqueta> etiquetes) {
        this.etiquetes = etiquetes;
    }

    public void setDate (){
        java.util.Date fecha = new Date();

        day = fecha.getDay();
        month = fecha.getMonth();
        year = fecha.getYear();
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}