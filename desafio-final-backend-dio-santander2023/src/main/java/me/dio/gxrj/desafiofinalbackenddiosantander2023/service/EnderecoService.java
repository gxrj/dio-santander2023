package me.dio.gxrj.desafiofinalbackenddiosantander2023.service;


import java.util.List;

import org.springframework.stereotype.Service;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Bairro;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Cidade;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Estado;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.repository.BairroRepository;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.repository.CidadeRepository;

@Service
public class EnderecoService {
    
    private final BairroRepository bairroRepository;
    private final CidadeRepository cidadeRepository;

    public EnderecoService( BairroRepository bairroRepository, CidadeRepository cidadeRepository ) {
        this.bairroRepository = bairroRepository;
        this.cidadeRepository = cidadeRepository;
    }

    public Bairro salvarBairro( Bairro bairro ) {
        return bairroRepository.save( bairro );
    }

    public Cidade salvarCidade( Cidade cidade ) {
        return cidadeRepository.save( cidade );
    }

    public List<Bairro> salvarMultiplosBairros( List<Bairro> bairros ) {
        return bairroRepository.saveAll( bairros );
    }

    public List<Cidade> salvarMultiplasCidades( List<Cidade> cidades ) {
        return cidadeRepository.saveAll( cidades );
    }

    public Bairro encontrarBairroPorId( Long id ) {
        return bairroRepository.findById( id ).orElse( null );
    }

    public Cidade encontrarCidadePorId( Long id ) {
        return cidadeRepository.findById( id ).orElse( null );
    }

    public List<Bairro> encontrarBairroPorNome( String nomeBairro ) {
        return bairroRepository.findByNomeContainingIgnoreCase( nomeBairro );
    }

    public List<Cidade> encontrarCidadePorNome( String nomeCidade ) {
        return cidadeRepository.findByNomeIgnoreCase( nomeCidade );
    }

    public List<Bairro> encontrarBairrosPorCidade( String nomeCidade ) {
        return bairroRepository.findByCidade_NomeIgnoreCase( nomeCidade );
    }

    public List<Cidade> encontrarCidadesPorEstado( String nomeEstado ) {
        return cidadeRepository.findByEstado( Estado.fromString( nomeEstado ) );
    }

    public Bairro editarBairro( Long bairroId, Bairro novoBairro ) {
        return bairroRepository.findById( bairroId )
                            .map( 
                                el -> {
                                    el.setNome( novoBairro.getNome() );
                                    el.setCidade( novoBairro.getCidade() );
                                    return el;
                                } 
                            )
                            .orElse( null );
    }

    public Cidade editarCidade( Long cidadeId, Cidade novaCidade ) {
        return cidadeRepository.findById( cidadeId )
                            .map( 
                                el -> {
                                    el.setNome( novaCidade.getNome() );
                                    el.setEstado( novaCidade.getEstado() );
                                    return el;
                                } 
                            )
                            .orElse( null );
    }

    public boolean excluirBairro( Long bairroId ) {
        return bairroRepository.findById( bairroId )
                .map( 
                    el -> {
                        bairroRepository.delete( el );
                        return true;
                    } 
                )
                .orElse( false );
    }

    public boolean excluirCidade( Long cidadeId ) {
        return cidadeRepository.findById( cidadeId )
                .map( 
                    el -> {
                        cidadeRepository.delete( el );
                        return true;
                    }
                )
                .orElse( false );
    }
}
