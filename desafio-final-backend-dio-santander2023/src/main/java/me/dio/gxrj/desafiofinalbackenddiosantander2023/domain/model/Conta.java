package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@MappedSuperclass
public abstract class Conta {
    @Id
    @GeneratedValue
    @Column( columnDefinition = "uuid not null" )
    private UUID id;

    @Column( nullable = false )
    private String senha;

    @Column( nullable = false, unique = true )
    private String login;

    @Column( nullable = false )
    private String nome;
}
