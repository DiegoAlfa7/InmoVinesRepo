package tests;

import entities.Comunidades;
import entities.Municipios;
import entities.Provincias;
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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Prueba de Inserción de Clientes")
public class ClientesTest {

    private Session s;

    /**
     * Before executing tests should initialize Hibernate Session
     */
    @BeforeAll
    public void setUp(){

        s = NewHibernateUtil.getSessionFactory().openSession();

    }

    @Test
    public void insertCliente(){

        Inmuebles inmuebles = new Inmuebles();

        Direccion direccion = new Direccion();

        direccion.setDireccionCalle("C/INMUEBLE DE PRUEBA");
        direccion.setDireccionEscalera("hehe");

        Caracteristicas caracteristicas = new Caracteristicas();

        caracteristicas.setCarpinteriaExterior("PRUEBA");
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
        inmuebles.setTextoReclamo(this.getClass().getCanonicalName());
        inmuebles.setDescripcion("Gracias a este edificio nos van a dar un 10 jajajajajjj");
        inmuebles.setTipo(1);


        Agentes agente = new Agentes(Long.valueOf(1l));
        agente.setCargo(s.get(Cargos.class,1l));
        agente.setNombre("AGENTE DE PRUEBA");


        Clientes cliente = new Clientes();
        DatosPersonales datos = new DatosPersonales(
                "Diego",
                "Alfaro Sáez",
                "mail@mail.com",
                "654789654",
                null,
                null,
                "67543223F",
                "No aporta",
                30
        );








    }
}
