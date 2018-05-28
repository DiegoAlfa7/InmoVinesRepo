/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diegoa.inmovinesrest.entities.inmuebles;

import com.diegoa.inmovinesrest.entities.agentes.Agentes;
import com.diegoa.inmovinesrest.entities.clientes.Clientes;
import com.diegoa.inmovinesrest.entities.inmuebles.localizacion.Localizacion;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Usuario 2 DAM
 */
@Entity
@Table(name = "inmuebles", schema = "inmovinescrm")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Inmuebles.findAll", query = "SELECT i FROM Inmuebles i")
        , @NamedQuery(name = "Inmuebles.findById", query = "SELECT i FROM Inmuebles i WHERE i.id = :id")
        , @NamedQuery(name = "Inmuebles.findByReferenciaCatastral", query = "SELECT i FROM Inmuebles i WHERE i.referenciaCatastral = :referenciaCatastral")
        , @NamedQuery(name = "Inmuebles.findByReferencia", query = "SELECT i FROM Inmuebles i WHERE i.referencia = :referencia")
        , @NamedQuery(name = "Inmuebles.findByTipo", query = "SELECT i FROM Inmuebles i WHERE i.tipos = :tipo")
        , @NamedQuery(name = "Inmuebles.findByDescripcion", query = "SELECT i FROM Inmuebles i WHERE i.descripcion = :descripcion")
        , @NamedQuery(name = "Inmuebles.findByTextoReclamo", query = "SELECT i FROM Inmuebles i WHERE i.textoReclamo = :textoReclamo")
        , @NamedQuery(name = "Inmuebles.findByGastosComunidad", query = "SELECT i FROM Inmuebles i WHERE i.gastosComunidad = :gastosComunidad")
        , @NamedQuery(name = "Inmuebles.findByAlturaEdificio", query = "SELECT i FROM Inmuebles i WHERE i.alturaEdificio = :alturaEdificio")
        , @NamedQuery(name = "Inmuebles.findByPrecioCompra", query = "SELECT i FROM Inmuebles i WHERE i.precioCompra = :precioCompra")
        , @NamedQuery(name = "Inmuebles.findByPrecioAlquiler", query = "SELECT i FROM Inmuebles i WHERE i.precioAlquiler = :precioAlquiler")
        , @NamedQuery(name = "Inmuebles.findByPrecioTraspaso", query = "SELECT i FROM Inmuebles i WHERE i.precioTraspaso = :precioTraspaso")
        , @NamedQuery(name = "Inmuebles.findByPrecioAlquilerOpcionCompra", query = "SELECT i FROM Inmuebles i WHERE i.precioAlquilerOpcionCompra = :precioAlquilerOpcionCompra")
        , @NamedQuery(name = "Inmuebles.findByTipoGestion", query = "SELECT i FROM Inmuebles i WHERE i.gestiones = :gestiones")
})

public class Inmuebles implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "referencia_catastral")
    private String referenciaCatastral;

    @Column(name = "referencia")
    private String referencia;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "texto_reclamo")
    private String textoReclamo;

    @Column(name = "gastos_comunidad")
    private Integer gastosComunidad;

    @Column(name = "altura_edificio")
    private Short alturaEdificio;

    @Column(name = "precio_compra")
    private Double precioCompra;

    @Column(name = "precio_alquiler")
    private Double precioAlquiler;

    @Column(name = "precio_traspaso")
    private Double precioTraspaso;

    @Column(name = "precio_alquiler_opcion_compra")
    private Double precioAlquilerOpcionCompra;

    @OneToMany(mappedBy = "idInmuebleInteres")
    private List<Clientes> clientesList;

    @ManyToOne
    @JoinColumn(name = "id_agente", referencedColumnName = "id")
    private Agentes agente;

    @JoinColumn(name = "id_cliente_propietario", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Clientes idClientePropietario;

    @JoinColumn(name = "tipo", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Tipos tipos;

    @JoinColumn(name = "tipo_gestion", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Gestiones gestiones;

    @Embedded
    private Caracteristicas caracteristicas;

    @Embedded
    private Localizacion localizacion;


    public Inmuebles() {
    }

    public Inmuebles(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferenciaCatastral() {
        return referenciaCatastral;
    }

    public void setReferenciaCatastral(String referenciaCatastral) {
        this.referenciaCatastral = referenciaCatastral;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTextoReclamo() {
        return textoReclamo;
    }

    public void setTextoReclamo(String textoReclamo) {
        this.textoReclamo = textoReclamo;
    }

    public Integer getGastosComunidad() {
        return gastosComunidad;
    }

    public void setGastosComunidad(Integer gastosComunidad) {
        this.gastosComunidad = gastosComunidad;
    }

    public Short getAlturaEdificio() {
        return alturaEdificio;
    }

    public void setAlturaEdificio(Short alturaEdificio) {
        this.alturaEdificio = alturaEdificio;
    }

    public Double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Double getPrecioAlquiler() {
        return precioAlquiler;
    }

    public void setPrecioAlquiler(Double precioAlquiler) {
        this.precioAlquiler = precioAlquiler;
    }

    public Double getPrecioTraspaso() {
        return precioTraspaso;
    }

    public void setPrecioTraspaso(Double precioTraspaso) {
        this.precioTraspaso = precioTraspaso;
    }

    public Double getPrecioAlquilerOpcionCompra() {
        return precioAlquilerOpcionCompra;
    }

    public void setPrecioAlquilerOpcionCompra(Double precioAlquilerOpcionCompra) {
        this.precioAlquilerOpcionCompra = precioAlquilerOpcionCompra;
    }

    public Caracteristicas getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(Caracteristicas caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public Localizacion getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(Localizacion localizacion) {
        this.localizacion = localizacion;
    }

    public Tipos getTipos() {
        return tipos;
    }

    public void setTipos(Tipos tipos) {
        this.tipos = tipos;
    }

    public Gestiones getGestiones() {
        return gestiones;
    }

    public void setGestiones(Gestiones gestiones) {
        this.gestiones = gestiones;
    }

    @XmlTransient
    @JsonIgnore
    public List<Clientes> getClientesList() {
        return clientesList;
    }

    public void setClientesList(List<Clientes> clientesList) {
        this.clientesList = clientesList;
    }

    public void addClienteInteresado(Clientes c) {

        if (this.clientesList != null) {

            this.clientesList.add(c);
            c.setIdInmuebleInteres(this);


        } else {

            this.clientesList = new ArrayList<Clientes>();
            this.addClienteInteresado(c);

        }


    }
    @JsonIgnore
    public Agentes getAgente() {
        return agente;
    }

    public void setAgente(Agentes agente) {
        this.agente = agente;
    }
    @JsonIgnore
    public Clientes getClientePropietario() {
        return idClientePropietario;
    }

    public void setClientePropietario(Clientes idClientePropietario) {
        this.idClientePropietario = idClientePropietario;
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
        if (!(object instanceof Inmuebles)) {
            return false;
        }
        Inmuebles other = (Inmuebles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.cliente.Inmuebles[ id=" + id + " ]";
    }

}
