package tests;

import entities.Comunidades;
import hibernateUtil.NewHibernateUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test Comunidades | Provincias | Municipios | Zonas")
public class LocalizacionTest {

    public static Session session;

    @BeforeAll
    public static void setupSession(){

        session = NewHibernateUtil.getSessionFactory().openSession();

    }

    @Test
    public void testComunidadValues(){

        Comunidades comunidades = session.get(Comunidades.class, 1l);


        assertNotNull(comunidades.getProvinciasList());
        System.out.println(comunidades.getProvinciasList());



    }




}
