package com.mycompany.peluqueriacanina.logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Mascota implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int numCliente;
    private String nombrePerro;
    private String raza;
    private String color;
    private String alergico;
    private String atencionEsp;
    private String observaciones;
    @OneToOne
    private Duenio duenio;

    public Mascota() {
    }

    public Mascota(int numCliente, String nombrePerro, String raza, String color, String alergico, String atencionEspecial, String observaciones, Duenio duenio) {
        this.numCliente = numCliente;
        this.nombrePerro = nombrePerro;
        this.raza = raza;
        this.color = color;
        this.alergico = alergico;
        this.atencionEsp = atencionEspecial;
        this.observaciones = observaciones;
        this.duenio = duenio;
    }

    public int getNumCliente() {
        return numCliente;
    }

    public void setNumCliente(int numCliente) {
        this.numCliente = numCliente;
    }

    public String getNombre() {
        return nombrePerro;
    }

    public void setNombre(String nombre) {
        this.nombrePerro = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAlergico() {
        return alergico;
    }

    public void setAlergico(String alergico) {
        this.alergico = alergico;
    }

    public String getAtencionEspecial() {
        return atencionEsp;
    }

    public void setAtencionEspecial(String atencionEspecial) {
        this.atencionEsp = atencionEspecial;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Duenio getDuenio() {
        return duenio;
    }

    public void setDuenio(Duenio duenio) {
        this.duenio = duenio;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

}
