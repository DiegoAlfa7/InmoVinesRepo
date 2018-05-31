package com.diegoa.inmovinesrest.controllers.accesible.inmuebles;


import com.diegoa.inmovinesrest.entities.inmuebles.Inmuebles;
import com.diegoa.inmovinesrest.entities.inmuebles.InmueblesPublicSerializer;
import com.diegoa.inmovinesrest.entities.inmuebles.InmueblesPublicSerializerCard_Page;
import com.diegoa.inmovinesrest.services.inmuebles.impl.InmueblesServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador Restful encargado de la distribución de datos
 * de inmuebles para las aplicaciones identificadas como usuario.
 *
 * @since 0.0.1
 * @author Diego Alfaro
 */
@CrossOrigin(origins = {"http://localhost:8087","http://localhost:8080" })
@RestController
@RequestMapping("user")
public class PublicInmueblesController {

    @Autowired
    InmueblesServiceImpl inmueblesUserService;



    @GetMapping(value = "/inmuebles", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<String> getInmuebles(Pageable pageable) throws JsonProcessingException {


        Page<Inmuebles> pagina = inmueblesUserService.listAllByPage(pageable);

        List<Inmuebles> inmuebles = pagina.getContent();

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Page.class, new InmueblesPublicSerializerCard_Page());
        mapper.registerModule(module);


        String JsonPage = mapper.writeValueAsString(pagina);

        return new ResponseEntity<>(JsonPage, HttpStatus.OK);




    }

    @GetMapping(value = "/inmuebles/{id}", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<String> getInmuebleById( @PathVariable("id") long id,  Pageable pageable) throws JsonProcessingException {


        Inmuebles i = inmueblesUserService.findOneById(id);

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Inmuebles.class, new InmueblesPublicSerializer());
        mapper.registerModule(module);
        return new ResponseEntity<String>(mapper.writeValueAsString(i), HttpStatus.OK);

    }



}