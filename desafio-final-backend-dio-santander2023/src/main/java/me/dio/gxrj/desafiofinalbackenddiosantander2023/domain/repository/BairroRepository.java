package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Bairro;

public interface BairroRepository extends JpaRepository<Bairro, Long> {
    List<Bairro> findByNomeContainingIgnoreCase( String nomeBairro );
    List<Bairro> findByCidade_NomeIgnoreCase( String nomeCidade );
}
