package me.dio.gxrj.desafiofinalbackenddiosantander2023.controller;


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

import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Cliente;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.service.ClienteService;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.utils.ResponseUtils;

// Todo: Avaliar a substituíção de ResponseUtils por org.springframework.data.rest.webmvc.ControllerUtils

@RestController
@RequestMapping( "/cliente" )
public class ClienteController {
    
    private final ClienteService clienteService;

    public ClienteController( ClienteService clienteService ) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Cliente> criar( @RequestBody Cliente cliente ) {
        var clienteSalvo = clienteService.criar( cliente );
        return ResponseUtils.prepararPostResponse( clienteSalvo, clienteSalvo.getId(), HttpStatus.CREATED );
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<Cliente> alterar( @RequestBody Cliente alteracao, @PathVariable UUID id ) {
        var cliente = clienteService.editar( id, alteracao );
        return ResponseUtils.prepararPutResponse( cliente, cliente.getId(), HttpStatus.OK );
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<Cliente> obterPorId( @PathVariable UUID id ) {
        var cliente = clienteService.encontrarPorId( id );
        return ResponseUtils.prepararGetResponse( cliente );
    }

    @GetMapping( "/{login}" )
    public ResponseEntity<Cliente> obterPorLogin( @PathVariable String login ) {
        var cliente = clienteService.encontrarPorLogin( login );
        return ResponseUtils.prepararGetResponse( cliente );
    }
    
    @DeleteMapping( "/{id}" )
    public ResponseEntity<?> deletar( @PathVariable UUID id ) {
        var resultado = clienteService.deletar( id );
        return ResponseUtils.prepararDeleteResponse( resultado );
    }
}
