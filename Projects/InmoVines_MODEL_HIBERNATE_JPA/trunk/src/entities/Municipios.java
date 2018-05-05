/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.clientes.Intereses;
import entities.inmuebles.Inmuebles;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Usuario 2 DAM
 */
@Entity
@Table(catalog = "inmovinescrm", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Municipios.findAll", query = "SELECT m FROM Municipios m")
    , @NamedQuery(name = "Municipios.findById", query = "SELECT m FROM Municipios m WHERE m.id = :id")
    , @NamedQuery(name = "Municipios.findByIdProvincia", query = "SELECT m FROM Municipios m WHERE m.provincia = :idProvincia")
    , @NamedQuery(name = "Municipios.findByMunicipio", query = "SELECT m FROM Municipios m WHERE m.municipio = :municipio")
    , @NamedQuery(name = "Municipios.findBySlug", query = "SELECT m FROM Municipios m WHERE m.slug = :slug")
    , @NamedQuery(name = "Municipios.findByLatitud", query = "SELECT m FROM Municipios m WHERE m.latitud = :latitud")
    , @NamedQuery(name = "Municipios.findByLongitud", query = "SELECT m FROM Municipios m WHERE m.longitud = :longitud")})
public class Municipios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;


    @ManyToOne
    private Provincias provincia;

    @ManyToOne
    @JoinColumn(name = "id_provincia", referencedColumnName = "id_provincia")
    private Provincias idProvincia;

    @Basic(optional = false)
    private String municipio;
    @Basic(optional = false)
    private String slug;

    private Double latitud;
    private Double longitud;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id_municipio")
    private List<Zonas> zonasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id_municipio")
    private List<Intereses> interesesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private List<Inmuebles> inmueblesList;

    public Municipios() {

    }

    public Municipios(Long id) {
        this.id = id;
    }



    public Municipios(Long id, Provincias idProvincia, String municipio, String slug) {

        this.id = id;
        this.provincia = provincia;
        this.municipio = municipio;
        this.slug = slug;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Provincias getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincias idProvincia) {
        this.provincia = idProvincia;
    }

    public Provincias getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Provincias idProvincia) {
        this.idProvincia = idProvincia;

    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    @XmlTransient
    public List<Zonas> getZonasList() {
        return zonasList;
    }

    public void setZonasList(List<Zonas> zonasList) {
        this.zonasList = zonasList;
    }

    @XmlTransient
    public List<Intereses> getInteresesList() {
        return interesesList;
    }

    public void setInteresesList(List<Intereses> interesesList) {
        this.interesesList = interesesList;
    }

    @XmlTransient
    public List<Inmuebles> getInmueblesList() {
        return inmueblesList;
    }

    public void setInmueblesList(List<Inmuebles> inmueblesList) {
        this.inmueblesList = inmueblesList;
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
        if (!(object instanceof Municipios)) {
            return false;
        }
        Municipios other = (Municipios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.cliente.Municipios[ id=" + id + " ]";
    }
    
}
