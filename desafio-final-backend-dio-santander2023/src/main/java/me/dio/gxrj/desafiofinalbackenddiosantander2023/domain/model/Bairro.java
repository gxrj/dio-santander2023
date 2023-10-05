package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model;

import jakarta.persistence.Column;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Bairro extends Localidade {
    @Column( nullable = false )
    private Cidade cidade;
}
