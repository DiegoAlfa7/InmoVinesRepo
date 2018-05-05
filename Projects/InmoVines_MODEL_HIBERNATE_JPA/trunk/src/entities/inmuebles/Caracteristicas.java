/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.inmuebles;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * @author Usuario 2 DAM
 */
@Embeddable
public class Caracteristicas {
    
    /************************************CARACTERÍSTICAS A PARTE**************************************/

    @Column(name = "n_habitaciones")
    private Short nHabitaciones;
    @Column(name = "n_banos")
    private Short nBanos;
    @Column(name = "n_aseos")
    private Short nAseos;
    @Column(name = "m2_utiles")
    private Double m2Utiles;
    @Column(name = "m2_construidos")
    private Float m2Construidos;
    @Column(name = "m2_terreno")
    private Double m2Terreno;
    @Basic(optional = false)
    @Column(name = "estado_conservacion")
    private int estadoConservacion;
    @Column(name = "visible")
    private Boolean visible;
    @Column(name = "zona_deportiva")
    private Boolean zonaDeportiva;
    @Column(name = "amueblado")
    private Boolean amueblado;
    @Column(name = "garaje")
    private Boolean garaje;
    @Column(name = "calefaccion")
    private Boolean calefaccion;
    @Column(name = "aire_acondicionado")
    private Boolean aireAcondicionado;
    @Column(name = "piscina")
    private Boolean piscina;
    @Column(name = "jardin")
    private Boolean jardin;
    @Column(name = "trastero")
    private Boolean trastero;
    @Column(name = "ascensor")
    private Boolean ascensor;
    @Column(name = "terraza")
    private Boolean terraza;
    @Column(name = "piso_banco")
    private Boolean pisoBanco;
    @Column(name = "vpo")
    private Boolean vpo;
    @Column(name = "reservado")
    private Boolean reservado;
    @Column(name = "eficiencia_energetica_tipo")
    private String eficienciaEnergeticaTipo;
    @Basic(optional = false)
    @Column(name = "eficiencia_energetica_entramite_01")
    private boolean eficienciaEnergeticaEntramite01;
    @Column(name = "eficiencia_energetica_fecvalid")
    @Temporal(TemporalType.DATE)
    private Date eficienciaEnergeticaFecvalid;
    @Column(name = "eficiencia_energetica_emisiones")
    private Float eficienciaEnergeticaEmisiones;
    @Column(name = "orientacion_solar")
    private String orientacionSolar;
    @Column(name = "suelos")
    private String suelos;
    @Column(name = "carpinteria_exterior")
    private String carpinteriaExterior;
    @Column(name = "carpinteria_interior")
    private String carpinteriaInterior;

    public Caracteristicas() {
    }

    public Caracteristicas(Short nHabitaciones, Short nBanos, Short nAseos, Double m2Utiles, Float m2Construidos, Double m2Terreno, int estadoConservacion, Boolean visible, Boolean zonaDeportiva, Boolean amueblado, Boolean garaje, Boolean calefaccion, Boolean aireAcondicionado, Boolean piscina, Boolean jardin, Boolean trastero, Boolean ascensor, Boolean terraza, Boolean pisoBanco, Boolean vpo, Boolean reservado, String eficienciaEnergeticaTipo, boolean eficienciaEnergeticaEntramite01, Date eficienciaEnergeticaFecvalid, Float eficienciaEnergeticaEmisiones, String orientacionSolar, String suelos, String carpinteriaExterior, String carpinteriaInterior) {
        this.nHabitaciones = nHabitaciones;
        this.nBanos = nBanos;
        this.nAseos = nAseos;
        this.m2Utiles = m2Utiles;
        this.m2Construidos = m2Construidos;
        this.m2Terreno = m2Terreno;
        this.estadoConservacion = estadoConservacion;
        this.visible = visible;
        this.zonaDeportiva = zonaDeportiva;
        this.amueblado = amueblado;
        this.garaje = garaje;
        this.calefaccion = calefaccion;
        this.aireAcondicionado = aireAcondicionado;
        this.piscina = piscina;
        this.jardin = jardin;
        this.trastero = trastero;
        this.ascensor = ascensor;
        this.terraza = terraza;
        this.pisoBanco = pisoBanco;
        this.vpo = vpo;
        this.reservado = reservado;
        this.eficienciaEnergeticaTipo = eficienciaEnergeticaTipo;
        this.eficienciaEnergeticaEntramite01 = eficienciaEnergeticaEntramite01;
        this.eficienciaEnergeticaFecvalid = eficienciaEnergeticaFecvalid;
        this.eficienciaEnergeticaEmisiones = eficienciaEnergeticaEmisiones;
        this.orientacionSolar = orientacionSolar;
        this.suelos = suelos;
        this.carpinteriaExterior = carpinteriaExterior;
        this.carpinteriaInterior = carpinteriaInterior;
    }

    public Short getnHabitaciones() {
        return nHabitaciones;
    }

    public void setnHabitaciones(Short nHabitaciones) {
        this.nHabitaciones = nHabitaciones;
    }

    public Short getnBanos() {
        return nBanos;
    }

