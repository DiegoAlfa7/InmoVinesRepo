/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diegoa.inmovinesrest.entities.clientes;

import com.diegoa.inmovinesrest.entities.agentes.Agentes;
import com.diegoa.inmovinesrest.entities.inmuebles.Inmuebles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @JoinColumn(name = "id_agente", referencedColumnName = "id")
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Agentes agente;
    @JoinColumn(name = "id_agente_entrada", referencedColumnName = "id")
    @ManyToOne
    private Agentes agenteEntrada;
    @JoinColumn(name = "id_inmueble_interes", referencedColumnName = "ID")
    @ManyToOne
    private Inmuebles inmuebleInteres;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCliente")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private List<Intereses> interesesList;
    @com.fasterxml.jackson.annotation.JsonIgnore
    @OneToMany(mappedBy = "clientePropietario")
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

    public void copyParameters(Clientes clientes) {
        this.datosPersonales = clientes.datosPersonales;
        this.inquilino = clientes.inquilino;
        this.arrendatario = clientes.arrendatario;
        this.comprador = clientes.comprador;
        this.vendedor = clientes.vendedor;
        this.comentarios = clientes.comentarios;
        this.fechaEntrada = clientes.fechaEntrada;
        this.presupuestoMin = clientes.presupuestoMin;
        this.presupuestoMax = clientes.presupuestoMax;
        this.canalEntrada = clientes.canalEntrada;
        this.accountHash = clientes.accountHash;
        this.agente = clientes.agente;
        this.agenteEntrada = clientes.agenteEntrada;
        this.inmuebleInteres = clientes.inmuebleInteres;
        this.interesesList = clientes.interesesList;
        this.inmueblesList = clientes.inmueblesList;
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

    @JsonProperty("idAgente")
    public long getIdAgente() {
        return this.agente.getId();
    }

    @JsonIgnore
    public Agentes getAgente() {
        return agente;
    }

    public void setAgente(Agentes idAgente) {

        this.agente = idAgente;
    }

    @JsonProperty("idAgenteEntrada")
    public long getIdAgenteEntrada() {
        return this.agenteEntrada.getId();
    }

    @JsonIgnore
    public Agentes getAgenteEntrada() {
        return agenteEntrada;
    }

    public void setAgenteEntrada(Agentes agenteEntrada) {

        this.agenteEntrada = agenteEntrada;

    }

    @JsonProperty("idInmuebleInteres")
    public long getIdInmuebleInteres() {
        return this.inmuebleInteres.getId();
    }

    @JsonIgnore
    public Inmuebles getInmuebleInteres() {
        return inmuebleInteres;
    }

    public void setInmuebleInteres(Inmuebles inmuebleInteres) {
        this.inmuebleInteres = inmuebleInteres;
    }

    @XmlTransient
    @JsonIgnore
    public List<Intereses> getInteresesList() {
        return interesesList;
    }

    public void setInteresesList(List<Intereses> interesesList) {
        this.interesesList = interesesList;
    }

    public void addInteresesList(Intereses intereses) {


        if (this.interesesList != null) {


            this.interesesList.add(intereses);
            intereses.setIdCliente(this);

        } else {

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
    @JsonIgnore
    public List<Inmuebles> getInmueblesList() {
        return inmueblesList;
    }

    public void setInmueblesList(List<Inmuebles> inmueblesList) {
        this.inmueblesList = inmueblesList;
    }

    public void addInmueble(Inmuebles inmuebles) {

        if (this.inmueblesList != null) {

            this.inmueblesList.add(inmuebles);
            inmuebles.setClientePropietario(this);

        } else {

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
