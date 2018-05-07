package tests;

import entities.Comunidades;
import entities.Municipios;
import entities.Provincias;
import entities.Zonas;
import entities.agentes.Agentes;
import entities.agentes.Cargos;
import entities.clientes.Clientes;
import entities.clientes.DatosPersonales;
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

    /**
     * This inmueble is a dummy entity for testing purposes
     */
    static Inmuebles inmueblePrueba;

    /**
<<<<<<< HEAD
     * This test should be all ok.
     * It inserts an Inmueble instance to the database with all its necessary relationships
     * ClientePropietario, AgenteCargo, Localización (Comunidad, Provincia, Municipio, Zona).
     * When test finishes, transaction is rolled back so no rubbish data is published
     *
     * @throws SQLException
=======
     * This method opens an hibernate session within database and instantiates an entity of Inmuebles
>>>>>>> 376f0847afcd6042ed2d5779d616bac0ac63ed02
     */
    @Test
    public void insertarInmuebleOK() throws SQLException {
    @BeforeAll
    public static void setUp() {
        session = NewHibernateUtil.getSessionFactory().openSession();

        Date date = new Date();
        Inmuebles inmuebles = new Inmuebles();
        Agentes agentes = new Agentes(Long.valueOf(1));
        agentes.setNombre("Diego");
        inmueblePrueba = new Inmuebles();

        Agentes agente = new Agentes();
        agente.setNombre("Agente de Prueba");
        agente.setFacebook("FacebookAgentePrueba");
        agente.setTwitter("TwitterAgentePrueba");
        agente.setLinkedin("LinkedInAgentePrueba");
        agente.setInstagram("InstagramAgentePrueba");
        agente.setCargo(new Cargos(1l));

<<<<<<< HEAD
        Comunidades comunidades = session.get(Comunidades.class, 1l);
=======
        Clientes cliente = new Clientes();
        DatosPersonales pdata = new DatosPersonales();
        cliente.setDatosPersonales(pdata);
        cliente.getDatosPersonales().setNombre("Cliente de Prueba");
        cliente.setIdAgenteEntrada(agente);
        cliente.setIdAgente(agente);
        inmueblePrueba.setClientePropietario(cliente);
        inmueblePrueba.setAgente(agente);


        Comunidades comunidades = session.get(Comunidades.class, Long.valueOf(1));
>>>>>>> 376f0847afcd6042ed2d5779d616bac0ac63ed02

        Provincias provincia = comunidades.getProvinciasList().get(0);

        Municipios municipio = provincia.getCapitalId();

        Zonas zona = municipio.getZonasList().get(0);



        //CARACTERISTICAS DE INMUEBLES
        inmuebles.setAgente(agentes);
        inmuebles.setReferenciaCatastral("Referencia catastral");
        inmuebles.setReferencia("Referencia");
        inmuebles.setTipo(1);
        inmuebles.setDescripcion("Descripción");
        inmuebles.setTextoReclamo("Texto reclamo");
        inmuebles.setGastosComunidad(100);
        inmuebles.setAlturaEdificio(Short.valueOf("1"));
        inmuebles.setPrecioCompra(4200000.0);
        inmuebles.setPrecioAlquiler(420.0);
        inmuebles.setPrecioAlquilerOpcionCompra(500.0);

        inmueblePrueba.setReferenciaCatastral("Referencia catastral");
        inmueblePrueba.setReferencia("Referencia");
        inmueblePrueba.setTipo(1);
        inmueblePrueba.setDescripcion("Descripción");
        inmueblePrueba.setTextoReclamo("Texto reclamo");
        inmueblePrueba.setGastosComunidad(100);
        inmueblePrueba.setAlturaEdificio(Short.valueOf("1"));
        inmueblePrueba.setPrecioCompra(4200000.0);
        inmueblePrueba.setPrecioAlquiler(420.0);
        inmueblePrueba.setPrecioAlquilerOpcionCompra(500.0);
        //LOCALIZACIONES
        Localizacion localizacion = new Localizacion();
        localizacion.setComunidad(comunidades);
        localizacion.setProvincia(provincia);
        localizacion.setMunicipio(municipio);
        localizacion.setZona(zona);

        localizacion.setCp(47879);
        localizacion.setLatitud(municipio.getLatitud());
        localizacion.setLongitud(municipio.getLongitud());

        //DIRECCION
        Direccion direccion = new Direccion();
        direccion.setDireccionTipoVia("calle");
        direccion.setDireccionCalle("DE TU PUTA MADRE");
        direccion.setDireccionNumero(3);
        direccion.setDireccionPiso("Direccion Piso");
        direccion.setDireccionLetra("Direccion Letra");
        direccion.setDireccionEscalera("Direccion Escalera");
        localizacion.setDireccion(direccion);
        inmuebles.setLocalizacion(localizacion);
        inmueblePrueba.setLocalizacion(localizacion);


        //CARACTERISTICAS
        Caracteristicas caracteristicas = new Caracteristicas();
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

        Date date = new Date();

        caracteristicas.setEficienciaEnergeticaFecvalid(date);
        caracteristicas.setEficienciaEnergeticaEmisiones(Float.valueOf(23));
        caracteristicas.setOrientacionSolar("Norte");
        caracteristicas.setSuelos("Suelos");
        caracteristicas.setCarpinteriaExterior("Carpinteria Exterior");
        caracteristicas.setCarpinteriaInterior("Carpinteria Interior");
        inmuebles.setCaracteristicas(caracteristicas);
        inmueblePrueba.setCaracteristicas(caracteristicas);

        Transaction transaction = session.beginTransaction();

<<<<<<< HEAD
        System.out.println("DATOS DE LOS INMUEBLES --> " + inmuebles.toString());
        System.out.println("DATOS DE LAS LOCALIZACIONES -->" + localizacion.toString());
        System.out.println("DATOS DE LAS DIRECCIONES --> " + direccion.toString());
        System.out.println("DATOS DE LAS CARACTERISTICAS --> " + caracteristicas.toString());
        Long i_insertado = (Long) session.save(inmuebles);
=======
    }


    /**
     * This test should be all ok.
     * It inserts an Inmueble instance to the database with all its necessary relationships
     * ClientePropietario, AgenteCargo, Localización (Comunidad, Provincia, Municipio, Zona).
     * When test finishes, transaction is rolled back so no rubbish data is published
     *
     * @throws SQLException
     */
    @Test
    public void insertarInmuebleOK() throws SQLException {


        Transaction transaction = session.beginTransaction();

        Long i_insertado = (Long) session.save(inmueblePrueba);
>>>>>>> 376f0847afcd6042ed2d5779d616bac0ac63ed02


        //TEST ASSERTS


        assertNotNull(i_insertado);
<<<<<<< HEAD
        assertEquals(session.get(Inmuebles.class, i_insertado), inmuebles);
        assertEquals(comunidades, inmuebles.getLocalizacion().getComunidad());
        assertEquals(provincia, inmuebles.getLocalizacion().getProvincia());
        assertEquals(municipio, inmuebles.getLocalizacion().getPoblacion());
        assertEquals(zona, inmuebles.getLocalizacion().getZona());
        assertEquals("Zona Melilla Prueba", inmuebles.getLocalizacion().getZona().getNombre());
        assertEquals((Double) 4200000.0, (Double) inmuebles.getPrecioCompra());
=======
        assertEquals( inmueblePrueba, session.get(Inmuebles.class, i_insertado));
        assertEquals(inmueblePrueba.getLocalizacion().getComunidad(), session.get(Inmuebles.class, inmueblePrueba.getId()).getLocalizacion().getComunidad());
        assertEquals(inmueblePrueba.getLocalizacion().getProvincia(), session.get(Inmuebles.class, inmueblePrueba.getId()).getLocalizacion().getProvincia());
        assertEquals(inmueblePrueba.getLocalizacion().getPoblacion(), session.get(Inmuebles.class, inmueblePrueba.getId()).getLocalizacion().getPoblacion());
        assertEquals((Double) 4200000.0, inmueblePrueba.getPrecioCompra());
        assertThat(session.get(Inmuebles.class, i_insertado).getClientePropietario(), is(inmueblePrueba.getClientePropietario()));
        assertThat(session.get(Inmuebles.class, i_insertado).getClientePropietario().getIdAgente(), is(inmueblePrueba.getClientePropietario().getIdAgente()));
>>>>>>> 376f0847afcd6042ed2d5779d616bac0ac63ed02

        transaction.rollback();

        session.close();

    }

    /**
     * This test is supposed to fail upon ConstraintViolationException throw as its query data is not coherence
     */
    @Test
    public void insertarInmuebleKO() {

        Executable insertarInmueble = () -> {

            Inmuebles inmuebles = new Inmuebles();

            //This cliente is expected to not exist in database
            inmuebles.setClientePropietario(new Clientes(Long.valueOf(420l)));

            Direccion direccion = new Direccion();

            direccion.setDireccionCalle("C/DE TU PUTA MADRE");
            direccion.setDireccionEscalera("hehe");

            Caracteristicas caracteristicas = new Caracteristicas();

            caracteristicas.setCarpinteriaExterior("to guapa");
            caracteristicas.setEstadoConservacion(1);

            Localizacion localizacion = new Localizacion();
            //This location values may not be in the database
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

            if (session.getTransaction() != null) {

                transaction = session.getTransaction();

            } else {

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
