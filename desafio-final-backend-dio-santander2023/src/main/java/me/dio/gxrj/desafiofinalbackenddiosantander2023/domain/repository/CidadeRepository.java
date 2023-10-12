package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Cidade;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Estado;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    Optional<List<Cidade>> findByNomeLike( String nomeCidade );
    List<Cidade> findByEstado( Estado estado );
}
