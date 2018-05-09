import entities.Comunidades;
import entities.Municipios;
import entities.Provincias;
import entities.Zonas;
import entities.agentes.Agentes;
import entities.clientes.Clientes;
import entities.clientes.DatosPersonales;
import entities.inmuebles.Caracteristicas;
import entities.inmuebles.Inmuebles;
import entities.inmuebles.Localizacion;
import hibernateUtil.NewHibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.runners.MethodSorters;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Prueba de Inserción de Clientes")
public class ClientesTest {

    private static Session session;
    private static Clientes clientePrueba;
    private static Executable insertarCliente;
    private static Long inmueble_insertado;
    /**
     * Before executing test should initialize Hibernate Session
     */
    @BeforeAll
    public static void setUp() {

        session = NewHibernateUtil.getSessionFactory().openSession();

        clientePrueba = new Clientes();
        Localizacion localizacion = new Localizacion();
        DatosPersonales datosPersonales = new DatosPersonales();
        Comunidades comunidades = session.get(Comunidades.class, 2l);
        Provincias provincias = comunidades.getProvinciasList().get(0);
        Municipios municipios = provincias.getMunicipiosList().get(0);
        Zonas zonas = new Zonas(42l);
        Inmuebles inmueble1 = new Inmuebles();
        Inmuebles inmueble2 = new Inmuebles();
        Agentes agentes = session.get(Agentes.class, 1l);
        Caracteristicas caracteristicas = new Caracteristicas();

        caracteristicas.setEstadoConservacion(6);
        caracteristicas.setEficienciaEnergeticaEntramite01(true);

        zonas.setMunicipio(municipios);

        localizacion.setComunidad(comunidades);
        localizacion.setProvincia(provincias);
        localizacion.setMunicipio(municipios);
        localizacion.setZona(zonas);

        inmueble1.setLocalizacion(localizacion);
        inmueble1.setDescripcion("Descripcion del inmueble 1");
        inmueble1.setCaracteristicas(caracteristicas);

        inmueble2.setLocalizacion(localizacion);
        inmueble2.setDescripcion("Descripcion del inmueble 2");
        inmueble2.setCaracteristicas(caracteristicas);

        clientePrueba.addInmueble(inmueble1);
        clientePrueba.addInmueble(inmueble2);

        //DATOS PERSONALES
        datosPersonales.setApellidos("apellidos");
        datosPersonales.setDni("dni");
        datosPersonales.setEdad(18);
        datosPersonales.setMail("mail1@mail1.com");
        datosPersonales.setMail1("mail2@mail2.com");
        datosPersonales.setNacionalidad("nacionalidad");
        datosPersonales.setNombre("nombre");
        datosPersonales.setTelefono("telefono");
        datosPersonales.setTelefono1("telefono1");

        //CLIENTE
        clientePrueba.setDatosPersonales(datosPersonales);
        clientePrueba.setInquilino(true);
        clientePrueba.setArrendatario(false);
        clientePrueba.setComprador(false);
        clientePrueba.setVendedor(true);
        clientePrueba.setAgente(agentes);
        clientePrueba.setAgenteEntrada(agentes);
        clientePrueba.setIdInmuebleInteres(inmueble1);

        inmueble_insertado = (Long) session.save(inmueble1);

        insertarCliente = () -> {

            Transaction transaction = session.beginTransaction();

            Clientes c = clientePrueba;
            c.setAgenteEntrada(null);

            session.save(c);
            transaction.rollback();



        };


    }

    @AfterAll
    public static void manageShutDown() {

        session.close();
    }

    /**
     * En este test no debería producirse ningún error
     * Inserta un cliente con los datos seteados anteriormente
     * El cliente nunca es insertado finalmente
     */
    @Test
    public void a() {


        Transaction transaction;

        if (session.getTransaction() == null) {

            transaction = session.getTransaction();

        }else{

            transaction = session.beginTransaction();
        }


        //need to insert an Inmuebles instance before saving a cliente with IdInmuebleInteres

        clientePrueba.setIdInmuebleInteres(session.get(Inmuebles.class,inmueble_insertado));
        Long cliente_insertado = (Long) session.save(clientePrueba);


        // Comprueba que el cliente insertado no venga vacío
        assertNotNull(cliente_insertado);
        // Comprueba que el agente vinculado al cliente coincide con el esperado
        assertEquals(clientePrueba.getAgente(), session.get(Clientes.class, clientePrueba.getId()).getAgente());
        // Comprueba que los inmuebles asociados al clientes no vengan vacios
        assertNotNull(clientePrueba.getInmueblesList());
        // Comrpueba que los inmuebles asociados coinciden con los esperados
        assertEquals(clientePrueba.getInmueblesList(), session.get(Clientes.class, clientePrueba.getId()).getInmueblesList());
        // Comprueba que los datos personales no vengan vacíos
        assertNotNull(clientePrueba.getDatosPersonales());
        //Comprueba que el inmueble insertado, es el mismo que el inmueble de interés del cliente
        assertEquals(clientePrueba.getIdInmuebleInteres(), session.get(Inmuebles.class,inmueble_insertado));

        transaction.rollback();


    }

    /**
     * Este test debería...
     */
    @Test
    public void b() {


        assertThrows(ConstraintViolationException.class, insertarCliente);

    }
}
