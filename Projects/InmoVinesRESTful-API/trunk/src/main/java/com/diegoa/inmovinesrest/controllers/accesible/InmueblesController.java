package com.diegoa.inmovinesrest.controllers.accesible;


import com.diegoa.inmovinesrest.entities.inmuebles.Inmuebles;
import com.diegoa.inmovinesrest.entities.inmuebles.InmueblesPublicSerializer;
import com.diegoa.inmovinesrest.entities.inmuebles.InmueblesSerializerCard_Page;
import com.diegoa.inmovinesrest.services.inmuebles.impl.InmueblesServiceImpl;
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
 * @author Diego Alfaro
 * @since 0.0.1
 */
@RestController
@RequestMapping("user")
public class InmueblesController {

    @Autowired
    InmueblesServiceImpl inmueblesUserService;


    @GetMapping("/inmuebles")
    @ResponseBody
    public String getInmuebles(Pageable pageable) {


        Page<Inmuebles> pagina = inmueblesUserService.listAllByPage(pageable);

        List<Inmuebles> inmuebles = pagina.getContent();

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Page.class, new InmueblesSerializerCard_Page());
        mapper.registerModule(module);

        Page<Inmuebles> pageable_inmuebles_data = new PageImpl<Inmuebles>(inmuebles, pagina.getPageable(), inmuebles.size());


        try {
            return mapper.writeValueAsString(pageable_inmuebles_data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }


    }

    @GetMapping("/inmuebles/{id}")
    @ResponseBody
    public String getInmuebleById(@PathVariable("id") long id, Pageable pageable) {


        Inmuebles i = inmueblesUserService.findOneById(id);


        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Inmuebles.class, new InmueblesPublicSerializer());
        mapper.registerModule(module);


        try {
            return mapper.writeValueAsString(i);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }


    }


}