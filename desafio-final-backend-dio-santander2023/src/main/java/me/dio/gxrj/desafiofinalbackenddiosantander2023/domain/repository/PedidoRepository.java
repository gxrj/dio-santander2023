package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.repository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Cliente;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Pedido;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Restaurante;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {

    List<Pedido> findByCliente( Cliente cliente );
    // lista pedidos feitos no restaurante em data intervalo 
    @Query( "select p from Pedido p where p.restaurante = ?1 and p.data between ?2 and ?3 and p.pagamentoConfirmado = true" )
    List<Pedido> findByRestauranteBetween( Restaurante restaurante, LocalDateTime incio, LocalDateTime fim );
    // lista pedidos pendentes no restaurante
    @Query( "select p from Pedido p where p.restaurante = ?1 and p.status = 'pendente' and p.pagamentoConfirmado = true" )
    List<Pedido> findPendentesByRestaurante( Restaurante restaurante );
    // lista pedidos concluidos no restaurante por data
    @Query( "select p from Pedido p where p.restaurante = ?1 and p.data between ?2 and ?3 and p.status = 'concluido'" )
    List<Pedido> findConcluidosByRestauranteBetween( Restaurante restaurante, LocalDateTime incio, LocalDateTime fim );
}
