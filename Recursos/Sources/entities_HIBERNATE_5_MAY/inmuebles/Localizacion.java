/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.inmuebles;

import entities.Comunidades;
import entities.Municipios;
import entities.Provincias;
import entities.Zonas;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Usuario 2 DAM
 */
@Embeddable
public class Localizacion {

    /**
     * ***********************************ID COMUNIDAD ID MUNICIPIOS ID PROVINCIA*****************************************
     */
    @JoinColumn(name = "id_comunidad", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Comunidades comunidad;
    @JoinColumn(name = "id_municipios", referencedColumnName = "ID")
    @ManyToOne
    private Municipios municipio;
    @JoinColumn(name = "id_provincia", referencedColumnName = "ID")
    @ManyToOne
    private Provincias provincia;

    /**
     * *****************************CARACTERÍSTICAS DE LA ZONA******************************************
     */
    @JoinColumn(name = "id_zona", referencedColumnName = "id")
    @ManyToOne
    private Zonas zona;
    @Column(name = "cp")
    private int cp;
    @Column(name = "latitud")
    private float latitud;
    @Column(name = "longitud")
    private float longitud;

    /**
     * ******************************OBJETO DIRECCIÓN*****************************************
     */
    @Embedded
    private Direccion direccion;

    public Localizacion() {
    }

    public Localizacion(Provincias provincia, Municipios municipio, Zonas zona, int cp, float latitud, float longitud, Direccion direccion) {

        this.provincia = provincia;
        this.municipio = municipio;
        this.zona = zona;
        this.cp = cp;
        this.latitud = latitud;
        this.longitud = longitud;
        this.direccion = direccion;
    }

    public Provincias getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincias provincia) {
        this.provincia = provincia;
    }

    public Municipios getPoblacion() {
        return municipio;
    }

    public void setMunicipio(Municipios municipio) {
        this.municipio = municipio;
    }

    public Zonas getZona() {
        return zona;
    }

    public void setZona(Zonas zona) {
        this.zona = zona;
    }

    public Comunidades getComunidad() {
        return comunidad;
    }

    public void setComunidad(Comunidades comunidad) {
        this.comunidad = comunidad;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

}
