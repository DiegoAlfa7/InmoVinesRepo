package com.diegoa.inmovinesrest.controllers.secured.clientes;


import com.diegoa.inmovinesrest.entities.clientes.Clientes;
import com.diegoa.inmovinesrest.services.clientes.impl.ClientesServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Controlador Restful encargado de la distribución de datos
 * de inmuebles para las aplicaciones identificadas como administrador.
 *
 * @author Diego Alfaro
 */
@CrossOrigin()
@RestController
@RequestMapping("admin")
public class AdminClientesController {

    @Autowired
    ClientesServiceImpl clientesService;

    /**
     * Esta operación del controlador se encarga de devolver en una Página (Page) de <b> los Clientes que haya en la base de datos.</b>
     * agrupándolos en función del tipo de página que se pida en los parámetros de la URL.
     *
     * @return ResponseEntity<Page < Clientes>> Respuesta Http con la representación string del JSON de la página de los clientes del la BBDD.
     * @throws JsonProcessingException si ocurre un error durante la serialización del objeto.
     * @apiNote <b>ENDPOINT: .../admin/clientes/page[?page={page}&size={size}&sort={property},asc|desc]</b>
     */
    @RequestMapping(value = "/clientes/page", produces = {"application/json"}, method = {RequestMethod.GET, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Page<Clientes>> getClientesPage(Pageable pageable) {


        Page<Clientes> pagina = clientesService.listAllByPage(pageable);


        return new ResponseEntity(pagina, HttpStatus.OK);


    }

    /**
     * Esta operación del controlador se encarga de devolver en una Lista genérica <b>todos los Clientes que haya en la base de datos.</b>
     *
     * @return ResponseEntity<String> Respuesta Http con la representación string del JSON del array de todos los clientes del la BBDD.
     * @throws JsonProcessingException si ocurre un error durante la serialización del objeto.
     * @apiNote <b>ENDPOINT: .../admin/clientes</b>
     * `
     */
    @RequestMapping(value = "/clientes", produces = {"application/json"}, method = {RequestMethod.GET, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<List<Clientes>> getClientesList() {

        List<Clientes> pagina = clientesService.listAll();

        return new ResponseEntity(pagina, HttpStatus.OK);

    }

    /**
     * Esta operación del controlador se encarga de una única entidad de tipo Clientes. Realiza una búsqueda en los Repositorios en función de
     * el ID facilitado.
     *
     * @return ResponseEntity<String> Respuesta Http con la representación string del JSON del objeto del cliente en cuestión.
     * @throws JsonProcessingException si ocurre un error durante la serialización del objeto.
     * @apiNote <b>ENDPOINT: .../admin/cliente/{id}</b>
     * `
     */
    @RequestMapping(value = "/clientes/{id}", produces = {"application/json"}, method = {RequestMethod.GET, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Clientes> getClienteById(@PathVariable("id") long id) {

        Clientes i = clientesService.findOneById(id);

        if (i != null) {

            return new ResponseEntity(i, HttpStatus.OK);

        } else {

            throw new RuntimeException("El cliente con id: " + id + " no ha devuelto ningún resultado");
        }

    }

    @RequestMapping(value = "/clientes/nuevo", consumes = "application/json", method = {RequestMethod.POST, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Clientes> addCliente(@RequestBody @Valid Clientes clientes) {

        Clientes clienteCreado = clientesService.create(clientes);

        return new ResponseEntity(clienteCreado, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/clientes/modificar", produces = "application/json", method = {RequestMethod.PUT, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Clientes> updateCliente(@RequestBody @Valid Clientes clientes) {

        Clientes clienteModificado = clientesService.update(clientes);

        return new ResponseEntity(clienteModificado, HttpStatus.OK);

    }

    @RequestMapping(value = "/clientes/{id}", produces = "application/json", method = {RequestMethod.DELETE, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Void> deleteAgente(@PathVariable("id") long id) {

        this.clientesService.delete(id);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/clientes/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, method = {RequestMethod.POST, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Clientes> getClienteByEmailPass(@RequestParam Map<String, String> map) {

        String email = map.get("email");
        String pass = map.get("pass");

        if (!email.isEmpty() && !pass.isEmpty()) {

            this.clientesService.listByEmailAndPass(email, pass);
            return new ResponseEntity(HttpStatus.OK);
        } else {

            throw new RuntimeException("Los parametros de entrada no son validos");
        }


    }

    @RequestMapping(value = "clientes/nuevo?idAgente={id}&idAgenteEntrada={id}&interes{id}", consumes = "application/json", method = {RequestMethod.POST, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Clientes> addCliente(
            @RequestBody @Valid Clientes clientes,
            @RequestParam long idAgente,
            @RequestParam long idAgenteEntrada,
            @RequestParam long idInteres
    ) {
        Clientes clientesCreado = clientesService.create(clientes);



    }


}

