package com.diegoa.inmovinesrest.controllers.secured.agentes;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:8087","http://localhost:8080" })
@RestController
@RequestMapping("user")
public class AdminAgentesController {
}
