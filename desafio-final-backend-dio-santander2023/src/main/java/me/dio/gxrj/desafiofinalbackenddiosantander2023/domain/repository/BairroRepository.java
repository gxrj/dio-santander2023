package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Bairro;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Cidade;

public interface BairroRepository extends JpaRepository<Bairro, Long> {
    List<Bairro> findByCidade( Cidade cidade );
}
