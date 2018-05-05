package tests;

import entities.Comunidades;
import entities.Municipios;
import entities.Provincias;
import entities.Zonas;
import entities.agentes.Agentes;
import entities.clientes.Clientes;
import entities.inmuebles.Caracteristicas;
import entities.inmuebles.Direccion;
import entities.inmuebles.Inmuebles;
import entities.inmuebles.Localizacion;
import hibernateUtil.NewHibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Date;

import static org.junit.Assert.assertNotNull;


@DisplayName("Prueba de Inserción de Inmuebles")
public class InmueblesTest {

    static Session session;

    @BeforeAll
    public static void openSesion() {
        session = NewHibernateUtil.getSessionFactory().openSession();
    }

    @Test
    public void insertarInmuebleOK() throws SQLException {

        Date date = new Date();

        Inmuebles inmuebles = new Inmuebles();
        Agentes agentes = new Agentes(Long.valueOf(1));
        Direccion direccion = new Direccion();
        Caracteristicas caracteristicas = new Caracteristicas();
        Localizacion localizacion = new Localizacion();
        Comunidades comunidades = session.get(Comunidades.class,19l);
        Municipios municipios = new Municipios(Long.valueOf(4));
        Provincias provincias = new Provincias(Long.valueOf(3));
        Zonas zonas = new Zonas(Long.valueOf(2));
        //CARACTERISTICAS DE INMUEBLES
        inmuebles.setIdAgente(agentes);
        inmuebles.setReferenciaCatastral("Referencia catastral");
        inmuebles.setReferencia("Referencia");
        inmuebles.setTipo(1);
        inmuebles.setDescripcion("Descripción");
        inmuebles.setTextoReclamo("Texto reclamo");
        inmuebles.setGastosComunidad(100);
        inmuebles.setAlturaEdificio(Short.valueOf("1"));
        inmuebles.setPrecioCompra(42.0);
        inmuebles.setPrecioAlquiler(42.0);
        inmuebles.setPrecioAlquilerOpcionCompra(42.0);
        //LOCALIZACIONES
        localizacion.setComunidad(comunidades);
        localizacion.setProvincia(provincias);
        localizacion.setMunicipio(municipios);
        localizacion.setZona(zonas);
        localizacion.setCp(28012);
        localizacion.setLatitud(12);
        localizacion.setLongitud(12);
        //DIRECCION
        direccion.setDireccionTipoVia("calle");
        direccion.setDireccionCalle("calle falsa");
        direccion.setDireccionNumero(3);
        direccion.setDireccionPiso("Direccion Piso");
        direccion.setDireccionLetra("Direccion Letra");
        direccion.setDireccionEscalera("Direccion Escalera");
        localizacion.setDireccion(direccion);
        inmuebles.setLocalizacion(localizacion);
        //CARACTERISTICAS
        caracteristicas.setnHabitaciones(Short.valueOf("4"));
        caracteristicas.setnBanos(Short.valueOf("2"));
        caracteristicas.setnAseos(Short.valueOf("1"));
        caracteristicas.setM2Utiles(42.0);
        caracteristicas.setM2Construidos(Float.valueOf("42"));
        caracteristicas.setM2Terreno(42.0);
        caracteristicas.setEstadoConservacion(1);
        caracteristicas.setVisible(true);
        caracteristicas.setZonaDeportiva(true);
        caracteristicas.setAmueblado(true);
        caracteristicas.setGaraje(false);
        caracteristicas.setCalefaccion(true);
        caracteristicas.setAireAcondicionado(false);
        caracteristicas.setPiscina(true);
        caracteristicas.setJardin(false);
        caracteristicas.setTrastero(true);
        caracteristicas.setAscensor(true);
        caracteristicas.setTerraza(false);
        caracteristicas.setPisoBanco(false);
        caracteristicas.setVpo(false);
        caracteristicas.setReservado(false);
        caracteristicas.setEficienciaEnergeticaTipo("A");
        caracteristicas.setEficienciaEnergeticaEntramite01(false);
        caracteristicas.setEficienciaEnergeticaFecvalid(date);
        caracteristicas.setEficienciaEnergeticaEmisiones(Float.valueOf(23));
        caracteristicas.setOrientacionSolar("Norte");
        caracteristicas.setSuelos("Suelos");
        caracteristicas.setCarpinteriaExterior("Carpinteria Exterior");
        caracteristicas.setCarpinteriaInterior("Carpinteria Interior");
        inmuebles.setCaracteristicas(caracteristicas);

        Transaction transaction = session.beginTransaction();

        System.out.println("DATOS DE LOS INMUEBLES --> " + inmuebles.toString());
        System.out.println("DATOS DE LAS LOCALIZACIONES -->" + localizacion.toString());
        System.out.println("DATOS DE LAS DIRECCIONES --> " + direccion.toString());
        System.out.println("DATOS DE LAS CARACTERISTICAS --> " + caracteristicas.toString());

        Long i_insertado = (Long) session.save(inmuebles);

        assertNotNull(i_insertado);

        transaction.rollback();

        session.close();

    }

    @Test()
    public void insertarInmuebleKO() {

        Executable insertarInmueble = () -> {

            Inmuebles inmuebles = new Inmuebles();

            inmuebles.setClientePropietario(new Clientes(Long.valueOf(1l)));

            Direccion direccion = new Direccion();

            direccion.setDireccionCalle("C/DE TU PUTA MADRE");
            direccion.setDireccionEscalera("hehe");

            Caracteristicas caracteristicas = new Caracteristicas();

            caracteristicas.setCarpinteriaExterior("to guapa");
            caracteristicas.setEstadoConservacion(1);

            Localizacion localizacion = new Localizacion();
            Comunidades comunidades = new Comunidades(Long.valueOf(19));
            Municipios municipios = new Municipios(Long.valueOf(4));
            Provincias provincias = new Provincias(Long.valueOf(3));

            localizacion.setDireccion(direccion);
            localizacion.setComunidad(comunidades);
            localizacion.setMunicipio(municipios);
            localizacion.setProvincia(provincias);

            inmuebles.setCaracteristicas(caracteristicas);
            inmuebles.setLocalizacion(localizacion);
            inmuebles.setTextoReclamo("insertarInmuebleFailed -> InmueblesTest");
            inmuebles.setDescripcion("Gracias a este edificio nos van a dar un 10 jajajajajjj");
            inmuebles.setTipo(1);

            Transaction transaction;

            if(session.getTransaction()!=null){

                transaction = session.getTransaction();

            }else{

                transaction = session.beginTransaction();

            }
            System.out.println(inmuebles);
            session.save(inmuebles);

            transaction.rollback();
            session.close();


        };

        assertThrows(ConstraintViolationException.class, insertarInmueble);




    }
}
