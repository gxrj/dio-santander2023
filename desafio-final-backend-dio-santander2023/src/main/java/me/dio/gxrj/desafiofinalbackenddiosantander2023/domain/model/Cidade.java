package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table( name = "cidade" )
public class Cidade extends Localidade {
    @Column( nullable = false )
    private Estado estado;
}
