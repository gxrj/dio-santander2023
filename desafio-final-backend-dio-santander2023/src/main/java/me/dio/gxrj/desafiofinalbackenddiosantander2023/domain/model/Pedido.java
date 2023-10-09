package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model;

import java.time.LocalDateTime;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Builder @Getter @Setter
@Table( name = "pedido" )
public class Pedido {
 
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToMany( mappedBy = "id", fetch = FetchType.EAGER )
    private List<Item> itens;

    @Enumerated( EnumType.STRING ) 
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
}
