package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model;


import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Item {
    
    @Id
    @GeneratedValue
    private UUID id;
    @Column( nullable = false )
    private String descricao;
    @Column( nullable = false, scale = 2 )
    private Double preco;
    @Column( name = "em_estoque", nullable = false )
    private Boolean emEstoque;
    // Todo: Mapear
    private Restaurante restaurante;
}
