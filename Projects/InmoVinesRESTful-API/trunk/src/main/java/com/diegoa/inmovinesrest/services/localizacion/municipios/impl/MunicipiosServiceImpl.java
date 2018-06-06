package com.diegoa.inmovinesrest.services.localizacion.municipios.impl;

import com.diegoa.inmovinesrest.entities.localizacion.Municipios;
import com.diegoa.inmovinesrest.entities.localizacion.Zonas;
import com.diegoa.inmovinesrest.repositories.localizacion.MunicipiosRepository;
import com.diegoa.inmovinesrest.services.localizacion.municipios.srv.MunicipiosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Daniel Arroyo
 * @version 0.0.1
 */
@Service
public class MunicipiosServiceImpl implements MunicipiosService {

    @Autowired
    MunicipiosRepository municipiosRepository;

    @Override
    public Municipios findOneById(long ID) {

        Optional<Municipios> optionalMunicipios = this.municipiosRepository.findById(ID);

        if (optionalMunicipios.isPresent()) {
            return optionalMunicipios.get();
        }

        throw new RuntimeException("El municipio con ID: "+ID+" no produjo ningún resultado");
    }

    @Override
    public List<Municipios> listAll() {

        return (List<Municipios>) this.municipiosRepository.findAll();
    }

    @Override
    public List<Zonas> findZonasById(long ID) {

        Optional<Municipios> optionalProvincias = this.municipiosRepository.findById(ID);

        if (optionalProvincias.isPresent()) {
            return optionalProvincias.get().getZonasList();
        }

        throw new RuntimeException("El municipio con ID: "+ID+" no produjo ningún resultado");
    }
}
