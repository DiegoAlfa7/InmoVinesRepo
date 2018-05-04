/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.agentes;

import entities.clientes.Clientes;
import entities.inmuebles.Inmuebles;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    @NamedQuery(name = "Agentes.findAll", query = "SELECT a FROM Agentes a")
    , @NamedQuery(name = "Agentes.findById", query = "SELECT a FROM Agentes a WHERE a.id = :id")
    , @NamedQuery(name = "Agentes.findByNombre", query = "SELECT a FROM Agentes a WHERE a.nombre = :nombre")
    , @NamedQuery(name = "Agentes.findByApellidos", query = "SELECT a FROM Agentes a WHERE a.apellidos = :apellidos")
    , @NamedQuery(name = "Agentes.findByMail", query = "SELECT a FROM Agentes a WHERE a.mail = :mail")
    , @NamedQuery(name = "Agentes.findByTlf", query = "SELECT a FROM Agentes a WHERE a.tlf = :tlf")
    , @NamedQuery(name = "Agentes.findByTwitter", query = "SELECT a FROM Agentes a WHERE a.twitter = :twitter")
    , @NamedQuery(name = "Agentes.findByFacebook", query = "SELECT a FROM Agentes a WHERE a.facebook = :facebook")
    , @NamedQuery(name = "Agentes.findByLinkedin", query = "SELECT a FROM Agentes a WHERE a.linkedin = :linkedin")
    , @NamedQuery(name = "Agentes.findByInstagram", query = "SELECT a FROM Agentes a WHERE a.instagram = :instagram")
    , @NamedQuery(name = "Agentes.findByFoto", query = "SELECT a FROM Agentes a WHERE a.foto = :foto")
    , @NamedQuery(name = "Agentes.findByEslogan", query = "SELECT a FROM Agentes a WHERE a.eslogan = :eslogan")
    , @NamedQuery(name = "Agentes.findByCodigoAgente", query = "SELECT a FROM Agentes a WHERE a.codigoAgente = :codigoAgente")
    , @NamedQuery(name = "Agentes.findByActivacion", query = "SELECT a FROM Agentes a WHERE a.activacion = :activacion")
    , @NamedQuery(name = "Agentes.findByPermisos", query = "SELECT a FROM Agentes a WHERE a.permisos = :permisos")
    , @NamedQuery(name = "Agentes.findByPassword", query = "SELECT a FROM Agentes a WHERE a.password = :password")})
public class Agentes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;
    @Basic(optional = false)
    private String nombre;
    private String apellidos;
    private String mail;
    private String tlf;
    private String twitter;
    @Basic(optional = false)
    private String facebook;
    @Basic(optional = false)
    private String linkedin;
    @Basic(optional = false)
    private String instagram;
    private String foto;
    private String eslogan;
    @Column(name = "codigo_agente")
    private String codigoAgente;
    private Boolean activacion;
    private Short permisos;
    private String password;
    @JoinColumn(name = "id_cargo", referencedColumnName = "id_cargo")
    @ManyToOne(optional = false)
    private Cargos idCargo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAgente")
    private List<Clientes> clientesList;
    @OneToMany(mappedBy = "idAgenteEntrada")
    private List<Clientes> clientesList1;
    @OneToMany(mappedBy = "idAgente")
    private List<Inmuebles> inmueblesList;

    public Agentes() {
    }

    public Agentes(Long id) {
        this.id = id;
    }

    public Agentes(Long id, String nombre, String facebook, String linkedin, String instagram) {
        this.id = id;
        this.nombre = nombre;
        this.facebook = facebook;
        this.linkedin = linkedin;
        this.instagram = instagram;
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

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getEslogan() {
        return eslogan;
    }

    public void setEslogan(String eslogan) {
        this.eslogan = eslogan;
    }

    public String getCodigoAgente() {
        return codigoAgente;
    }

    public void setCodigoAgente(String codigoAgente) {
        this.codigoAgente = codigoAgente;
    }

    public Boolean getActivacion() {
        return activacion;
    }

    public void setActivacion(Boolean activacion) {
        this.activacion = activacion;
    }

    public Short getPermisos() {
        return permisos;
    }

    public void setPermisos(Short permisos) {
        this.permisos = permisos;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Cargos getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Cargos idCargo) {
        this.idCargo = idCargo;
    }

    @XmlTransient
    public List<Clientes> getClientesList() {
        return clientesList;
    }

    public void setClientesList(List<Clientes> clientesList) {
        this.clientesList = clientesList;
    }

    @XmlTransient
    public List<Clientes> getClientesList1() {
        return clientesList1;
    }

    public void setClientesList1(List<Clientes> clientesList1) {
        this.clientesList1 = clientesList1;
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
        if (!(object instanceof Agentes)) {
            return false;
        }
        Agentes other = (Agentes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.cliente.Agentes[ id=" + id + " ]";
    }
    
}
