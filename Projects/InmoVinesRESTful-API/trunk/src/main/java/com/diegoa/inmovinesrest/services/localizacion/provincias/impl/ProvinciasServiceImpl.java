package com.diegoa.inmovinesrest.services.localizacion.provincias.impl;

import com.diegoa.inmovinesrest.entities.localizacion.Comunidades;
import com.diegoa.inmovinesrest.entities.localizacion.Municipios;
import com.diegoa.inmovinesrest.entities.localizacion.Provincias;
import com.diegoa.inmovinesrest.repositories.localizacion.ProvinciasRepository;
import com.diegoa.inmovinesrest.services.localizacion.provincias.srv.ProvinciasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProvinciasServiceImpl implements ProvinciasService {

    @Autowired
    ProvinciasRepository provinciasRepository;

    @Override
    public Provincias findOneById(long ID) {

        Optional<Provincias> optionalProvincias = this.provinciasRepository.findById(ID);

        if (optionalProvincias.isPresent()) {
            return optionalProvincias.get();
        }
        throw new RuntimeException("La provincia con ID: "+ID+" no produjo ningún resultado");
    }

    @Override
    public List<Provincias> listAll() {

        return (List<Provincias>) this.provinciasRepository.findAll();
    }

    @Override
    public List<Municipios> findMunicipiosById(long ID) {

        Optional<Provincias> optionalProvincias = this.provinciasRepository.findById(ID);

        if (optionalProvincias.isPresent()) {
            return optionalProvincias.get().getMunicipiosList();
        }

        throw new RuntimeException("La provincia con ID: "+ID+" no produjo ningún resultado");
    }
}
