package com.diegoa.inmovinesrest.entities.inmuebles.incidencias;

import com.diegoa.inmovinesrest.entities.inmuebles.Inmuebles;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.persistence.*;

/**
 * @author Daniel Arroyo
 * @version 0.0.1
 */

@Entity
@Table(name = "incidencias", schema = "inmovinescrm")
public class Incidencias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "motivo")
    private String motivo;

    @Column(name = "descripcion")
    private String descripcion;


    @Column(name = "estado")
    private String estado;


    @Column(name = "terminada")
    private Boolean terminada;

    @JoinColumn(name = "id_inmueble", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Inmuebles inmueble;

    public Incidencias() {}

    public Incidencias(String motivo, String descripcion, String estado, Boolean terminada, Inmuebles inmueble) {
        this.motivo = motivo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.terminada = terminada;
        this.inmueble = inmueble;
    }

    public Incidencias(Long id) {
        this.id = id;
    }

    public void copyParameters(Incidencias incidencias) {
        this.motivo = incidencias.motivo;
        this.estado = incidencias.estado;
        this.terminada = incidencias.terminada;
        this.descripcion = incidencias.descripcion;
        this.inmueble = incidencias.inmueble;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public Inmuebles getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmuebles inmueble) {
        this.inmueble = inmueble;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    @JsonProperty("terminada")
    public Boolean isTerminada() {
        return terminada;
    }

    public void setTerminada(Boolean terminada) {
        this.terminada = terminada;
    }
}
