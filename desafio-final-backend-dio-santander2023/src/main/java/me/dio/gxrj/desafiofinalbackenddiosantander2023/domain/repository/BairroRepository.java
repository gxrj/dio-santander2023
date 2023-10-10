package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Bairro;

public interface BairroRepository extends JpaRepository<Bairro, Long> {
    Optional<Bairro> findByNome( String nomeBairro );
    List<Bairro> findByCidade_Nome( String nomeCidade );
}
