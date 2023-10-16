package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.repository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Cliente;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {

    /**
     * Lista todos os pedidos feitos pelo cliente, com pgto em aberto ou confirmado(pedente ou concluido)
     * @param String cpfCliente 
     * @return List<Pedido> pedidos
     */
    List<Pedido> findByCliente_Cpf( String cpfCliente );
    /**
     * Lista todos os pedidos feitos pelo cliente, com pgto em aberto ou confirmado(pedente ou concluido)
     * @param Cliente cliente
     * @return List<Pedido> pedidos
     */
    List<Pedido> findByCliente( Cliente cliente );


    /**
     * Lista pedidos feitos no restaurante em data intervalo e com o pgto confirmado.
     * Pedidos com pgto pendente são ignorados
     */
    @Query( """
        select p from Pedido p 
        where p.restaurante.cnpj = ?1 
        and p.data between ?2 and ?3 
        and p.pagamentoConfirmado = true
    """ )
    List<Pedido> findByRestaurante_CnpjBetween( 
                            String cnpjRestaurante, LocalDateTime incio, LocalDateTime fim );


    /** Lista pedidos no restaurante em data intervalo com o pgto confirmado e status pendentes
     * (não há interesse em listar pedidos com o pagamento em aberto para o restaurante).
     */
    @Query( """
        select p from Pedido p 
        where p.restaurante.cnpj = ?1 
        and p.status = 'PENDENTE'
        and p.pagamentoConfirmado = true
    """ )
    List<Pedido> findPendentesByRestaurante_Cnpj( String cnpjRestaurante );


    /** Lista pedidos no restaurante em data intervalo com o status concluido,
     * (para que um pedido seja concluido é necessário que seu pgto seja confirmado)
     */
    @Query( """
        select p from Pedido p 
        where p.restaurante.cnpj = ?1 
        and p.data between ?2 and ?3 
        and p.status = 'CONCLUIDO'
    """ )
    List<Pedido> findConcluidosByRestaurante_CnpjBetween( 
                        String cnpjRestaurante, LocalDateTime incio, LocalDateTime fim );
}
