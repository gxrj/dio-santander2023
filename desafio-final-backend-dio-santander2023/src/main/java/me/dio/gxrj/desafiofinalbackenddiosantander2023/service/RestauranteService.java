package me.dio.gxrj.desafiofinalbackenddiosantander2023.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Cidade;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Restaurante;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {
    
    private final RestauranteRepository repository;
    // Todo: injetar passwordEncoder

    public RestauranteService( RestauranteRepository repository ) {
        this.repository = repository;
    }

    public Restaurante salvar( Restaurante restaurante ) {
        return repository.save( restaurante );
    }

    public Restaurante editar( UUID id, Restaurante restaurante ) {
        return repository.findById( id )
                .map(
                    el -> {
                        el.setNome( restaurante.getNome() );
                        el.setCnpj( restaurante.getCnpj() );
                        el.setSenha( restaurante.getSenha() );
                        el.setEndereco( restaurante.getEndereco() );
                        el.setDescricao( restaurante.getDescricao() );
                        el.setExpediente( restaurante.getExpediente() );
                        el.setFuncionaFeriados( restaurante.getFuncionaFeriados() );

                        return repository.save( el );
                    }
                )
                .orElse( null );
    }

    public boolean deletar( UUID id ) {
        return repository.findById( id )
                .map(
                    el -> {
                        repository.delete( el );
                        return true;
                    }
                )
                .orElse( false );
    }

    public Restaurante encontrarPorId( UUID id ) {
        return repository.findById( id ).orElse( null );
    }

    public Restaurante buscarPorCnpj( String cnpj ) {
        return repository.findByCnpj( cnpj ).orElse( null );
    }

    public Restaurante buscarPorLogin( String login ) {
        return repository.findByLogin( login ).orElse( null );
    }

    public List<Restaurante> buscarRestaurantesPorNome( String nome ) {
        return repository.findByNomeContainingIgnoreCase( nome );
    }

    public List<Restaurante> buscarRestaurantesPorBairro( String nomeBairro ) {
        return repository.findByEndereco_Bairro_NomeIgnoreCase( nomeBairro );
    }

    public List<Restaurante> buscarRestaurantesPorCidade( String nomeCidade ) {
        return repository.findByEndereco_Bairro_Cidade_NomeIgnoreCase( nomeCidade );
    }

    public List<Restaurante> buscarRestaurantesPorItem( Cidade cidade, String descricaoItem ) {
        return repository.findByDescricaoItemCardapio( cidade, descricaoItem );
    }

    public List<Restaurante> buscarAbertos( Cidade cidade ) {
        var now = LocalDateTime.now();
        return repository.findByAbertos( cidade, now.getDayOfWeek(), now.toLocalTime(), Pageable.ofSize( 5 ) );
    }
}
