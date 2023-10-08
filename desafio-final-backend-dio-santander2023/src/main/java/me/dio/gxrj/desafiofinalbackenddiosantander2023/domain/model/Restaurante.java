package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model;


import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table( name = "restaurante" )
public class Restaurante extends Conta {
    
    @Column( nullable = false )
    private String cnpj;

    @Column( nullable = false )
    private String descricao;

    @ElementCollection
    @CollectionTable(
        name = "restaurante_funcionamento",
        joinColumns = @JoinColumn( name = "restaurante_id" )
    )
    private List<Funcionamento> horarioFuncionamento;

    @Builder 
    public Restaurante( 
        String nome, String login, String senha, Endereco endereco, 
        String cnpj, String descricao, List<Funcionamento> horarioFuncionamento ) {

            this.nome = nome;
            this.login = login;
            this.senha = senha;
            this.endereco = endereco;
            this.cnpj = cnpj;
            this.descricao = descricao;
            this.horarioFuncionamento = horarioFuncionamento;
    }
}
