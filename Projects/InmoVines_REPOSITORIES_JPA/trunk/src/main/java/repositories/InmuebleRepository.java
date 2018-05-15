package repositories;

import entities.inmuebles.Inmuebles;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class InmuebleRepository {

    @PersistenceContext
    EntityManager em;

    public List<Inmuebles> list(){

        return em.createQuery("from Inmuebles").getResultList();




    }


}
