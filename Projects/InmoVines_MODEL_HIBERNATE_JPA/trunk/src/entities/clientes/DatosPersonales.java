/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.clientes;

import javax.persistence.Column;

/**
 *
 * @author Usuario 2 DAM
 */
public class DatosPersonales {
    
    private String nombre;
    private String apellidos;
    private String mail;
    
    private String telefono;
    @Column(name = "telefono_1")
    private String telefono1;
    @Column(name = "mail_1")
    private String mail1;
    private String dni;
    
    private String nacionalidad;
    private Integer edad;
    
}
