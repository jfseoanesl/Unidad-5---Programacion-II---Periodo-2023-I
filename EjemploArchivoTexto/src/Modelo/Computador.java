/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;

/**
 *
 * @author Jairo F
 */
public class Computador implements Serializable {
    
    private int nSerie;
    private String marca;
    private double precio;

    public Computador() {
    }

    public Computador(int nSerie) {
        this.nSerie = nSerie;
    }

    public Computador(int nSerie, String marca, double precio) {
        this.nSerie = nSerie;
        this.marca = marca;
        this.precio = precio;
    }

    /**
     * @return the nSerie
     */
    public int getnSerie() {
        return nSerie;
    }

    /**
     * @param nSerie the nSerie to set
     */
    public void setnSerie(int nSerie) {
        this.nSerie = nSerie;
    }

    /**
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Computador{" + "nSerie=" + nSerie + ", marca=" + marca + ", precio=" + precio + '}';
    }
    
    public String getDataFileFormat(){
        return this.nSerie + ";" + this.marca + ";" + this.precio;
    }
    
}
