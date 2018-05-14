package com.diegoa.inmovinesrest.entities.agentes;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "tareas", schema = "inmovinescrm")
public class Tareas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_agente", referencedColumnName = "id")
    private Agentes agente;

    private String concepto;
    private String descripcion;
    private Date fecha;
    private Byte completada;
    @Column(name = "fecha_completada")
    private Date fechaCompletada;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Agentes getAgente() {
        return agente;
    }

    public void setAgente(Agentes agente) {
        this.agente = agente;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Byte getCompletada() {
        return completada;
    }

    public void setCompletada(Byte completada) {
        this.completada = completada;
    }

    public Date getFechaCompletada() {
        return fechaCompletada;
    }

    public void setFechaCompletada(Date fechaCompletada) {
        this.fechaCompletada = fechaCompletada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tareas that = (Tareas) o;
        return id == that.id &&
                Objects.equals(concepto, that.concepto) &&
                Objects.equals(descripcion, that.descripcion) &&
                Objects.equals(fecha, that.fecha) &&
                Objects.equals(completada, that.completada) &&
                Objects.equals(fechaCompletada, that.fechaCompletada);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, concepto, descripcion, fecha, completada, fechaCompletada);
    }
}
