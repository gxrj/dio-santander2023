package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.repository;


import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Item;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Restaurante;

public interface ItemRepository extends JpaRepository<Item, UUID> {
    List<Item> findByRestaurante( Restaurante restaurante );
    List<Item> findByDescricao( String descricao );
}
