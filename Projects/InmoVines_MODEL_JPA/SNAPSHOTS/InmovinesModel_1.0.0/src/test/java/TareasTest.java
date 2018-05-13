import entities.agentes.Agentes;
import entities.agentes.Tareas;
import hibernateUtil.NewHibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.sql.Date;
import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Prueba de Inserción de Tareas")
public class TareasTest {

    private static Session session;
    private static Executable insertarTarea;
    private static Tareas tareaPrueba;
    private static Long tarea_insertada;

    @BeforeAll
    public static void setUp() {


        session = NewHibernateUtil.getSessionFactory().openSession();

        tareaPrueba = new Tareas();
        Agentes agentes = session.get(Agentes.class, 1l);

        tareaPrueba.setAgente(agentes);
        tareaPrueba.setCompletada(Byte.valueOf("0"));
        tareaPrueba.setConcepto("Sacar un 10");
        tareaPrueba.setDescripcion("Lo dicho un PUTO 10");
        tareaPrueba.setFecha(new Date(Calendar.getInstance().getTime().getTime()));
        tareaPrueba.setFechaCompletada(new Date(Calendar.getInstance().getTime().getTime()));

        tarea_insertada = (Long) session.save(tareaPrueba);

        insertarTarea = () -> {

            Transaction transaction = session.beginTransaction();

            Tareas tareas = tareaPrueba;

            session.save(tareas);
            transaction.rollback();

        };

    }

    @AfterAll
    public static void manageShutDown() {

        session.close();
    }

    @Test
    public void insertarTareaOK() {

        Transaction transaction;

        if (session.getTransaction() == null) {

            transaction = session.getTransaction();

        } else {

            transaction = session.beginTransaction();
        }

        tarea_insertada = (Long) session.save(tareaPrueba);

        assertNotNull(tarea_insertada);

        assertThat(session.get(Tareas.class, tarea_insertada).getAgente().getId(), is(tareaPrueba.getAgente().getId()));


        transaction.rollback();

    }

    @Test
    public void insertarTareaKO() {

        assertThrows(ConstraintViolationException.class, insertarTarea);

    }
}
