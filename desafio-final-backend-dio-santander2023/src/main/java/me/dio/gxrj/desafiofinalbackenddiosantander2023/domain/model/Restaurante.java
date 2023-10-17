package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model;


import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table( name = "restaurante" )
public class Restaurante extends Conta {
    
    @Setter( AccessLevel.NONE )
    @Column( nullable = false )
    private String cnpj;

    @Column
    private String descricao;

    @Column( name = "funciona_em_feriados", columnDefinition = "boolean default false" )
    private Boolean funcionaFeriados;

    @ElementCollection
    @CollectionTable(
        name = "restaurante_expediente",
        joinColumns = @JoinColumn( name = "restaurante_id" )
    )
    private List<ExpedienteDiario> expediente;

    @Builder 
    public Restaurante( 
        String nome, String login, String senha, Endereco endereco, 
        String cnpj, String descricao, List<ExpedienteDiario> expediente ) {

            this.nome = nome;
            this.login = login;
            this.senha = senha;
            this.endereco = endereco;
            this.cnpj = cnpj;
            this.descricao = descricao;
            this.expediente = expediente;
    }

    public Restaurante() {}
}
