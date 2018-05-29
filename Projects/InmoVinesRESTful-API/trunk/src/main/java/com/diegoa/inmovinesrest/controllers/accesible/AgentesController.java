package com.diegoa.inmovinesrest.controllers.accesible;


import com.diegoa.inmovinesrest.services.agentes.impl.AgentesServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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



    @GetMapping(value = "/agentes", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<String> getAgentes(Pageable pageable) throws JsonProcessingException {


      /*  Page<Agentes> pagina = agentesService.listAllByPage(pageable);

        List<Agentes> inmuebles = pagina.getContent();

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Page.class, new PageInmueblesSerializer());
        mapper.registerModule(module);


        String JsonPage = mapper.writeValueAsString(pagina);

        return new ResponseEntity<>(JsonPage, HttpStatus.OK);*/
      return null;




    }

    @GetMapping(value = "/agentes/{id}", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<String> getInmuebleById( @PathVariable("id") long id,  Pageable pageable) throws JsonProcessingException {


        /*Inmuebles i = agentesService.findOneById(id);

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Inmuebles.class, new InmueblesSerializer());
        mapper.registerModule(module);

        return new ResponseEntity<String>(mapper.writeValueAsString(i), HttpStatus.OK);*/
        return null;

    }







}