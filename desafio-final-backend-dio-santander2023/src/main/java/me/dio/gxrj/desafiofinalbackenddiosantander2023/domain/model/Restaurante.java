package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model;


import java.util.List;
import java.util.UUID;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Builder @Getter @Setter
@Table( name = "restaurante" )
public class Restaurante extends Conta {
    
    @Id
    @GeneratedValue
    private UUID id;

    @Column( nullable = false )
    private String cnpj;

    @Column( nullable = false )
    private String descricao;

    @Embedded
    private Endereco endereco;

    @ElementCollection
    @CollectionTable(
        name = "restaurante_funcionamento",
        joinColumns = @JoinColumn( name = "restaurante_id" )
    )
    private List<Funcionamento> horarioFuncionamento;
}
