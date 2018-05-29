/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diegoa.inmovinesrest.entities.localizacion;

import com.diegoa.inmovinesrest.entities.inmuebles.Inmuebles;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Usuario 2 DAM
 */
@Entity
@Table(name = "comunidades", schema = "inmovinescrm")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comunidades.findAll", query = "SELECT c FROM Comunidades c")
    , @NamedQuery(name = "Comunidades.findById", query = "SELECT c FROM Comunidades c WHERE c.id = :id")
    , @NamedQuery(name = "Comunidades.findBySlug", query = "SELECT c FROM Comunidades c WHERE c.slug = :slug")
    , @NamedQuery(name = "Comunidades.findByComunidad", query = "SELECT c FROM Comunidades c WHERE c.comunidad = :comunidad")})
public class Comunidades implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    @Column(name = "slug")
    private String slug;

    @Basic(optional = false)
    @Column(name = "comunidad")
    private String comunidad;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private List<Inmuebles> inmueblesList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comunidad")
    private List<Provincias> provinciasList;




    public Comunidades() {
    }

    public Comunidades(Long id) {
        this.id = id;
    }

    public Comunidades(Long id, String slug, String comunidad) {
        this.id = id;
        this.slug = slug;
        this.comunidad = comunidad;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getComunidad() {
        return comunidad;
    }

    public void setComunidad(String comunidad) {
        this.comunidad = comunidad;
    }

    @XmlTransient
    @JsonIgnore
    public List<Inmuebles> getInmueblesList() {
        return inmueblesList;
    }

    public void setInmueblesList(List<Inmuebles> inmueblesList) {
        this.inmueblesList = inmueblesList;
    }

    @JsonIgnore
    public List<Provincias> getProvinciasList() {
        return provinciasList;
    }

    public void setProvinciasList(List<Provincias> provinciasList) {
        this.provinciasList = provinciasList;
    }

    public void addInmueble(Inmuebles i) {
        this.inmueblesList.add(i);
        i.getLocalizacion().setComunidad(this);
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
        if (!(object instanceof Comunidades)) {
            return false;
        }
        Comunidades other = (Comunidades) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.cliente.Comunidades[ id=" + id + " ]";
    }
    
}
