package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.repository;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

}
