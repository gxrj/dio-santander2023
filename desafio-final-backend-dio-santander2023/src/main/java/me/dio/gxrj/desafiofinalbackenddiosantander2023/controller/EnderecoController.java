package me.dio.gxrj.desafiofinalbackenddiosantander2023.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.service.EnderecoService;

@RestController
@RequestMapping( "/endereco" )
public class EnderecoController {
    
    private final EnderecoService enderecoService;

    public EnderecoController( EnderecoService enderecoService ) {
        this.enderecoService = enderecoService;
    }
}
