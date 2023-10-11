package me.dio.gxrj.desafiofinalbackenddiosantander2023.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.service.PedidoService;

@RestController
@RequestMapping( "/pedido" )
public class PedidoController {
    
    private final PedidoService service;

    public PedidoController( PedidoService service ) {
        this.service = service;
    }
}
