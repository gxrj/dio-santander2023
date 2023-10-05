package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table( name = "bairro" )
public class Bairro extends Localidade {
    @Column( nullable = false )
    private Cidade cidade;
}
