package com.diegoa.inmovinesrest.entities.inmuebles.incidencias;

import com.diegoa.inmovinesrest.entities.inmuebles.Inmuebles;

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

    private String motivo;

    private String descripcion;

    @JoinColumn(name = "id_inmueble", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Inmuebles idInmueble;

    public Incidencias(String motivo) {
        this.motivo = motivo;
    }

    public Incidencias(Long id) {
        this.id = id;
    }

    public void copyParameters(Incidencias incidencias) {
        this.motivo = incidencias.motivo;
        this.descripcion = incidencias.descripcion;
        this.idInmueble = incidencias.idInmueble;
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


    public Inmuebles getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(Inmuebles idInmueble) {
        this.idInmueble = idInmueble;
    }
}