    public void setnBanos(Short nBanos) {
        this.nBanos = nBanos;
    }

    public Short getnAseos() {
        return nAseos;
    }

    public void setnAseos(Short nAseos) {
        this.nAseos = nAseos;
    }

    public Double getM2Utiles() {
        return m2Utiles;
    }

    public void setM2Utiles(Double m2Utiles) {
        this.m2Utiles = m2Utiles;
    }

    public Float getM2Construidos() {
        return m2Construidos;
    }

    public void setM2Construidos(Float m2Construidos) {
        this.m2Construidos = m2Construidos;
    }

    public Double getM2Terreno() {
        return m2Terreno;
    }

    public void setM2Terreno(Double m2Terreno) {
        this.m2Terreno = m2Terreno;
    }

    public int getEstadoConservacion() {
        return estadoConservacion;
    }

    public void setEstadoConservacion(int estadoConservacion) {
        this.estadoConservacion = estadoConservacion;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Boolean getZonaDeportiva() {
        return zonaDeportiva;
    }

    public void setZonaDeportiva(Boolean zonaDeportiva) {
        this.zonaDeportiva = zonaDeportiva;
    }

    public Boolean getAmueblado() {
        return amueblado;
    }

    public void setAmueblado(Boolean amueblado) {
        this.amueblado = amueblado;
    }

    public Boolean getGaraje() {
        return garaje;
    }

    public void setGaraje(Boolean garaje) {
        this.garaje = garaje;
    }

    public Boolean getCalefaccion() {
        return calefaccion;
    }

    public void setCalefaccion(Boolean calefaccion) {
        this.calefaccion = calefaccion;
    }

    public Boolean getAireAcondicionado() {
        return aireAcondicionado;
    }

    public void setAireAcondicionado(Boolean aireAcondicionado) {
        this.aireAcondicionado = aireAcondicionado;
    }

    public Boolean getPiscina() {
        return piscina;
    }

    public void setPiscina(Boolean piscina) {
        this.piscina = piscina;
    }

    public Boolean getJardin() {
        return jardin;
    }

    public void setJardin(Boolean jardin) {
        this.jardin = jardin;
    }

    public Boolean getTrastero() {
        return trastero;
    }

    public void setTrastero(Boolean trastero) {
        this.trastero = trastero;
    }

    public Boolean getAscensor() {
        return ascensor;
    }

    public void setAscensor(Boolean ascensor) {
        this.ascensor = ascensor;
    }

    public Boolean getTerraza() {
        return terraza;
    }

    public void setTerraza(Boolean terraza) {
        this.terraza = terraza;
    }

    public Boolean getPisoBanco() {
        return pisoBanco;
    }

    public void setPisoBanco(Boolean pisoBanco) {
        this.pisoBanco = pisoBanco;
    }

    public Boolean getVpo() {
        return vpo;
    }

    public void setVpo(Boolean vpo) {
        this.vpo = vpo;
    }

    public Boolean getReservado() {
        return reservado;
    }

    public void setReservado(Boolean reservado) {
        this.reservado = reservado;
    }

    public String getEficienciaEnergeticaTipo() {
        return eficienciaEnergeticaTipo;
    }

    public void setEficienciaEnergeticaTipo(String eficienciaEnergeticaTipo) {
        this.eficienciaEnergeticaTipo = eficienciaEnergeticaTipo;
    }

    public boolean isEficienciaEnergeticaEntramite01() {
        return eficienciaEnergeticaEntramite01;
    }

    public void setEficienciaEnergeticaEntramite01(boolean eficienciaEnergeticaEntramite01) {
        this.eficienciaEnergeticaEntramite01 = eficienciaEnergeticaEntramite01;
    }

    public Date getEficienciaEnergeticaFecvalid() {
        return eficienciaEnergeticaFecvalid;
    }

    public void setEficienciaEnergeticaFecvalid(Date eficienciaEnergeticaFecvalid) {
        this.eficienciaEnergeticaFecvalid = eficienciaEnergeticaFecvalid;
    }

    public Float getEficienciaEnergeticaEmisiones() {
        return eficienciaEnergeticaEmisiones;
    }

    public void setEficienciaEnergeticaEmisiones(Float eficienciaEnergeticaEmisiones) {
        this.eficienciaEnergeticaEmisiones = eficienciaEnergeticaEmisiones;
    }

    public String getOrientacionSolar() {
        return orientacionSolar;
    }

    public void setOrientacionSolar(String orientacionSolar) {
        this.orientacionSolar = orientacionSolar;
    }

    public String getSuelos() {
        return suelos;
    }

    public void setSuelos(String suelos) {
        this.suelos = suelos;
    }

    public String getCarpinteriaExterior() {
        return carpinteriaExterior;
    }

    public void setCarpinteriaExterior(String carpinteriaExterior) {
        this.carpinteriaExterior = carpinteriaExterior;
    }

    public String getCarpinteriaInterior() {
        return carpinteriaInterior;
    }

    public void setCarpinteriaInterior(String carpinteriaInterior) {
        this.carpinteriaInterior = carpinteriaInterior;
    }

}
