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

    public void salvarBairro( Bairro bairro ) {
        bairroRepository.save( bairro );
    }

    public void salvarCidade( Cidade cidade ) {
        cidadeRepository.save( cidade );
    }

    public void salvarMultiplosBairros( List<Bairro> bairros ) {
        bairroRepository.saveAll( bairros );
    }

    public void salvarMultiplasCidades( List<Cidade> cidades ) {
        cidadeRepository.saveAll( cidades );
    }

    public Bairro encontrarBairroPorId( Long id ) {
        return bairroRepository.findById( id ).orElse( null );
    }

    public Cidade encontrarCidadePorId( Long id ) {
        return cidadeRepository.findById( id ).orElse( null );
    }

    public List<Bairro> encontrarBairrosPorCidade( String nomeCidade ) {
        return bairroRepository.findByCidade_Nome( nomeCidade );
    }

    public Bairro encontrarBairro( String nomeBairro ) {
        return bairroRepository.findByNome( nomeBairro ).orElse( null );
    }

    public Cidade encontrarCidadePorNome( String nomeCidade ) {
        return cidadeRepository.findByNome( nomeCidade ).orElse( null );
    }

    public List<Cidade> encontrarCidadesPorEstado( String nomeEstado ) {
        return cidadeRepository.findByEstado( Estado.fromString( nomeEstado ) );
    }

    public void excluirBairro( Bairro bairro ) {
        bairroRepository.delete( bairro );
    }

    public void excluirCidade( Cidade cidade ) {
        cidadeRepository.delete( cidade );
    }
}
