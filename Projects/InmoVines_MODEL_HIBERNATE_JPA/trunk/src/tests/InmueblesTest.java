package tests;


import entities.Comunidades;
import entities.Municipios;
import entities.Provincias;
import entities.clientes.Clientes;
import entities.inmuebles.Caracteristicas;
import entities.inmuebles.Direccion;
import entities.inmuebles.Inmuebles;
import entities.inmuebles.Localizacion;
import hibernateUtil.NewHibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;

@DisplayName("Prueba de Inserción de Inmuebles")
public class InmueblesTest {

    @Test
    public void insertarInmueble() throws SQLException {

        Inmuebles inmuebles = new Inmuebles();

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
        inmuebles.setTextoReclamo("insertarInmueble -> InmueblesTest");
        inmuebles.setDescripcion("Gracias a este edificio nos van a dar un 10 jajajajajjj");
        inmuebles.setTipo(1);


        Session session = NewHibernateUtil.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();
        System.out.println(inmuebles);

        Long i_insertado = (Long) session.save(inmuebles);
        CriteriaBuilder criteriaB = session.getCriteriaBuilder();
        CriteriaQuery<Long> crit = criteriaB.createQuery(Long.class);

        crit.select(criteriaB.count(crit.from(Inmuebles.class)));
        Long row_count = session.createQuery(crit).getSingleResult();





        assertNotNull(i_insertado);

        transaction.rollback();
        session.close();




    }
    @Test(expected = org.hibernate.exception.ConstraintViolationException.class)
    public void insertarInmuebleFailed(){

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


        Session session = NewHibernateUtil.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();
        System.out.println(inmuebles);
        session.save(inmuebles);

        transaction.rollback();
        session.close();












    }
}
