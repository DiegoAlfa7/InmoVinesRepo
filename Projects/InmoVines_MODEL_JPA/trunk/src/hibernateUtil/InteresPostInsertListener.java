package hibernateUtil;

import entities.clientes.Intereses;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.persister.entity.EntityPersister;

public class InteresPostInsertListener implements PostInsertEventListener {


    @Override
    public void onPostInsert(PostInsertEvent event) {

        if(event.getEntity() instanceof Intereses){


            //Search for cohincidence in inmuebles table



        }

    }

    @Override
    public boolean requiresPostCommitHanding(EntityPersister persister) {
        return false;
    }
}
