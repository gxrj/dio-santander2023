package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table( name = "bairro" )
public class Bairro extends Localidade {
    @ManyToOne
    @JoinColumn( name = "cidade_id", nullable = false, referencedColumnName = "id" )
    private Cidade cidade;

    @Builder
    public Bairro( String nome, Cidade cidade ) {
        this.nome = nome;
        this.cidade = cidade;
    }

    public Bairro() {}
}
