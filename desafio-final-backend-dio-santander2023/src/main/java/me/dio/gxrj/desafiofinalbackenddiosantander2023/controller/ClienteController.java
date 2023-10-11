package me.dio.gxrj.desafiofinalbackenddiosantander2023.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.service.ClienteService;

@RestController
@RequestMapping( "/cliente" )
public class ClienteController {
    
    private final ClienteService clienteService;

    public ClienteController( ClienteService clienteService ) {
        this.clienteService = clienteService;
    }
}
