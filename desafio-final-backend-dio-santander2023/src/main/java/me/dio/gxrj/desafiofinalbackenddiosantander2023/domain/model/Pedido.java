package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model;

import java.time.LocalDateTime;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Pedido {
 
    @Id
    @GeneratedValue
    private UUID id;
    // Todo: Mapear
    private List<Item> itens;
    @Enumerated( EnumType.STRING )
    private StatusPedido status = StatusPedido.PENDENTE;
    @Column( nullable = false, columnDefinition = "TIMESTAMP" )
    private LocalDateTime data;
    @Column( scale = 2 )
    private Double total;
    @Column( name = "pgto_confirmado", columnDefinition = "boolean default false" )
    private Boolean pagamentoConfirmado;
}
