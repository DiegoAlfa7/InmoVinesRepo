package com.diegoa.inmovinesrest.controllers.accesible;


import com.diegoa.inmovinesrest.entities.inmuebles.Inmuebles;
import com.diegoa.inmovinesrest.entities.inmuebles.PageInmueblesSerializer;
import com.diegoa.inmovinesrest.services.user.clientes.impl.ClientesUserServiceImpl;
import com.diegoa.inmovinesrest.services.user.inmuebles.impl.InmueblesUserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
public class InmueblesController {

    @Autowired
    InmueblesUserServiceImpl inmueblesUserService;

    @Autowired
    ClientesUserServiceImpl clientesUserService;


    @GetMapping("/inmuebles")
    @ResponseBody
    public String getInmuebles(Pageable pageable) {


        Page<Inmuebles> pagina = inmueblesUserService.listAllByPage(pageable);

        List<Inmuebles> inmuebles = pagina.getContent();

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Page.class, new PageInmueblesSerializer());
        mapper.registerModule(module);

        Page<Inmuebles> pageable_inmuebles_data = new PageImpl<Inmuebles>(inmuebles, pagina.getPageable(), inmuebles.size());


        try {
            return mapper.writeValueAsString(pageable_inmuebles_data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }


    }


}