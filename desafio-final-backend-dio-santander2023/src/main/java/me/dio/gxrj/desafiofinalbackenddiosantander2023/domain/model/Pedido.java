package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model;


import java.time.LocalDateTime;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Builder @Getter @Setter
@Table( name = "pedido" )
public class Pedido {
 
    @Id
    @GeneratedValue
    private UUID id;

    @ElementCollection
    @CollectionTable(
        name = "item_pedido",
        joinColumns = @JoinColumn( name = "pedido_id" )
    )
    private List<ItemPedido> itens;

    @Enumerated( EnumType.STRING )//Elege as constantes literais ( nesse caso, PENDENTE e CONCLUIDO ) para sinalizar o valor
    @Builder.Default
    private StatusPedido status = StatusPedido.PENDENTE;

    @Column( nullable = false, columnDefinition = "TIMESTAMP" )
    private LocalDateTime data;

    @Column( scale = 2 )
    private Double total;

    @Column( name = "pgto_confirmado", columnDefinition = "boolean default false" )
    private Boolean pagamentoConfirmado;

    @ManyToOne( optional = false, cascade = CascadeType.ALL )
    @JoinColumn( name = "restaurante_id", referencedColumnName = "id" )
    private Restaurante restaurante;

    @ManyToOne( optional = false, cascade = CascadeType.ALL )
    @JoinColumn( name = "cliente_id", referencedColumnName = "id" )
    private Cliente cliente;

    public Pedido() {}
}
