package me.dio.gxrj.desafiofinalbackenddiosantander2023.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.service.RestauranteService;

@RestController
@RequestMapping( "/restaurante" )
public class RestauranteController {
    
    private final RestauranteService restauranteService;

    public RestauranteController( RestauranteService restauranteService ) {
        this.restauranteService = restauranteService;
    }
}
