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







}