package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model;


import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@MappedSuperclass
public abstract class Localidade implements Serializable {

    @Id 
    @GeneratedValue
    protected Long id;
    @Column( nullable = false, unique = true )
    protected String nome;
}
