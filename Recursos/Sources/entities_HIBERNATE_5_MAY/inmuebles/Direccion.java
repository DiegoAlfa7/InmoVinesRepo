/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.inmuebles;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Usuario 2 DAM
 */
@Embeddable
public class Direccion {

    @Column(name = "direccion_tipo_via")
    private String direccionTipoVia;
    @Column(name = "direccion_calle")
    private String direccionCalle;
    @Column(name = "direccion_numero")
    private String direccionNumero;
    @Column(name = "direccion_piso")
    private String direccionPiso;
    @Column(name = "direccion_letra")
    private String direccionLetra;
    @Column(name = "direccion_escalera")
    private String direccionEscalera;

    public Direccion() {
    }

    public Direccion(String direccionTipoVia, String direccionCalle, String direccionNumero, String direccionPiso, String direccionLetra, String direccionEscalera) {
        this.direccionTipoVia = direccionTipoVia;
        this.direccionCalle = direccionCalle;
        this.direccionNumero = direccionNumero;
        this.direccionPiso = direccionPiso;
        this.direccionLetra = direccionLetra;
        this.direccionEscalera = direccionEscalera;
    }

    public String getDireccionTipoVia() {
        return direccionTipoVia;
    }

    public void setDireccionTipoVia(String direccionTipoVia) {
        this.direccionTipoVia = direccionTipoVia;
    }

    public String getDireccionCalle() {
        return direccionCalle;
    }

    public void setDireccionCalle(String direccionCalle) {
        this.direccionCalle = direccionCalle;
    }

    public String getDireccionNumero() {
        return direccionNumero;
    }

    public void setDireccionNumero(String direccionNumero) {
        this.direccionNumero = direccionNumero;
    }

    public String getDireccionPiso() {
        return direccionPiso;
    }

    public void setDireccionPiso(String direccionPiso) {
        this.direccionPiso = direccionPiso;
    }

    public String getDireccionLetra() {
        return direccionLetra;
    }

    public void setDireccionLetra(String direccionLetra) {
        this.direccionLetra = direccionLetra;
    }

    public String getDireccionEscalera() {
        return direccionEscalera;
    }

    public void setDireccionEscalera(String direccionEscalera) {
        this.direccionEscalera = direccionEscalera;
    }

}
