package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.repository;


import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.ItemCardapio;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Restaurante;

public interface ItemCardapioRepository extends JpaRepository<ItemCardapio, UUID> {
    List<ItemCardapio> findByDescricao( String descricao );
    List<ItemCardapio> findByRestaurante( Restaurante restaurante );
    List<ItemCardapio> findByPrecoBetween( Double min, Double max ); // Todo: Testar
}
