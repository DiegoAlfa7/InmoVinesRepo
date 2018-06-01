package com.diegoa.inmovinesrest.controllers.secured.clientes;


import com.diegoa.inmovinesrest.entities.clientes.Clientes;
import com.diegoa.inmovinesrest.services.clientes.impl.ClientesServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  Controlador Restful encargado de la distribución de datos
 *  de inmuebles para las aplicaciones identificadas como administrador.
 * @author Diego Alfaro
 */
@CrossOrigin(origins = {"http://localhost:8087","http://localhost:8080" })
@RestController
@RequestMapping("admin")
public class AdminClientesController {



    @Autowired
    ClientesServiceImpl clientesService;


    /**
     * Esta operación del controlador se encarga de devolver en una Página (Page) de <b> los Clientes que haya en la base de datos.</b>
     * agrupándolos en función del tipo de página que se pida en los parámetros de la URL.
     *
     * @apiNote <b>ENDPOINT: .../admin/clientes/page[?page={page}&size={size}&sort={property},asc|desc]</b>
     * @return ResponseEntity<Page<Clientes>> Respuesta Http con la representación string del JSON de la página de los clientes del la BBDD.
     * @throws JsonProcessingException si ocurre un error durante la serialización del objeto.
     */
    @RequestMapping(value = "/clientes/page", produces = {"application/json"}, method = {RequestMethod.GET, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Page<Clientes>> getClientesPage(Pageable pageable) throws JsonProcessingException {


        Page<Clientes> pagina = clientesService.listAllByPage(pageable);


        return new ResponseEntity(pagina, HttpStatus.OK);




    }

    /**
     * Esta operación del controlador se encarga de devolver en una Lista genérica <b>todos los Clientes que haya en la base de datos.</b>
     *
     * @apiNote <b>ENDPOINT: .../admin/clientes</b>
     *`
     * @return ResponseEntity<String> Respuesta Http con la representación string del JSON del array de todos los clientes del la BBDD.
     * @throws JsonProcessingException si ocurre un error durante la serialización del objeto.
     */
    @RequestMapping(value = "/clientes", produces = {"application/json"}, method = {RequestMethod.GET, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<List<Clientes>> getClientesList() throws JsonProcessingException {


        List<Clientes> pagina = clientesService.listAll();


        return new ResponseEntity(pagina, HttpStatus.OK);




    }

    /**
     * Esta operación del controlador se encarga de una única entidad de tipo Clientes. Realiza una búsqueda en los Repositorios en función de
     * el ID facilitado.
     *
     * @apiNote <b>ENDPOINT: .../admin/cliente/{id}</b>
     *`
     * @return ResponseEntity<String> Respuesta Http con la representación string del JSON del objeto del cliente en cuestión.
     * @throws JsonProcessingException si ocurre un error durante la serialización del objeto.
     */
    @RequestMapping(value = "/clientes/{id}", produces = {"application/json"}, method = {RequestMethod.GET, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Clientes> getClienteById( @PathVariable("id") long id) throws JsonProcessingException {


        Clientes i = clientesService.findOneById(id);
        if(i != null){

            return new ResponseEntity(i, HttpStatus.OK);

        }else{

            throw new RuntimeException("El cliente con id: "+id+" no ha devuelto ningún resultado");

        }



    }


}
