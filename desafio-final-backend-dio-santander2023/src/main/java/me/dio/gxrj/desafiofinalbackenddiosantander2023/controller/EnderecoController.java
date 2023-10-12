package me.dio.gxrj.desafiofinalbackenddiosantander2023.controller;


import java.util.List;

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

import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Bairro;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Cidade;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.service.EnderecoService;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.utils.ResponseUtils;

@RestController
@RequestMapping( "/endereco" )
public class EnderecoController {
    
    private final EnderecoService enderecoService;

    public EnderecoController( EnderecoService enderecoService ) {
        this.enderecoService = enderecoService;
    }

    @PostMapping( "/bairro" )
    public ResponseEntity<Bairro> criarBairro( @RequestBody Bairro bairro ) {
        if( bairro == null )
            return ResponseUtils.prepararPostResponse( null, null, null );

        var bairroSalvo = enderecoService.salvarBairro( bairro );
        return ResponseUtils.prepararPostResponse( bairroSalvo, bairroSalvo.getId(), HttpStatus.CREATED );
    }

    @PostMapping( "/cidade" )
    public ResponseEntity<Cidade> criarCidade( @RequestBody Cidade cidade ) {
        if( cidade == null )
            return ResponseUtils.prepararPostResponse( null, null, null );

        var cidadeSalva = enderecoService.salvarCidade( cidade );
        return ResponseUtils.prepararPostResponse( cidadeSalva, cidadeSalva.getId(), HttpStatus.CREATED );
    }

    @PostMapping( "/bairros" )
    public ResponseEntity<List<Bairro>> criarBairros( @RequestBody List<Bairro> bairros ) {
        if( bairros == null || bairros.size() == 0 )
            return ResponseUtils.prepararPostResponse( null, null, null );

        var listaBairros = enderecoService.salvarMultiplosBairros( bairros );
        return ResponseUtils.prepararGetResponse( listaBairros );
    }

    @PostMapping( "/cidades" )
    public ResponseEntity<List<Cidade>> criarCidades( @RequestBody List<Cidade> cidades ) {
        if( cidades == null || cidades.size() == 0)
            return ResponseUtils.prepararPostResponse( null, null, null );

        var listaCidades = enderecoService.salvarMultiplasCidades( cidades );
        return ResponseUtils.prepararGetResponse( listaCidades );
    }

    @GetMapping( "/bairro/{id}" )
    public ResponseEntity<Bairro> obterBairroPorId( @PathVariable Long id ) {
        var bairro = enderecoService.encontrarBairroPorId( id );
        return ResponseUtils.prepararGetResponse( bairro );
    }

    @GetMapping( "/cidade/{id}" )
    public ResponseEntity<Cidade> obterCidadePorId( @PathVariable Long id ) {
        var cidade = enderecoService.encontrarCidadePorId( id );
        return ResponseUtils.prepararGetResponse( cidade );
    }

    @GetMapping( "/bairros" )
    public ResponseEntity<List<Bairro>> obterBairrosPorCidade( @RequestParam( "cidade" ) String nomeCidade ) {
        var bairros = enderecoService.encontrarBairrosPorCidade( nomeCidade );
        return ResponseUtils.prepararGetResponse( bairros );
    }

    @GetMapping( "/cidades" )
    public ResponseEntity<List<Cidade>> obterCidadesPorEstado( @RequestParam( "estado" ) String nomeEstado ) {
        var cidades = enderecoService.encontrarCidadesPorEstado( nomeEstado );
        return ResponseUtils.prepararGetResponse( cidades );
    }

    @GetMapping( "/bairro" )
    public ResponseEntity<List<Bairro>> procurarBairroPorNome( @RequestParam( "nome" ) String nomeBairro ) {
        var resultados = enderecoService.encontrarBairroPorNome( nomeBairro );
        return ResponseUtils.prepararGetResponse( resultados );
    }

    @GetMapping( "/cidade" )
    public ResponseEntity<List<Cidade>> procurarCidadePorNome( @RequestParam( "nome" ) String nomeCidade ) {
        var resultados = enderecoService.encontrarCidadePorNome( nomeCidade );
        return ResponseUtils.prepararGetResponse( resultados );
    }

    @PutMapping( "/bairro/{id}" )
    public ResponseEntity<Bairro> editarBairro( @RequestBody Bairro alteracao, @PathVariable Long id ) {
        if( alteracao == null || id == null )
            return ResponseUtils.prepararPutResponse( null, null, null );

        var bairroSalvo = enderecoService.editarBairro( id, alteracao );
        return ResponseUtils.prepararPutResponse( bairroSalvo, bairroSalvo.getId(), HttpStatus.OK );
    }

    @PutMapping( "/cidade/{id}" )
    public ResponseEntity<Cidade> editarCidade( @RequestBody Cidade alteracao, @PathVariable Long id ) {
        if( alteracao == null || id == null )
            return ResponseUtils.prepararPutResponse( null, null, null );

        var cidadeSalva = enderecoService.editarCidade( id, alteracao );
        return ResponseUtils.prepararPutResponse( cidadeSalva, cidadeSalva.getId(), HttpStatus.OK );
    }

    @DeleteMapping( "/bairro/{id}" )
    public ResponseEntity<?> deletarBairro( @PathVariable Long id ) {
        if( id == null )
            return ResponseUtils.prepararDeleteResponse( null );

        var resultado = enderecoService.excluirBairro( id );
        return ResponseUtils.prepararDeleteResponse( resultado );
    }

    @PutMapping( "/cidade/{id}" )
    public ResponseEntity<?> deletarCidade( @PathVariable Long id ) {
        if( id == null )
            return ResponseUtils.prepararDeleteResponse( null );

        var resultado = enderecoService.excluirCidade( id );
        return ResponseUtils.prepararDeleteResponse( resultado );
    }
}
