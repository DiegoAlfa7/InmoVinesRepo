/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diegoa.inmovinesrest.entities.localizacion;

import com.diegoa.inmovinesrest.entities.inmuebles.Inmuebles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

/**
 * @author Usuario 2 DAM
 */
@Entity
@Table(name = "zonas", schema = "inmovinescrm")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Zonas.findAll", query = "SELECT z FROM Zonas z")
        , @NamedQuery(name = "Zonas.findById", query = "SELECT z FROM Zonas z WHERE z.id = :id")
        , @NamedQuery(name = "Zonas.findByNombre", query = "SELECT z FROM Zonas z WHERE z.nombre = :nombre")
        , @NamedQuery(name = "Zonas.findByActiva", query = "SELECT z FROM Zonas z WHERE z.activa = :activa")
        , @NamedQuery(name = "Zonas.findByNombreAdmin", query = "SELECT z FROM Zonas z WHERE z.nombreAdmin = :nombreAdmin")})
public class Zonas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "activa")
    @Basic(optional = false)
    private boolean activa;
    @Column(name = "nombre_admin")
    private String nombreAdmin;
    @JoinColumn(name = "id_municipio", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Municipios municipio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private List<Inmuebles> inmueblesList;

    public Zonas() {
    }

    public Zonas(Long id) {
        this.id = id;
    }

    public Zonas(Long id, boolean activa) {
        this.id = id;
        this.activa = activa;
    }

    public void copyParameters(Zonas zonas) {
        this.nombre = zonas.nombre;
        this.activa = zonas.activa;
        this.nombreAdmin = zonas.nombreAdmin;
        this.municipio =zonas.municipio;
        this.inmueblesList = zonas.inmueblesList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public String getNombreAdmin() {
        return nombreAdmin;
    }

    public void setNombreAdmin(String nombreAdmin) {
        this.nombreAdmin = nombreAdmin;
    }

    @JsonProperty("idComunidad")
    public long getIdComunidad(){
        return this.getMunicipio().getProvincia().getComunidad().getId();
    }
    @JsonProperty("idProvincia")
    public long getIdProvincia(){
        return this.getMunicipio().getProvincia().getId();
    }
    @JsonProperty("idMunicipio")
    public long getIdMunicipio(){
        return this.getMunicipio().getId();
    }
    @JsonIgnore
    public Municipios getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipios idMunicipio) {
        this.municipio = idMunicipio;
    }

    @XmlTransient
    @JsonIgnore
    public List<Inmuebles> getInmueblesList() {
        return inmueblesList;
    }

    public void setInmueblesList(List<Inmuebles> inmueblesList) {
        this.inmueblesList = inmueblesList;
    }

    public void addInmueble(Inmuebles i) {


        this.inmueblesList.add(i);
        i.getLocalizacion().setComunidad(this.getMunicipio().getProvincia().getComunidad());
        i.getLocalizacion().setProvincia(this.getMunicipio().getProvincia());
        i.getLocalizacion().setMunicipio(this.getMunicipio());
        i.getLocalizacion().setZona(this);


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
        if (!(object instanceof Zonas)) {
            return false;
        }
        Zonas other = (Zonas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.cliente.Zonas[ id=" + id + " ]";
    }

}
