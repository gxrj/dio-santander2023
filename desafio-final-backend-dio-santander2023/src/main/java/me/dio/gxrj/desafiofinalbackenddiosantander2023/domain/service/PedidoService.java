package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.service;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Pedido;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.repository.PedidoRepository;

@Service
public class PedidoService {
    
    private final PedidoRepository repository;

    public PedidoService( PedidoRepository repository ) {
        this.repository = repository;
    }

    public void salvar( Pedido pedido ) {
        repository.save( pedido );
    }

    public void salvarMultiplos( List<Pedido> pedidos ) {
        repository.saveAll( pedidos );
    }

    public void deletar( Pedido pedido ) {
        repository.delete( pedido );
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
}
