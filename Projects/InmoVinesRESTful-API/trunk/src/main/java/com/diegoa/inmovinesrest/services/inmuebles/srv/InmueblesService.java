package com.diegoa.inmovinesrest.services.inmuebles.srv;

import com.diegoa.inmovinesrest.entities.clientes.Clientes;
import com.diegoa.inmovinesrest.entities.inmuebles.Inmuebles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InmueblesService {

    Page<Inmuebles> listAllByPage(Pageable pageable);

    Inmuebles findOneById(long ID);

    List<Inmuebles> listAll();

    public Inmuebles create(Inmuebles i, long idCliente, long idAgente, long idComunidad, long idProvincia, long idMunicipio, long idZona, long idGestion, long idTipo);

    public Inmuebles update(Inmuebles i);

    public boolean delete(long id);

    public Clientes getClientePropietarioByID(long ID);

    List<Clientes> getInteresadosByID(long id);

    List<Inmuebles> listByFilter(String tipo, String pMax, String pMin, String hab, String banos, String mUtiles, String comunidad);
}
