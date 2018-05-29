package com.diegoa.inmovinesrest.controllers.accesible;


import com.diegoa.inmovinesrest.entities.agentes.Agentes;
import com.diegoa.inmovinesrest.entities.inmuebles.Inmuebles;
import com.diegoa.inmovinesrest.entities.inmuebles.InmueblesSerializer;
import com.diegoa.inmovinesrest.entities.inmuebles.PageInmueblesSerializer;
import com.diegoa.inmovinesrest.services.agentes.impl.AgentesServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador Restful encargado de la distribución de datos
 * de inmuebles para las aplicaciones identificadas como usuario.
 *
 * @since 0.0.1
 * @author Diego Alfaro
 */
@RestController
@RequestMapping("user")
public class AgentesController {

    @Autowired
    AgentesServiceImpl agentesService;



    /*@GetMapping("/inmuebles")
    @ResponseBody
    public String getClientes(Pageable pageable) {


        Page<Agentes> pagina = agentesService.listAllByPage(pageable);

        List<Agentes> agentes = pagina.getContent();

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Agentes.class, new AgentesSerializer());
        mapper.registerModule(module);

        Page<Inmuebles> pageable_inmuebles_data = new PageImpl<Inmuebles>(agentes, pagina.getPageable(), agentes.size());


        try {
            return mapper.writeValueAsString(pageable_inmuebles_data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }


    }

    @GetMapping("/inmuebles/{id}")
    @ResponseBody
    public String getInmuebleById( @PathVariable("id") long id,  Pageable pageable) {


        Inmuebles i = agentesService.findOneById(id);



        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Inmuebles.class, new InmueblesSerializer());
        mapper.registerModule(module);




        try {
            return mapper.writeValueAsString(i);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }


    }*/



}