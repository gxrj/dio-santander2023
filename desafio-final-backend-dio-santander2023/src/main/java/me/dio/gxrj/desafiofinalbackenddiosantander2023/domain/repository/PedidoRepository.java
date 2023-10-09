package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.repository;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {

    // Todo: listar por cliente
    // Todo: listar pedidos feitos no restaurante por data 
    // Todo: listar pedidos pendentes no restaurante por data
    // Todo: listar pedidos concluidos no restaurante por data
}
