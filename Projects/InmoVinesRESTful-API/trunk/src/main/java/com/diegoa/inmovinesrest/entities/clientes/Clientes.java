/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diegoa.inmovinesrest.entities.clientes;

import com.diegoa.inmovinesrest.entities.agentes.Agentes;
import com.diegoa.inmovinesrest.entities.inmuebles.Inmuebles;
import com.diegoa.inmovinesrest.entities.roles.RolesUsuarios;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Usuario 2 DAM
 */
@Entity
@Table(name = "clientes", schema = "inmovinescrm")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Clientes.findAll", query = "SELECT c FROM Clientes c")
        , @NamedQuery(name = "Clientes.findById", query = "SELECT c FROM Clientes c WHERE c.id = :id")
        , @NamedQuery(name = "Clientes.findByInquilino", query = "SELECT c FROM Clientes c WHERE c.inquilino = :inquilino")
        , @NamedQuery(name = "Clientes.findByArrendatario", query = "SELECT c FROM Clientes c WHERE c.arrendatario = :arrendatario")
        , @NamedQuery(name = "Clientes.findByComprador", query = "SELECT c FROM Clientes c WHERE c.comprador = :comprador")
        , @NamedQuery(name = "Clientes.findByVendedor", query = "SELECT c FROM Clientes c WHERE c.vendedor = :vendedor")
        , @NamedQuery(name = "Clientes.findByFechaEntrada", query = "SELECT c FROM Clientes c WHERE c.fechaEntrada = :fechaEntrada")
        , @NamedQuery(name = "Clientes.findByPresupuestoMin", query = "SELECT c FROM Clientes c WHERE c.presupuestoMin = :presupuestoMin")
        , @NamedQuery(name = "Clientes.findByPresupuestoMax", query = "SELECT c FROM Clientes c WHERE c.presupuestoMax = :presupuestoMax")
        , @NamedQuery(name = "Clientes.findByCanalEntrada", query = "SELECT c FROM Clientes c WHERE c.canalEntrada = :canalEntrada")})

public class Clientes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Embedded
    private DatosPersonales datosPersonales;
    @Basic(optional = false)
    private boolean inquilino;
    @Basic(optional = false)
    private boolean arrendatario;
    @Basic(optional = false)
    private boolean comprador;
    @Basic(optional = false)
    private boolean vendedor;
    @Lob
    private String comentarios;
    @Column(name = "fecha_entrada")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrada;
    @Column(name = "presupuesto_min")
    private BigInteger presupuestoMin;
    @Column(name = "presupuesto_max")
    private BigInteger presupuestoMax;
    @Column(name = "canal_entrada")
    private Short canalEntrada;

    @Column(name = "account_hash")
    @Basic(optional = false)
    private String accountHash;

    @JoinColumn(name = "id_rol", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private RolesUsuarios rol;

    @JoinColumn(name = "id_agente", referencedColumnName = "id")
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Agentes agente;
    @JoinColumn(name = "id_agente_entrada", referencedColumnName = "id")
    @ManyToOne
    private Agentes agenteEntrada;
    @JoinColumn(name = "id_inmueble_interes", referencedColumnName = "ID")
    @ManyToOne
    private Inmuebles idInmuebleInteres;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCliente")
    private List<Intereses> interesesList;
    @OneToMany(mappedBy = "idClientePropietario")
    private List<Inmuebles> inmueblesList;

    public Clientes() {
    }

    public Clientes(Long id) {
        this.id = id;
    }

    public Clientes(Long id, boolean inquilino, boolean arrendatario, boolean comprador, boolean vendedor) {
        this.id = id;
        this.inquilino = inquilino;
        this.arrendatario = arrendatario;
        this.comprador = comprador;
        this.vendedor = vendedor;
    }

    public Long getId() {
        return id;
    }

    public DatosPersonales getDatosPersonales() {
        return datosPersonales;
    }

    public void setDatosPersonales(DatosPersonales datosPersonales) {
        this.datosPersonales = datosPersonales;
    }

    public boolean isInquilino() {
        return inquilino;
    }

    public boolean isArrendatario() {
        return arrendatario;
    }

    public boolean isComprador() {
        return comprador;
    }

    public boolean isVendedor() {
        return vendedor;
    }

    public boolean getInquilino() {
        return inquilino;
    }

    public void setInquilino(boolean inquilino) {
        this.inquilino = inquilino;
    }

    public boolean getArrendatario() {
        return arrendatario;
    }

    public void setArrendatario(boolean arrendatario) {
        this.arrendatario = arrendatario;
    }

    public boolean getComprador() {
        return comprador;
    }

    public void setComprador(boolean comprador) {
        this.comprador = comprador;
    }

    public boolean getVendedor() {
        return vendedor;
    }

    public void setVendedor(boolean vendedor) {
        this.vendedor = vendedor;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public BigInteger getPresupuestoMin() {
        return presupuestoMin;
    }

    public void setPresupuestoMin(BigInteger presupuestoMin) {
        this.presupuestoMin = presupuestoMin;
    }

    public BigInteger getPresupuestoMax() {
        return presupuestoMax;
    }

    public void setPresupuestoMax(BigInteger presupuestoMax) {
        this.presupuestoMax = presupuestoMax;
    }

    public Short getCanalEntrada() {
        return canalEntrada;
    }

    public void setCanalEntrada(Short canalEntrada) {
        this.canalEntrada = canalEntrada;
    }

    public Agentes getAgente() {
        return agente;
    }

    public void setAgente(Agentes idAgente) {

       this.agente = idAgente;
    }

    public Agentes getAgenteEntrada() {
        return agenteEntrada;
    }

    public void setAgenteEntrada(Agentes agenteEntrada) {

        this.agenteEntrada = agenteEntrada;

    }


    public RolesUsuarios getRol() {
        return rol;
    }

    public void setRol(RolesUsuarios rol) {
        this.rol = rol;
    }

    public Inmuebles getIdInmuebleInteres() {
        return idInmuebleInteres;
    }

    public void setIdInmuebleInteres(Inmuebles idInmuebleInteres) {
        this.idInmuebleInteres = idInmuebleInteres;
    }

    @XmlTransient
    public List<Intereses> getInteresesList() {
        return interesesList;
    }

    public void setInteresesList(List<Intereses> interesesList) {
        this.interesesList = interesesList;
    }

    public void addInteresesList(Intereses intereses) {


        if(this.interesesList!=null) {


            this.interesesList.add(intereses);
            intereses.setIdCliente(this);

        }else{

            this.interesesList = new ArrayList<Intereses>();
            addInteresesList(intereses);


        }



    }


    public String getAccountHash() {
        return accountHash;
    }

    public void setAccountHash(String accountHash) {
        this.accountHash = accountHash;
    }

    @XmlTransient
    public List<Inmuebles> getInmueblesList() {
        return inmueblesList;
    }

    public void setInmueblesList(List<Inmuebles> inmueblesList) {
        this.inmueblesList = inmueblesList;
    }

    public void addInmueble(Inmuebles inmuebles) {

            if(this.inmueblesList != null) {

                this.inmueblesList.add(inmuebles);
                inmuebles.setClientePropietario(this);

            }else{

                this.inmueblesList = new ArrayList<Inmuebles>();
                addInmueble(inmuebles);

            }

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
        if (!(object instanceof Clientes)) {
            return false;
        }
        Clientes other = (Clientes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.cliente.Clientes[ id=" + id + " ]";
    }

}
