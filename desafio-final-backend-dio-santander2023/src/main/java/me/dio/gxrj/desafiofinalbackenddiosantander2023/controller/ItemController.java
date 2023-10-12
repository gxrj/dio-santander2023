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

import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Item;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.service.ItemService;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.utils.ResponseUtils;

@RestController
@RequestMapping( "/item" )
public class ItemController {
    
    private final ItemService service;

    public ItemController( ItemService service ) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Item> criar( @RequestBody Item item ) {
        if( item == null )
            return ResponseUtils.prepararPostResponse( null, null, null );

        var itemCriado = service.salvar( item );
        return ResponseUtils.prepararPostResponse( itemCriado, itemCriado.getId(), HttpStatus.CREATED );
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<Item> editar( @RequestBody Item alteracao, @PathVariable UUID id ) {
        if( alteracao == null || id == null )
            return ResponseUtils.prepararPutResponse( null, null, null );

        var itemSalvo = service.editar( id, alteracao );
        return ResponseUtils.prepararPutResponse( itemSalvo, itemSalvo.getId(), HttpStatus.OK );
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<?> excluir( @PathVariable UUID id ) {
        var resultado = service.deletar( id );
        return ResponseUtils.prepararDeleteResponse( resultado );
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<Item> obterPeloId( @PathVariable UUID id ) {
        if( id == null )
            return ResponseUtils.prepararGetResponse( null );

        var item = service.encontrarPorId( id );
        return ResponseUtils.prepararGetResponse( item );
    }

    @GetMapping
    public ResponseEntity<List<Item>> obterPelaDescricao( @PathVariable String descricao ) {
        if( descricao == null || descricao.isBlank() )
            return ResponseUtils.prepararGetResponse( null );

        var resultados = service.encontrarItensPorDescricao( descricao );
        return ResponseUtils.prepararGetResponse( resultados );
    }

    @GetMapping( "/restaurante" )
    public ResponseEntity<List<Item>> buscarPeloCnpjRestaurante( @PathVariable( "cnpj" ) String cnpjRestaurante ) {
        if( cnpjRestaurante == null || cnpjRestaurante.isBlank() )
            return ResponseUtils.prepararGetResponse( null );

        var resultados = service.encontrarItensPorCnpjRestaurante( cnpjRestaurante );
        return ResponseUtils.prepararGetResponse( resultados );
    }

    @GetMapping( "/restaurante" )
    public ResponseEntity<List<Item>> buscarPeloLoginRestaurante( @PathVariable( "login" ) String loginRestaurante ) {
        if( loginRestaurante == null || loginRestaurante.isBlank() )
            return ResponseUtils.prepararGetResponse( null );

        var resultados = service.encontrarItensPorLoginRestaurante( loginRestaurante );
        return ResponseUtils.prepararGetResponse( resultados );
    }

    @GetMapping
    public ResponseEntity<List<Item>> buscarPelaFaixaDePreco( @PathVariable Double min, @PathVariable Double max ) {
        if( validarParametros( min, max ) )
            return ResponseUtils.prepararGetResponse( null );

        var resultados = service.buscarItensPorFaixaDePreco( min, max );
        return ResponseUtils.prepararGetResponse( resultados );
    }

    private boolean validarParametros( Double min, Double max ) {
        return ( min != null && max != null ) && ( min > 0 && max > 0 && max > min );
    }
}
