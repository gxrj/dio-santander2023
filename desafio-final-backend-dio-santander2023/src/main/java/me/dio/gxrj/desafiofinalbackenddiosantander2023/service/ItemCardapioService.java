package me.dio.gxrj.desafiofinalbackenddiosantander2023.service;


import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.ItemCardapio;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Restaurante;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.repository.ItemCardapioRepository;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.repository.RestauranteRepository;

@Service
public class ItemCardapioService {
    
    private final ItemCardapioRepository repository;
    private final RestauranteRepository restauranteRepository;

    public ItemCardapioService( ItemCardapioRepository repository, RestauranteRepository restauranteRepository ) {
        this.repository = repository;
        this.restauranteRepository = restauranteRepository;
    }

    public ItemCardapio salvar( ItemCardapio item ) {
        return repository.save( item );
    }

    public List<ItemCardapio> salvarMultiplos( List<ItemCardapio> itens ) {
        return repository.saveAll( itens );
    }

    public ItemCardapio editar( UUID id, ItemCardapio novoItem ) {
        return repository.findById( id )
                    .map(
                        el -> {
                            el.setPreco( el.getPreco() );
                            el.setDescricao( novoItem.getDescricao() );
                            el.setEmEstoque( novoItem.getEmEstoque() );
                            return el;
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

    public ItemCardapio encontrarPorId( UUID id ) {
        return repository.findById( id ).orElse( null );
    }

    public List<ItemCardapio> encontrarItensPorDescricao( String descricao ) {
        return repository.findByDescricao( descricao );
    }

    public List<ItemCardapio> buscarItensPorFaixaDePreco( Double min, Double max ) {
        return repository.findByPrecoBetween( min, max );
    }

    public List<ItemCardapio> encontrarItensPorCnpjRestaurante( String cnpjRestaurante ) {
        var restaurante = restauranteRepository.findByCnpj( cnpjRestaurante ).get();
        return itensPorRestaurante( restaurante );
    }

    public List<ItemCardapio> encontrarItensPorLoginRestaurante( String loginRestaurante ) {
        var restaurante = restauranteRepository.findByLogin( loginRestaurante ).get();
        return itensPorRestaurante( restaurante );
    }

    private List<ItemCardapio> itensPorRestaurante( Restaurante restaurante ) {
        if( restaurante == null ) 
            return null;

        return repository.findByRestaurante( restaurante );
    }

}
