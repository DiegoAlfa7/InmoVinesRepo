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


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Date;

import static org.junit.Assert.assertNotNull;


@DisplayName("Prueba de Inserción de Inmuebles")
public class InmueblesTest {

    static Session session;

   /**
     * This inmueble is a dummy entity for testing purposes
     */
    static Inmuebles inmueblePrueba;
    static Executable insertarInmueble;

    /**

     * This test should be all ok.
     * It inserts an Inmueble instance to the database with all its necessary relationships
     * ClientePropietario, AgenteCargo, Localización (Comunidad, Provincia, Municipio, Zona).
     * When test finishes, transaction is rolled back so no rubbish data is published
     *
     * @throws SQLException

     * This method opens an hibernate session within database and instantiates an entity of Inmuebles

     */
    @Test
    @BeforeAll
    public static void setUp() {
        session = NewHibernateUtil.getSessionFactory().openSession();

        insertarInmueble = () -> {

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

        Date date = new Date();

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


        Comunidades comunidades = session.get(Comunidades.class, 1l);

        Clientes cliente = new Clientes();
        DatosPersonales pdata = new DatosPersonales();
        cliente.setDatosPersonales(pdata);
        cliente.getDatosPersonales().setNombre("Cliente de Prueba");
        cliente.setAgenteEntrada(agente);
        cliente.setAgente(agente);
        inmueblePrueba.setClientePropietario(cliente);
        inmueblePrueba.setAgente(agente);



        Provincias provincia = comunidades.getProvinciasList().get(0);

        Municipios municipio = provincia.getMunicipiosList().get(0);

        Zonas zona = municipio.getZonasList().get(0);



        //CARACTERISTICAS DE INMUEBLES
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



        caracteristicas.setEficienciaEnergeticaFecvalid(date);
        caracteristicas.setEficienciaEnergeticaEmisiones(Float.valueOf(23));
        caracteristicas.setOrientacionSolar("Norte");
        caracteristicas.setSuelos("Suelos");
        caracteristicas.setCarpinteriaExterior("Carpinteria Exterior");
        caracteristicas.setCarpinteriaInterior("Carpinteria Interior");
        inmueblePrueba.setCaracteristicas(caracteristicas);





        System.out.println("DATOS DE LAS LOCALIZACIONES -->" + localizacion.toString());
        System.out.println("DATOS DE LAS DIRECCIONES --> " + direccion.toString());
        System.out.println("DATOS DE LAS CARACTERISTICAS --> " + caracteristicas.toString());


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



        //TEST ASSERTS


        assertNotNull(i_insertado);
        assertEquals( inmueblePrueba, session.get(Inmuebles.class, i_insertado));
        assertEquals(inmueblePrueba.getLocalizacion().getComunidad(), session.get(Inmuebles.class, inmueblePrueba.getId()).getLocalizacion().getComunidad());
        assertEquals(inmueblePrueba.getLocalizacion().getProvincia(), session.get(Inmuebles.class, inmueblePrueba.getId()).getLocalizacion().getProvincia());
        assertEquals(inmueblePrueba.getLocalizacion().getPoblacion(), session.get(Inmuebles.class, inmueblePrueba.getId()).getLocalizacion().getPoblacion());
        assertEquals((Double) 4200000.0, inmueblePrueba.getPrecioCompra());
        assertThat(session.get(Inmuebles.class, i_insertado).getClientePropietario(), is(inmueblePrueba.getClientePropietario()));
        assertThat(session.get(Inmuebles.class, i_insertado).getClientePropietario().getAgente(), is(inmueblePrueba.getClientePropietario().getAgente()));


        transaction.rollback();

        session.close();

    }

    /**
     * This test is supposed to fail upon ConstraintViolationException throw as its query data is not coherence
     */
    @Test
    public void insertarInmuebleKO() {

        assertThrows(ConstraintViolationException.class, this.insertarInmueble);
    }
}
