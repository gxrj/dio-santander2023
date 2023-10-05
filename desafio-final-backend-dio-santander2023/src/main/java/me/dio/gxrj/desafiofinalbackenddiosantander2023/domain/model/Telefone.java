package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class Telefone {
    @Id
    @GeneratedValue
    private Long id;    
    @Column( nullable = false )
    private Integer ddd;
    @Column( nullable = false )
    private String numero;
    @ManyToOne
    @JoinColumn( name = "client_id", referencedColumnName = "id" )
    private Cliente cliente;
}
