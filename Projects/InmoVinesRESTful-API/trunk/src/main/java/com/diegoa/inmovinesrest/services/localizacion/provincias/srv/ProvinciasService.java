package com.diegoa.inmovinesrest.services.localizacion.provincias.srv;

import com.diegoa.inmovinesrest.entities.localizacion.Provincias;

import java.util.List;

public interface ProvinciasService {

    Provincias findOneById(long ID);

    List<Provincias> listAll();


}
