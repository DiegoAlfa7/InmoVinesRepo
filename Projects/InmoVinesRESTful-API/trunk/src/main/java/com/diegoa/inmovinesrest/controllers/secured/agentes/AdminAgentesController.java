package com.diegoa.inmovinesrest.controllers.secured.agentes;

import com.diegoa.inmovinesrest.entities.agentes.Agentes;
import com.diegoa.inmovinesrest.services.agentes.impl.AgentesServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Daniel Arroyo
 * @since 0.0.1
 */

@CrossOrigin(origins = {"http://localhost:8087", "http://localhost:8080"})
@RestController
@RequestMapping("admin")
public class AdminAgentesController {

    @Autowired
    AgentesServiceImpl agentesService;

    @RequestMapping(value = "/agentes", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Page<Agentes>> getAgentes(Pageable pageable) throws JsonProcessingException {

        Page<Agentes> page = agentesService.listAllByPage(pageable);

        return new ResponseEntity(page, HttpStatus.OK);
    }


    @GetMapping(value = "/agentes/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Agentes> getAgenteById(@PathVariable("id") long id, Pageable pageable) throws JsonProcessingException {

        Agentes agentes = agentesService.findOneById(id);


        return new ResponseEntity(agentes, HttpStatus.OK);
    }
}
