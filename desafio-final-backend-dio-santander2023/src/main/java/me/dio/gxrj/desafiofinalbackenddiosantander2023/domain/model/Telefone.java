package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model;


import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@AllArgsConstructor
@Builder @Getter @Setter
public class Telefone implements Serializable {

    @Column( nullable = false )
    private Integer ddd;

    @Column( nullable = false )
    private String numero;

    public Telefone() {}
}
