package me.dio.gxrj.desafiofinalbackenddiosantander2023.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Pedido;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.repository.PedidoRepository;

@Service
public class PedidoService {
    
    private final PedidoRepository repository;

    public PedidoService( PedidoRepository repository ) {
        this.repository = repository;
    }

    public Pedido salvar( Pedido pedido ) {

        if( validarPedido( pedido ) ) {
            calcularTotal( pedido );
            pedido.setData( LocalDateTime.now() );
            return repository.save( pedido );
        }

        return null;
    }

    public Pedido editar( UUID id, Pedido novoPedido ) {
        return repository.findById( id )
                        .map(
                            el -> {
                                el.setPagamentoConfirmado( novoPedido.getPagamentoConfirmado() );
                                el.setStatus( novoPedido.getStatus() );
                                el.setItens( novoPedido.getItens() );
                                el.setTotal( novoPedido.getTotal() );
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

    public Pedido encontrarPorId( UUID id ) {
        return repository.findById( id ).orElse( null );
    }

    public List<Pedido> buscarPorCliente( String cpf ) {
        return repository.findByCliente_Cpf( cpf );
    }

    public List<Pedido> buscarPorRestauranteEntre( 
                            String cnpjRestaurante, LocalDateTime inicio, LocalDateTime fim ) {
        return repository.findByRestaurante_CnpjBetween( cnpjRestaurante, inicio, fim );
    }

    public List<Pedido> buscarPendentesPorRestaurante( String cnpjRestaurante ) {
        return repository.findPendentesByRestaurante_Cnpj( cnpjRestaurante );
    }

    public List<Pedido> buscarConcluidosPorRestauranteEntre( 
                            String cnpjRestaurante, LocalDateTime inicio, LocalDateTime fim ) {
        return repository.findConcluidosByRestaurante_CnpjBetween( cnpjRestaurante, inicio, fim );
    }

    private boolean validarPedido( Pedido pedido ) {
        return pedido.getItens() == null || pedido.getItens().isEmpty();
    }

    private void calcularTotal( Pedido pedido ) {
        var total = pedido.getItens()
            .parallelStream()
            .map( el -> el.getPrecoUnd() * el.getQuantidade() )
            .reduce( 0d, ( x, y ) -> x + y );
        pedido.setTotal( total );
    }
}
