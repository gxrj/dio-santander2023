package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model;


import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.UUID;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@MappedSuperclass
public abstract class Conta {
    @Id
    @GeneratedValue
    @Column( columnDefinition = "uuid not null" )
    protected UUID id;

    @Column( nullable = false )
    protected String senha;

    @Setter( AccessLevel.NONE )
    @Column( nullable = false, unique = true )
    protected String login;

    @Column( nullable = false )
    protected String nome;

    @Embedded
    protected Endereco endereco;
}
