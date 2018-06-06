package com.diegoa.inmovinesrest.services.localizacion.municipios.srv;

import com.diegoa.inmovinesrest.entities.localizacion.Municipios;
import com.diegoa.inmovinesrest.entities.localizacion.Zonas;

import java.util.List;

public interface MunicipiosService {

    Municipios findOneById(long ID);

    List<Municipios> listAll();


    List<Zonas> findZonasById(long ID);
}
