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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

@DisplayName("Prueba de Inserción de Clientes")
public class ClientesTest {

    private static Session s;
    private static Clientes clientePrueba;
    private static Executable insertarCliente;

    /**
     * Before executing test should initialize Hibernate Session
     */
    @BeforeAll
    public static void setUp() {

        s = NewHibernateUtil.getSessionFactory().openSession();

        clientePrueba = new Clientes();
        Localizacion localizacion = new Localizacion();
        DatosPersonales datosPersonales = new DatosPersonales();
        Comunidades comunidades = s.get(Comunidades.class, 2l);
        Provincias provincias = comunidades.getProvinciasList().get(0);
        Municipios municipios = provincias.getMunicipiosList().get(0);
        Zonas zonas = new Zonas();
        Inmuebles inmueble1 = new Inmuebles();
        Inmuebles inmueble2 = new Inmuebles();
        Agentes agentes = s.get(Agentes.class, 1l);
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

            Transaction transaction = s.beginTransaction();

            s.save(clientePrueba);

            transaction.rollback();

            s.close();


        };


    }

    @Test
    public void insertarClienteOK() {






    }
}
