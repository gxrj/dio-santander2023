package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.repository;


import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Bairro;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Cidade;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, UUID> {
    List<Restaurante> findByEndereco_Bairro( Bairro bairro );
    List<Restaurante> findByEndereco_Bairro_Cidade( Cidade cidade );
    // Todo: listar pelo item
    // Todo: listar retaurantes abertos
    // Todo: listar restaurantes mais proximos de abrir em determinado intervalo
}
