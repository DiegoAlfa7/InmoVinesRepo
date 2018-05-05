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
 * @author Usuario 2 DAM
 */
@Entity
@Table(catalog = "inmovinescrm", schema = "")
@XmlRootElement
@NamedQueries({
<<<<<<< HEAD
    @NamedQuery(name = "Provincias.findAll", query = "SELECT p FROM Provincias p")
    , @NamedQuery(name = "Provincias.findById", query = "SELECT p FROM Provincias p WHERE p.id = :id")
    , @NamedQuery(name = "Provincias.findBySlug", query = "SELECT p FROM Provincias p WHERE p.slug = :slug")
    , @NamedQuery(name = "Provincias.findByProvincia", query = "SELECT p FROM Provincias p WHERE p.provincia = :provincia")
    , @NamedQuery(name = "Provincias.findByComunidadId", query = "SELECT p FROM Provincias p WHERE p.comunidad = :comunidadId")
    })
=======
        @NamedQuery(name = "Provincias.findAll", query = "SELECT p FROM Provincias p")
        , @NamedQuery(name = "Provincias.findById", query = "SELECT p FROM Provincias p WHERE p.id = :id")
        , @NamedQuery(name = "Provincias.findBySlug", query = "SELECT p FROM Provincias p WHERE p.slug = :slug")
        , @NamedQuery(name = "Provincias.findByProvincia", query = "SELECT p FROM Provincias p WHERE p.provincia = :provincia")
        , @NamedQuery(name = "Provincias.findByComunidadId", query = "SELECT p FROM Provincias p WHERE p.comunidadId = :comunidadId")
        , @NamedQuery(name = "Provincias.findByCapitalId", query = "SELECT p FROM Provincias p WHERE p.capitalId = :capitalId")})
>>>>>>> 500b6a4047dce29aef8ab578857f395d190a9eeb
public class Provincias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Basic(optional = false)
    private String slug;
    @Basic(optional = false)
    private String provincia;
<<<<<<< HEAD

    @ManyToOne
    private Comunidades comunidad;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id_provincia")
=======
    @Basic(optional = false)
    @Column(name = "comunidad_id")
    @ManyToOne
    private int comunidadId;
    @Basic(optional = false)
    @Column(name = "capital_id")
    private int capitalId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProvincia")
>>>>>>> 500b6a4047dce29aef8ab578857f395d190a9eeb
    private List<Intereses> interesesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private List<Inmuebles> inmueblesList;

    public Provincias() {
    }

    public Provincias(Long id) {
        this.id = id;
    }

    public Provincias(Long id, String slug, String provincia, Comunidades comunidad) {
        this.id = id;
        this.slug = slug;
        this.provincia = provincia;
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

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public Comunidades getComunidad() {
        return comunidad;
    }

    public void setComunidad(Comunidades comunidadId) {
        this.comunidad = comunidadId;
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
        if (!(object instanceof Provincias)) {
            return false;
        }
        Provincias other = (Provincias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.cliente.Provincias[ id=" + id + " ]";
    }

}
