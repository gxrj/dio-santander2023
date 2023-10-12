package me.dio.gxrj.desafiofinalbackenddiosantander2023.controller;


import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Pedido;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.service.PedidoService;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.utils.ResponseUtils;

@RestController
@RequestMapping( "/pedido" )
public class PedidoController {
    
    private final PedidoService service;

    public PedidoController( PedidoService service ) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Pedido> criar( @RequestBody Pedido pedido ) {
        if( pedido == null ) 
            return ResponseUtils.prepararPostResponse( null, null, null );

        var pedidoSalvo = service.salvar( pedido );
        return ResponseUtils.prepararPostResponse( pedidoSalvo, pedidoSalvo.getId(), HttpStatus.CREATED );
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<Pedido> editar( @RequestBody Pedido alteracao, @PathVariable UUID id ) {
        if( id == null || alteracao == null ) 
            return ResponseUtils.prepararPutResponse( null, null, null );

        var pedidoSalvo = service.editar( id, alteracao );
        return ResponseUtils.prepararPutResponse( pedidoSalvo, pedidoSalvo.getId(), HttpStatus.OK );
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<?> deletar( @PathVariable UUID id ) {
        var resultado = service.deletar( id );
        return ResponseUtils.prepararDeleteResponse( resultado );
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<Pedido> buscarPorId( @PathVariable UUID id ) {
        if( id == null )
            return ResponseUtils.prepararGetResponse( null );

        var pedido = service.encontrarPorId( id );
        return ResponseUtils.prepararGetResponse( pedido );
    }

    @GetMapping( "/cliente" )
    public ResponseEntity<List<Pedido>> buscarPorCliente( @RequestParam( "cpf" ) String cpf ) {
        if( cpf == null || cpf.isBlank() )
            return ResponseUtils.prepararGetResponse( null );

        var pedidos = service.buscarPorCliente( cpf );
        return ResponseUtils.prepararGetResponse( pedidos );
    }

    @GetMapping( "/pendentes/restaurante" )
    public ResponseEntity<List<Pedido>> buscarPendentesPorRestaurante( 
                                    @RequestParam( "cnpj" ) String cnpj ) {

        if( checarCnpjInvalido( cnpj ) )
            return ResponseUtils.prepararGetResponse( null );

        var pedidos = service.buscarPendentesPorRestaurante( cnpj );
        return ResponseUtils.prepararGetResponse( pedidos );
    }

    @GetMapping( "/restaurante" )
    public ResponseEntity<List<Pedido>> buscarPorRestaurante( 
                                    @RequestParam( "cnpj" ) String cnpj, 
                                    @RequestParam( "dt_inicio" ) LocalDateTime inicio, 
                                    @RequestParam( "dt_fim" ) LocalDateTime fim ) {

        if( checarCnpjInvalido( cnpj ) || checarIntervaloInvalido( inicio, fim ) )
            return ResponseUtils.prepararGetResponse( null );

        var pedidos = service.buscarPorRestauranteEntre( cnpj, inicio, fim );
        return ResponseUtils.prepararGetResponse( pedidos );
    }


    @GetMapping( "/concluidos/restaurante" )
    public ResponseEntity<List<Pedido>> buscarConcluidosPorRestaurante( 
                                    @RequestParam( "cnpj" ) String cnpj, 
                                    @RequestParam( "dt_inicio" ) LocalDateTime inicio, 
                                    @RequestParam( "dt_fim" ) LocalDateTime fim ) {

        if( checarCnpjInvalido( cnpj ) || checarIntervaloInvalido( inicio, fim ) )
            return ResponseUtils.prepararGetResponse( null );

        var pedidos = service.buscarConcluidosPorRestauranteEntre( cnpj, inicio, fim );
        return ResponseUtils.prepararGetResponse( pedidos );
    }

    
    private boolean checarCnpjInvalido( String cnpj ) {
        return cnpj == null || cnpj.isBlank();
    }

    private boolean checarIntervaloInvalido( LocalDateTime inicio, LocalDateTime fim ) {
        return inicio == null || fim == null || inicio.isAfter( fim );
    }
}
