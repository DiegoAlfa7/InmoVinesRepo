/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diegoa.inmovinesrest.entities.agentes;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Usuario 2 DAM
 */
@Entity
@Table(name = "cargos", schema = "inmovinescrm")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Cargos.findAll", query = "SELECT c FROM Cargos c")
        , @NamedQuery(name = "Cargos.findByIdCargo", query = "SELECT c FROM Cargos c WHERE c.idCargo = :idCargo")
        , @NamedQuery(name = "Cargos.findByNombre", query = "SELECT c FROM Cargos c WHERE c.nombre = :nombre")})
public class Cargos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cargo")
    private Long idCargo;
    @Basic(optional = false)
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCargo")
    private List<Agentes> agentesList;

    public Cargos() {
    }

    public Cargos(Long idCargo) {
        this.idCargo = idCargo;
    }

    public Cargos(Long idCargo, String nombre) {
        this.idCargo = idCargo;
        this.nombre = nombre;
    }

    public Long getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Long idCargo) {
        this.idCargo = idCargo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    @JsonIgnore
    public List<Agentes> getAgentesList() {
        return agentesList;
    }

    public void setAgentesList(List<Agentes> agentesList) {
        this.agentesList = agentesList;
    }

    public void addAgente(Agentes agentes) {



            this.agentesList.add(agentes);
            agentes.setCargo(this);




    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCargo != null ? idCargo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cargos)) {
            return false;
        }
        Cargos other = (Cargos) object;
        if ((this.idCargo == null && other.idCargo != null) || (this.idCargo != null && !this.idCargo.equals(other.idCargo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.cliente.Cargos[ idCargo=" + idCargo + " ]";
    }

}
