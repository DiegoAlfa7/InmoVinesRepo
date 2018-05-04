/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.clientes;

import entities.Municipios;
import entities.Provincias;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario 2 DAM
 */
@Entity
@Table(catalog = "inmovinescrm", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Intereses.findAll", query = "SELECT i FROM Intereses i")
    , @NamedQuery(name = "Intereses.findById", query = "SELECT i FROM Intereses i WHERE i.id = :id")
    , @NamedQuery(name = "Intereses.findByHabitaciones", query = "SELECT i FROM Intereses i WHERE i.habitaciones = :habitaciones")
    , @NamedQuery(name = "Intereses.findByBa\u00f1os", query = "SELECT i FROM Intereses i WHERE i.ba\u00f1os = :ba\u00f1os")
    , @NamedQuery(name = "Intereses.findByM2", query = "SELECT i FROM Intereses i WHERE i.m2 = :m2")
    , @NamedQuery(name = "Intereses.findByPrecioMax", query = "SELECT i FROM Intereses i WHERE i.precioMax = :precioMax")})
public class Intereses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;
    @Basic(optional = false)
    private int habitaciones;
    @Basic(optional = false)
    private int baños;
    @Basic(optional = false)
    private int m2;
    @Basic(optional = false)
    private int precioMax;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Clientes idCliente;
    @JoinColumn(name = "id_municipio", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Municipios idMunicipio;
    @JoinColumn(name = "id_provincia", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Provincias idProvincia;

    public Intereses() {
    }

    public Intereses(Long id) {
        this.id = id;
    }

    public Intereses(Long id, int habitaciones, int baños, int m2, int precioMax) {
        this.id = id;
        this.habitaciones = habitaciones;
        this.baños = baños;
        this.m2 = m2;
        this.precioMax = precioMax;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(int habitaciones) {
        this.habitaciones = habitaciones;
    }

    public int getBaños() {
        return baños;
    }

    public void setBaños(int baños) {
        this.baños = baños;
    }

    public int getM2() {
        return m2;
    }

    public void setM2(int m2) {
        this.m2 = m2;
    }

    public int getPrecioMax() {
        return precioMax;
    }

    public void setPrecioMax(int precioMax) {
        this.precioMax = precioMax;
    }

    public Clientes getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Clientes idCliente) {
        this.idCliente = idCliente;
    }

    public Municipios getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Municipios idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Provincias getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Provincias idProvincia) {
        this.idProvincia = idProvincia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Intereses)) {
            return false;
        }
        Intereses other = (Intereses) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.cliente.Intereses[ id=" + id + " ]";
    }
    
}
