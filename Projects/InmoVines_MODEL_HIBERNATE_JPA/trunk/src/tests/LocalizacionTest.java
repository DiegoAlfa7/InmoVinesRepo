package tests;

import entities.Comunidades;
import entities.Municipios;
import entities.Provincias;
import entities.Zonas;
import entities.agentes.Agentes;
import hibernateUtil.NewHibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test Comunidades | Provincias | Municipios | Zonas")
public class LocalizacionTest {

    public static Session session;

    @BeforeAll
    public static void setupSession() {

        session = NewHibernateUtil.getSessionFactory().openSession();

    }

    @Test
    public void testZonasOK() {


        Comunidades comunidades = session.get(Comunidades.class, 1l);
        Municipios municipios = session.get(Municipios.class, 2l);
        Provincias provincias = session.get(Provincias.class, 1l);
        municipios.setProvincia(provincias);
        Zonas zonas = new Zonas();
        Agentes agentes = session.get(Agentes.class, 1l);

        zonas.setMunicipio(municipios);
        zonas.setNombre("prueba");
        zonas.setActiva(true);
        zonas.setNombreAdmin(agentes.getNombre());

        Transaction transaction;

        if (session.getTransaction() != null) {

            transaction = session.getTransaction();

        } else {

            transaction = session.beginTransaction();

        }

        Long z_insertado = (Long) session.save(zonas);


        assertNotNull(comunidades.getProvinciasList());
        assertEquals(session.get(Zonas.class, z_insertado), zonas);


        System.out.println(comunidades.getProvinciasList());

        transaction.rollback();

        session.close();


    }

    @Test
    public void testZonasKO() {

        Executable insertarZona = () -> {

            Municipios municipios = session.get(Municipios.class, 1l);
            Zonas zonas = new Zonas();
            Agentes agentes = session.get(Agentes.class, 1l);

            zonas.setMunicipio(municipios);
            zonas.setNombre("prueba");
            zonas.setActiva(true);
            zonas.setNombreAdmin(agentes.getNombre());


            Transaction transaction;

            if (session.getTransaction() != null) {

                transaction = session.getTransaction();

            } else {

                transaction = session.beginTransaction();

            }
            session.save(zonas);

            transaction.rollback();


            session.close();
        };

        assertThrows(ConstraintViolationException.class, insertarZona);
    }


}
