package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class Telefone {
   
    @Column( nullable = false )
    private Integer ddd;
    @Column( nullable = false )
    private String numero;
}
