package me.dio.gxrj.desafiofinalbackenddiosantander2023.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Restaurante;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.service.RestauranteService;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.utils.ResponseUtils;

@RestController
@RequestMapping( "/restaurante" )
public class RestauranteController {
    
    private final RestauranteService restauranteService;

    public RestauranteController( RestauranteService restauranteService ) {
        this.restauranteService = restauranteService;
    }

    @PostMapping
    public ResponseEntity<Restaurante> criar( @RequestBody Restaurante restaurante ) {
        var restauranteSalvo = restauranteService.salvar( restaurante );
        return ResponseUtils.prepararPostResponse( restauranteSalvo, restauranteSalvo.getId(), HttpStatus.CREATED );
    }
}
