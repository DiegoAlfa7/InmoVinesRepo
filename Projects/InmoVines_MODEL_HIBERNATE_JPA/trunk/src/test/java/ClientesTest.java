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

        insertarCliente = () -> {

            Transaction transaction = session.beginTransaction();
            clientePrueba.setAgente(new Agentes(420l));

            session.save(clientePrueba);

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

        if (session.getTransaction() != null) {

            transaction = session.getTransaction();

        } else {

            transaction = session.beginTransaction();

        }

        Long cliente_insertado = (Long) session.save(this.clientePrueba);

        // Comprueba que el cliente insertado no venga vacío
        assertNotNull(cliente_insertado);
        // Comprueba que el agente vinculado al cliente coincide con el esperado
        assertEquals(this.clientePrueba.getAgente(), session.get(Clientes.class, this.clientePrueba.getId()).getAgente());
        // Comprueba que los inmuebles asociados al clientes no vengan vacios
        assertNotNull(this.clientePrueba.getInmueblesList());
        // Comrpueba que los inmuebles asociados coinciden con los esperados
        assertEquals(this.clientePrueba.getInmueblesList(), session.get(Clientes.class, this.clientePrueba.getId()).getInmueblesList());
        // Comprueba que los datos personales no vengan vacíos
        assertNotNull(this.clientePrueba.getDatosPersonales());

        transaction.rollback();


    }

    /**
     * Este test debería...
     */
    @Test
    public void b() {

        clientePrueba.setAgente(null);
        assertThrows(ConstraintViolationException.class, insertarCliente);

    }
}
