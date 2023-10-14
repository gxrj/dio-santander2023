package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.repository;


import java.time.DayOfWeek;
import java.time.LocalTime;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Cidade;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, UUID> {
    Optional<Restaurante> findByCnpj( String cnpj );
    Optional<Restaurante> findByLogin( String login );
    List<Restaurante> findByNomeContainingIgnoreCase( String nome );
    List<Restaurante> findByEndereco_Bairro_NomeIgnoreCase( String nomeBairro );
    List<Restaurante> findByEndereco_Bairro_Cidade_NomeIgnoreCase( String nomeCidade );

    // lista retaurantes da cidade que apresentam o item pesquisado no cardápio
    @Query( """
        select r from Restaurante r 
        left join ItemCardapio i on i.restaurante = r 
        where r.endereco.bairro.cidade = ?1
        and lower( i.descricao ) like lower( concat('%', ?2, '%') )
    """ )
    List<Restaurante> findByDescricaoItemCardapio( Cidade cidade, String descricaoItem );

    // lista retaurantes da cidade abertos
    @Query( """
        select r from Restaurante r 
        right join r.expediente e 
        where e.dia = ?2 
        and r.endereco.bairro.cidade = ?1
        and ( case
                when ( e.inicio <= e.fim  ) then ( ?3 between e.inicio and e.fim ) 
                when ( e.inicio > e.fim ) then ( ?3 not between e.fim and e.inicio )
            end )
    """ )
    List<Restaurante> findByAbertos( Cidade cidade, DayOfWeek dia, LocalTime horaAtual, Pageable limite );
}
