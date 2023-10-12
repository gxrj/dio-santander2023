package me.dio.gxrj.desafiofinalbackenddiosantander2023.controller;


import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PutMapping( "/{id}" )
    public ResponseEntity<Restaurante> editar( @RequestBody Restaurante alteracao, @PathVariable UUID id ) {
        var restauranteSalvo = restauranteService.editar( id, alteracao );
        return ResponseUtils.prepararPutResponse( restauranteSalvo, restauranteSalvo.getId(), HttpStatus.CREATED );
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<?> deletar( @PathVariable UUID id ) {
        var resultado = restauranteService.deletar( id );
        return ResponseUtils.prepararDeleteResponse( resultado );
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<Restaurante> buscarPorId( @PathVariable UUID id ) {
        var restaurante = restauranteService.encontrarPorId( id );
        return ResponseUtils.prepararGetResponse( restaurante );
    }

    @GetMapping( "/{login}" )
    public ResponseEntity<Restaurante> buscarPorLogin( @PathVariable String login ) {
        var restaurante = restauranteService.buscarPorLogin( login );
        return ResponseUtils.prepararGetResponse( restaurante );
    }

    @GetMapping( "/{cnpj}" )
    public ResponseEntity<Restaurante> buscarPorCnpj( @PathVariable String cnpj ) {
        var restaurante = restauranteService.buscarPorCnpj( cnpj );
        return ResponseUtils.prepararGetResponse( restaurante );
    }

    @GetMapping( "/busca/{nome}" )
    public ResponseEntity<List<Restaurante>> buscarPorNome( @PathVariable String nome ) {
        var resultados = restauranteService.buscarRestaurantesPorNome( nome );
        return ResponseUtils.prepararGetResponse( resultados );
    }

    @GetMapping( "/busca/{bairro}" )
    public ResponseEntity<List<Restaurante>> buscarPorBairro( @PathVariable String bairro ) {
        var resultados = restauranteService.buscarRestaurantesPorBairro( bairro );
        return ResponseUtils.prepararGetResponse( resultados );
    }

    @GetMapping( "/busca/{cidade}" )
    public ResponseEntity<List<Restaurante>> buscarPorCidade( @PathVariable String cidade ) {
        var resultados = restauranteService.buscarRestaurantesPorCidade( cidade );
        return ResponseUtils.prepararGetResponse( resultados );
    }

    // Todo: Capturar usuário logado
    // Todo: Buscar Restaurantes na cidade do usuário logado com base na descrição do item
    // Todo: Buscar Restaurantes abertos com base na cidade do usuário logado
    // Todo: Buscar 5 Restaurantes mais próximos a abrir com base na cidade do usuário logado
}
