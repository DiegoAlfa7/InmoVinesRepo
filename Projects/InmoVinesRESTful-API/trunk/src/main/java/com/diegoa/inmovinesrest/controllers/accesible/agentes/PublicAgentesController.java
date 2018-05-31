package com.diegoa.inmovinesrest.controllers.accesible.agentes;


import com.diegoa.inmovinesrest.entities.agentes.Agentes;
import com.diegoa.inmovinesrest.entities.agentes.AgentesPublicSerializer;
import com.diegoa.inmovinesrest.entities.agentes.AgentesPublicSerializer_List;
import com.diegoa.inmovinesrest.entities.agentes.AgentesPublicSerializer_Page;
import com.diegoa.inmovinesrest.services.agentes.impl.AgentesServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador Restful encargado de la distribución de datos
 * de Agentes para las aplicaciones identificadas como usuario.
 * Este controlador hará uso de custom serializers para convertir
 * objetos java en JSONs adecuados para su trasmisión, con ciertos datos
 * sensibles omitidos, etc.
 *<hr/>
 * <br/><b>C O R S</b> eneabled for this Controller --> origins = {"http://localhost:8087","http://localhost:8080" }
 *
 * @since 0.0.1
 * @author Diego Alfaro
 */
@CrossOrigin(origins = {"http://localhost:8087","http://localhost:8080" })
@RestController
@RequestMapping("user")
public class PublicAgentesController {

    @Autowired
    AgentesServiceImpl agentesService;

    /**
     * Esta operación del controlador se encarga de listar dentro de un pageable los agentes de la base de datos, agrupándolos en
     * función del tipo de página que se pida en los parámetros de la URL.
     * @apiNote <b>ENDPOINT: .../user/agentes/page[?page=PAGE&size=SIZE&sort=SHORT,asc|desc]</b>
     * @param pageable Se refiere a la PageRequest que se espera que venga junto con la petición, con los parámetros
     * 'page', 'size' y 'sort' debidamente introducidos
     *
     * @return ResponseEntity<String> Respuesta Http con el la representación string del JSON del objeto de la <b>Page<\Agentes\></b>
     * @throws JsonProcessingException si ocurre un error durante la serialización del objeto.
     */
    @RequestMapping(value = "/agentes/page", produces = {"application/json"}, method = {RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<String> getAgentesByPage(Pageable pageable) throws JsonProcessingException {

        Page<Agentes> pagina = agentesService.listAllByPage(pageable);


        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Page.class, new AgentesPublicSerializer_Page());
        mapper.registerModule(module);


        String JsonPage = mapper.writeValueAsString(pagina);


        return new ResponseEntity<>(JsonPage, HttpStatus.OK);


    }
    /**
     * Esta operación del controlador se encarga de devolver en una Lista genérica <b>todos los Agentes que haya en la base de datos.</b>
     *
     * @apiNote <b>ENDPOINT: .../user/agentes</b>
     *`
     * @return ResponseEntity<String> Respuesta Http con la representación string del JSON del array de todos los agentes de la BBDD.
     * @throws JsonProcessingException si ocurre un error durante la serialización del objeto.
     */
    @RequestMapping(value = "/agentes", produces = {"application/json"}, method = {RequestMethod.GET, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<String> getAgentesList() throws JsonProcessingException {

        List<Agentes> lista = agentesService.listAll();



        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(List.class, new AgentesPublicSerializer_List());
        mapper.registerModule(module);


        String JsonList = mapper.writeValueAsString(lista);

        return new ResponseEntity<>(JsonList, HttpStatus.OK);


    }
    /**
     * Esta operación del controlador se encarga de una única entidad de tipo Agentes. Realiza una búsqueda en los Repositorios en función de
     * el ID facilitado.
     *
     * @apiNote <b>ENDPOINT: .../admin/agentes/{id}</b>
     * @return ResponseEntity<String> Respuesta Http con la representación string del JSON del agente de la BBDD.
     * @throws JsonProcessingException si ocurre un error durante la serialización del objeto.
     */
    @GetMapping(value = "/agentes/{id}", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<String> getAgenteById(@PathVariable("id") long id, Pageable pageable) throws JsonProcessingException {


        Agentes agente = this.agentesService.findOneById(id);
        if(agente == null){throw new RuntimeException("EL agente con id: "+id+" no ha devuelto ningún resultado");}
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Agentes.class, new AgentesPublicSerializer());
        mapper.registerModule(module);


        return new ResponseEntity(mapper.writeValueAsString(agente), HttpStatus.OK);

    }







}