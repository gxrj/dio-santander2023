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
@Table( name = "cliente" )
public class Cliente extends Conta {

    @Setter( AccessLevel.NONE )
    @Column( nullable = false, unique = true )
    private String cpf;

    @ElementCollection
    @CollectionTable(
        name = "cliente_telefone",
        joinColumns = @JoinColumn( name = "cliente_id" )
    )
    private List<Telefone> telefones;

    @Builder
    public Cliente( 
        String nome, String login, String senha, 
        Endereco endereco, String cpf, List<Telefone> telefones ) {
            this.nome = nome;
            this.login = login;
            this.senha = senha;
            this.endereco = endereco;
            this.cpf = cpf;
            this.telefones = telefones;
    }

    public Cliente() {}
}
