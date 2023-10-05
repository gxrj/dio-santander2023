package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model;


import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table( name = "cliente" )
public class Cliente extends Conta {
    @Column( nullable = false, unique = true )
    private String cpf;
    @Embedded
    private Endereco endereco;  
    @ElementCollection
    @CollectionTable(
        name = "cliente_telefone",
        joinColumns = @JoinColumn( name = "cliente_id" )
    )
    private List<Telefone> telefones;
}
