package tests;

import entities.Comunidades;
import entities.Municipios;
import entities.Provincias;
import entities.Zonas;
import entities.agentes.Agentes;
import entities.clientes.Clientes;
import entities.clientes.DatosPersonales;
import entities.clientes.Intereses;
import entities.inmuebles.Caracteristicas;
import entities.inmuebles.Inmuebles;
import entities.inmuebles.Localizacion;
import hibernateUtil.NewHibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

@DisplayName("Prueba de Inserción de Clientes")
public class ClientesTest {

    private static Session s;

    /**
     * Before executing tests should initialize Hibernate Session
     */
    @BeforeAll
    public static void setUp() {

        s = NewHibernateUtil.getSessionFactory().openSession();

    }

    @Test
    public void insertCliente() {

        Clientes clientes = s.get(Clientes.class,6l);
        Localizacion localizacion = new Localizacion();
        DatosPersonales datosPersonales = new DatosPersonales();
        Comunidades comunidades = s.get(Comunidades.class, 2l);
        Provincias provincias = comunidades.getProvinciasList().get(0);
        Municipios municipios = provincias.getMunicipiosList().get(0);
        Zonas zonas = new Zonas();
        Intereses intereses = new Intereses();
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

        Transaction transaction = s.beginTransaction();

        Inmuebles i1 = s.get(Inmuebles.class, 15l);
        Inmuebles i2 = s.get(Inmuebles.class, 16l);

        ArrayList <Inmuebles> inmueblesList = new ArrayList<>();

        inmueblesList.add(i1);
        inmueblesList.add(i2);


        clientes.addInmueblesList(i1);
        clientes.addInmueblesList(i2);


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
        clientes.setDatosPersonales(datosPersonales);
        clientes.setInmueblesList(inmueblesList);
        clientes.setInquilino(true);
        clientes.setArrendatario(false);
        clientes.setComprador(false);
        clientes.setVendedor(true);
        clientes.setIdAgente(agentes);


        s.save(clientes);

        transaction.rollback();

        s.close();


    }
}
