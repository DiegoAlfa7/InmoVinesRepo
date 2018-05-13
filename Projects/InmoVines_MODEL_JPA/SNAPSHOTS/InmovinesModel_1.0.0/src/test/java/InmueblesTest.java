import entities.Comunidades;
import entities.Municipios;
import entities.Provincias;
import entities.Zonas;
import entities.agentes.Agentes;
import entities.agentes.Cargos;
import entities.clientes.Clientes;
import entities.clientes.DatosPersonales;
import entities.inmuebles.*;
import hibernateUtil.NewHibernateUtil;
import org.hamcrest.CoreMatchers;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.sql.SQLException;
import java.util.Date;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DisplayName("Prueba de Inserción de Inmuebles")
public class InmueblesTest {

    static Session session;

    /**
     * This inmueble is a dummy entity for testing purposes
     */
    static Inmuebles inmueblePrueba;
    static Executable insertarInmueble;

    /**
     * This method opens an hibernate session within database and other data operations related to Inmuebles.class
     */
    @Test
    @BeforeAll
    public static void setUp() {
        session = NewHibernateUtil.getSessionFactory().openSession();

        insertarInmueble = () -> {

            Inmuebles inmuebles = new Inmuebles();

            //This cliente is expected to not exist in database
            inmuebles.setClientePropietario(new Clientes(Long.valueOf(1l)));

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
            inmuebles.setTipos(new Tipos(Long.valueOf(20l)));

            Transaction transaction;

            if (session.getTransaction() != null) {

                transaction = session.getTransaction();

            } else {

                transaction = session.beginTransaction();

            }
            System.out.println(inmuebles);
            session.save(inmuebles);

            transaction.rollback();


        };

        inmueblePrueba = new Inmuebles();

        Date date = new Date();
        Agentes agentes = session.get(Agentes.class,1l);
        Comunidades comunidades = session.get(Comunidades.class, 1l);
        Clientes clientes = session.get(Clientes.class, 420l);

        inmueblePrueba.setClientePropietario(clientes);
        inmueblePrueba.setAgente(agentes);


        Provincias provincia = comunidades.getProvinciasList().get(0);

        Municipios municipio = provincia.getMunicipiosList().get(0);

        Zonas zona = municipio.getZonasList().get(0);

        //TIPO DE INMUEBLE
        Tipos tipos = session.get(Tipos.class,1l);

        //GESTION INMUEBLE
        Gestiones gestiones = session.get(Gestiones.class,1l);

        //CARACTERISTICAS DE INMUEBLES
        inmueblePrueba.setReferenciaCatastral("Referencia catastral");
        inmueblePrueba.setReferencia("Referencia");
        inmueblePrueba.setTipos(tipos);
        inmueblePrueba.setGestiones(gestiones);
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

        System.out.println("Nombre tipo: " + inmueblePrueba.getTipos().getNombre());
        System.out.println("DATOS DE LAS LOCALIZACIONES -->" + localizacion.toString());
        System.out.println("DATOS DE LAS DIRECCIONES --> " + direccion.toString());
        System.out.println("DATOS DE LAS CARACTERISTICAS --> " + caracteristicas.toString());


    }


    /**
     * Este método sólo ejecuta
     */
    @AfterAll
    public static void manageShutDown() {


        session.close();


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
        assertEquals(inmueblePrueba, session.get(Inmuebles.class, i_insertado));
        assertEquals(inmueblePrueba.getLocalizacion().getComunidad(), session.get(Inmuebles.class, inmueblePrueba.getId()).getLocalizacion().getComunidad());
        assertEquals(inmueblePrueba.getLocalizacion().getProvincia(), session.get(Inmuebles.class, inmueblePrueba.getId()).getLocalizacion().getProvincia());
        assertEquals(inmueblePrueba.getLocalizacion().getPoblacion(), session.get(Inmuebles.class, inmueblePrueba.getId()).getLocalizacion().getPoblacion());
        assertEquals(inmueblePrueba.getLocalizacion().getPoblacion(), session.get(Inmuebles.class, inmueblePrueba.getId()).getLocalizacion().getPoblacion());
        assertEquals((Double) 4200000.0, inmueblePrueba.getPrecioCompra());
        assertThat(session.get(Inmuebles.class, i_insertado).getClientePropietario(), CoreMatchers.is(equalTo(inmueblePrueba.getClientePropietario())));
        assertThat(session.get(Inmuebles.class, i_insertado).getClientePropietario().getAgente(), is(inmueblePrueba.getClientePropietario().getAgente()));
        assertThat(session.get(Inmuebles.class,i_insertado).getTipos().getId(), is(inmueblePrueba.getTipos().getId()));
        assertThat(session.get(Inmuebles.class,i_insertado).getGestiones().getId(),is(inmueblePrueba.getGestiones().getId()));

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
