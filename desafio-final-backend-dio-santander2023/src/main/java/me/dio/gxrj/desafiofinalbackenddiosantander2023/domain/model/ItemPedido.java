package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@AllArgsConstructor
@Builder @Getter @Setter
public class ItemPedido {
    
    @ManyToOne
    @JoinColumn( name = "item_cardapio_id", referencedColumnName = "id" )
    private ItemCardapio itemCardapio;
    @Builder.Default
    @Column( nullable = false )
    private Integer quantidade = 1;
    @Builder.Default
    @Column( nullable = false )
    private Double precoUnd = 0d;

    public ItemPedido() {}
}
