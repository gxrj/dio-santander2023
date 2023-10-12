package me.dio.gxrj.desafiofinalbackenddiosantander2023.service;


import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Item;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Restaurante;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.repository.ItemRepository;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.repository.RestauranteRepository;

@Service
public class ItemService {
    
    private final ItemRepository repository;
    private final RestauranteRepository restauranteRepository;

    public ItemService( ItemRepository repository, RestauranteRepository restauranteRepository ) {
        this.repository = repository;
        this.restauranteRepository = restauranteRepository;
    }

    public Item salvar( Item item ) {
        return repository.save( item );
    }

    public List<Item> salvarMultiplos( List<Item> itens ) {
        return repository.saveAll( itens );
    }

    public Item editar( UUID id, Item novoItem ) {
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

    public Item encontrarPorId( UUID id ) {
        return repository.findById( id ).orElse( null );
    }

    public List<Item> encontrarItensPorDescricao( String descricao ) {
        return repository.findByDescricao( descricao );
    }

    public List<Item> buscarItensPorFaixaDePreco( Double min, Double max ) {
        return repository.findByPrecoBetween( min, max );
    }

    public List<Item> encontrarItensPorCnpjRestaurante( String cnpjRestaurante ) {
        var restaurante = restauranteRepository.findByCnpj( cnpjRestaurante ).get();
        return itensPorRestaurante( restaurante );
    }

    public List<Item> encontrarItensPorLoginRestaurante( String loginRestaurante ) {
        var restaurante = restauranteRepository.findByLogin( loginRestaurante ).get();
        return itensPorRestaurante( restaurante );
    }

    private List<Item> itensPorRestaurante( Restaurante restaurante ) {
        if( restaurante == null ) 
            return null;

        return repository.findByRestaurante( restaurante );
    }

}