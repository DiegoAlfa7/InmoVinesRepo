package com.diegoa.inmovinesrest.services.localizacion.comunidades.srv;

import com.diegoa.inmovinesrest.entities.localizacion.Comunidades;
import com.diegoa.inmovinesrest.entities.localizacion.Provincias;

import java.util.List;

public interface ComunidadesService {

    Comunidades findOneById(long ID);

    List<Comunidades> listAll();


    List<Provincias> findProvinciasById(long ID);
}
