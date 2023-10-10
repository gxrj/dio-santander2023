package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.service;


import java.util.List;

import org.springframework.stereotype.Service;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Cidade;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Estado;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.repository.CidadeRepository;

@Service
public class CidadeService {
    
    private final CidadeRepository repository;

    public CidadeService( CidadeRepository repository ) {
        this.repository = repository;
    }

    public void salvar( Cidade cidade ) {
        repository.save( cidade );
    }

    public void salvarMultiplas( List<Cidade> cidades ) {
        repository.saveAll( cidades );
    }

    public Cidade encontrarCidadePorNome( String nomeCidade ) {
        return repository.findByNome( nomeCidade ).orElse( null );
    }

    public List<Cidade> encontrarCidadesPorEstado( String nomeEstado ) {
        return repository.findByEstado( Estado.fromString( nomeEstado ) );
    }

    public void excluir( Cidade cidade ) {
        repository.delete( cidade );
    }
}
