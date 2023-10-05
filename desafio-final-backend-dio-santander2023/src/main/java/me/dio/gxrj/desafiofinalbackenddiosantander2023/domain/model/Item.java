package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model;


import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Builder @Getter @Setter
@Table( name = "item" )
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
    @ManyToOne( targetEntity = Restaurante.class )
    private Restaurante restaurante;
}
