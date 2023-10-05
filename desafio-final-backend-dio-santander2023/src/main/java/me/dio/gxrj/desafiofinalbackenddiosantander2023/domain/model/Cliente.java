package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model;


import java.util.List;

import jakarta.persistence.Column;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Cliente extends Conta {
    @Column( nullable = false, unique = true )
    private String cpf;
    @Column( nullable = false ) // Todo: Mapear
    private Endereco endereco;  
    // Todo: Mapear
    private List<Telefone> telefones;
}
