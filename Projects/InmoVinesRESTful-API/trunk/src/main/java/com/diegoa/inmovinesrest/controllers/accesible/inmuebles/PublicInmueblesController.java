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
 * de Inmuebles para las aplicaciones identificadas como usuario.
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
public class PublicInmueblesController {

    @Autowired
    InmueblesServiceImpl inmueblesUserService;


    /**
     * Esta operación del controlador se encarga de listar dentro de un pageable los <b>Inmuebles</b> de la base de datos, agrupándolos en
     * función del tipo de página que se pida en los parámetros de la URL.
     * @apiNote <b>ENDPOINT: .../user/inmuebles/page[?page={page}&size={size}&sort={objectProperty},asc|desc]</b>
     * @param pageable Se refiere a la PageRequest que se espera que venga junto con la petición, con los parámetros
     * 'page', 'size' y 'sort' debidamente introducidos
     *
     * @return ResponseEntity<String> Respuesta Http con el la representación string del JSON del objeto de la <b>Page<\Inmuebles\></b>
     * @throws JsonProcessingException si ocurre un error durante la serialización del objeto.
     */
    @RequestMapping(value = "/inmuebles/page", produces = {"application/json"}, method = {RequestMethod.GET, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<String> getInmueblesPage(Pageable pageable) throws JsonProcessingException {


        Page<Inmuebles> pagina = inmueblesUserService.listAllByPage(pageable);

        List<Inmuebles> inmuebles = pagina.getContent();

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Page.class, new InmueblesPublicSerializerCard_Page());
        mapper.registerModule(module);


        String JsonPage = mapper.writeValueAsString(pagina);

        return new ResponseEntity<>(JsonPage, HttpStatus.OK);




    }

    /**
     * Esta operación del controlador se encarga de una única entidad de tipo Inmuebles. Realiza una búsqueda en los Repositorios en función de
     * el ID facilitado, oculta la información sensible del Inmueble y devuelve su representación JSON.
     *
     * @apiNote <b>ENDPOINT: .../user/inmuebles/{id}</b>
     * @return ResponseEntity<String> Respuesta Http con la representación string del JSON del Inmueble de la BBDD.
     * @throws JsonProcessingException si ocurre un error durante la serialización del objeto.
     */
    @GetMapping(value = "/inmuebles/{id}", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<String> getInmuebleById( @PathVariable("id") long id,  Pageable pageable) throws JsonProcessingException {


        Inmuebles i = inmueblesUserService.findOneById(id);
        if(i == null){throw new RuntimeException("El Inmueble con id: "+id+" no ha devuelto ningún resultado");}

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Inmuebles.class, new InmueblesPublicSerializer());
        mapper.registerModule(module);
        return new ResponseEntity<String>(mapper.writeValueAsString(i), HttpStatus.OK);

    }



}