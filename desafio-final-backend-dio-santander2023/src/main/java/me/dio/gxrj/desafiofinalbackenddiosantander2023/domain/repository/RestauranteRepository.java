package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.repository;


import java.time.DayOfWeek;
import java.time.LocalTime;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Bairro;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Cidade;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, UUID> {
    Optional<Restaurante> findByCnpj( String cnpj );
    Optional<Restaurante> findByLogin( String login );
    List<Restaurante> findByNomeLike( String nome );
    List<Restaurante> findByEndereco_Bairro( Bairro bairro );
    List<Restaurante> findByEndereco_Bairro_Cidade( Cidade cidade );

    // lista retaurantes da cidade que apresentam o item pesquisado no cardápio
    @Query( """
        select r from Restaurante r 
        left join ItemCardapio i 
        where i.restaurante = r 
        and i.descricao like %?2
        and r.endereco.bairro.cidade = ?1
    """ )
    List<Restaurante> findByDescricaoItem( Cidade cidade, String descricaoItem );

    // lista retaurantes da cidade abertos
    @Query( """
        select r from Restaurante r 
        join r.horarioFuncionamento f 
        where f.dia = ?2 
        and r.endereco.bairro.cidade = ?1
        and f.horarioFechamento > ?3
    """ )
    List<Restaurante> findByAbertos( Cidade cidade, DayOfWeek dia, LocalTime horaAtual );

    // lista restaurantes da cidade que vão abrir
    @Query( """
        select r from Restaurante r 
        join r.horarioFuncionamento f 
        where f.dia = ?2 
        and r.endereco.bairro.cidade = ?1
        and f.horarioAbertura >= ?3
        order by f.horarioAbertura
    """ )
    List<Restaurante> findByAbertosEmBreve( Cidade cidade, DayOfWeek dia, LocalTime hora, Pageable limit );
}
