package com.diegoa.inmovinesrest.services.localizacion.municipios.srv;

import com.diegoa.inmovinesrest.entities.localizacion.Municipios;

import java.util.List;

public interface MunicipiosService {

    Municipios findOneById(long ID);

    List<Municipios> listAll();


}
