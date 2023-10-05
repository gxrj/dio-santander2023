package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Telefone {
    @Id
    @GeneratedValue
    private Long id;    
    @Column( nullable = false )
    private Integer ddd;
    @Column( nullable = false )
    private String numero;
    @Column // Todo: Mapear
    private Cliente cliente;
}
