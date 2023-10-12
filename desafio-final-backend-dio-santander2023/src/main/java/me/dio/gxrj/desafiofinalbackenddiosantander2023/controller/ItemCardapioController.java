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

import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.ItemCardapio;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.service.ItemCardapioService;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.utils.ResponseUtils;

@RestController
@RequestMapping( "/item" )
public class ItemCardapioController {
    
    private final ItemCardapioService service;

    public ItemCardapioController( ItemCardapioService service ) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ItemCardapio> criar( @RequestBody ItemCardapio item ) {
        var itemCriado = service.salvar( item );
        return ResponseUtils.prepararPostResponse( itemCriado, itemCriado.getId(), HttpStatus.CREATED );
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<ItemCardapio> editar( @RequestBody ItemCardapio alteracao, @PathVariable UUID id ) {
        var itemSalvo = service.editar( id, alteracao );
        return ResponseUtils.prepararPutResponse( itemSalvo, itemSalvo.getId(), HttpStatus.OK );
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<?> excluir( @PathVariable UUID id ) {
        var resultado = service.deletar( id );
        return ResponseUtils.prepararDeleteResponse( resultado );
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<ItemCardapio> obterPeloId( @PathVariable UUID id ) {
        var item = service.encontrarPorId( id );
        return ResponseUtils.prepararGetResponse( item );
    }

    @GetMapping
    public ResponseEntity<List<ItemCardapio>> obterPelaDescricao( @PathVariable String descricao ) {
        if( descricao.isBlank() )
            return ResponseUtils.prepararGetResponse( null );

        var resultados = service.encontrarItensPorDescricao( descricao );
        return ResponseUtils.prepararGetResponse( resultados );
    }

    @GetMapping( "/restaurante/{cnpj}" )
    public ResponseEntity<List<ItemCardapio>> buscarItensPeloCnpjRestaurante( 
                                                        @PathVariable String cnpjRestaurante ) {

        if( cnpjRestaurante.isBlank() )
            return ResponseUtils.prepararGetResponse( null );

        var resultados = service.encontrarItensPorCnpjRestaurante( cnpjRestaurante );
        return ResponseUtils.prepararGetResponse( resultados );
    }

    @GetMapping( "/restaurante/{login}" )
    public ResponseEntity<List<ItemCardapio>> buscarItensPeloLoginRestaurante( 
                                                        @PathVariable String loginRestaurante ) {

        if( loginRestaurante.isBlank() )
            return ResponseUtils.prepararGetResponse( null );

        var resultados = service.encontrarItensPorLoginRestaurante( loginRestaurante );
        return ResponseUtils.prepararGetResponse( resultados );
    }


    @GetMapping( "/pesquisa_por_preco" )
    public ResponseEntity<List<ItemCardapio>> buscarPelaFaixaDePreco( @PathVariable Double min, @PathVariable Double max ) {
        if( validarParametros( min, max ) )
            return ResponseUtils.prepararGetResponse( null );

        var resultados = service.buscarItensPorFaixaDePreco( min, max );
        return ResponseUtils.prepararGetResponse( resultados );
    }

    private boolean validarParametros( Double min, Double max ) {
        return ( min > 0 ) && ( max > 0 ) && ( max > min );
    }
}
