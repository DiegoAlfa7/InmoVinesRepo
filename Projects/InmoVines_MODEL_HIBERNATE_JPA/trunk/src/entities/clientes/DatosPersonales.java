/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.clientes;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Usuario 2 DAM
 */
@Embeddable
public class DatosPersonales {

    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "mail")
    private String mail;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "telefono_1")
    private String telefono1;
    @Column(name = "mail_1")
    private String mail1;
    @Column(name = "dni")
    private String dni;
    @Column(name = "nacionalidad")
    private String nacionalidad;
    @Column(name = "edad")
    private Integer edad;

    public DatosPersonales(String nombre, String apellidos, String mail, String telefono, String telefono1, String mail1, String dni, String nacionalidad, Integer edad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.mail = mail;
        this.telefono = telefono;
        this.telefono1 = telefono1;
        this.mail1 = mail1;
        this.dni = dni;
        this.nacionalidad = nacionalidad;
        this.edad = edad;
    }
    public DatosPersonales() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getMail1() {
        return mail1;
    }

    public void setMail1(String mail1) {
        this.mail1 = mail1;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

}
