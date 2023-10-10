package me.dio.gxrj.desafiofinalbackenddiosantander2023.service;


import java.util.List;

import org.springframework.stereotype.Service;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Bairro;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.repository.BairroRepository;

@Service
public class BairroService {
    
    private final BairroRepository repository;

    public BairroService( BairroRepository repository ) {
        this.repository = repository;
    }

    public void salvar( Bairro bairro ) {
        repository.save( bairro );
    }

    public void salvarMultiplos( List<Bairro> bairros ) {
        repository.saveAll( bairros );
    }

    public List<Bairro> encontrarBairrosPorCidade( String nomeCidade ) {
        return repository.findByCidade_Nome( nomeCidade );
    }

    public Bairro encontrarBairro( String nomeBairro ) {
        return repository.findByNome( nomeBairro ).orElse( null );
    }

    public void excluir( Bairro bairro ) {
        repository.delete( bairro );
    }
}
