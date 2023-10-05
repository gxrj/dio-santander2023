package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model;


import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Endereco {
    
    @Id
    @GeneratedValue
    @Column( columnDefinition = "uuid not null" )
    private UUID id;
    @Column( nullable = false )
    private String cep;
    @Column( nullable = false )
    private String logradouro;
    @Column( nullable = false )
    private String numero;
    @Column
    private String referencia;
    // Todo: Mapear ?
    @Column( nullable = false )
    private Bairro bairro;
}
