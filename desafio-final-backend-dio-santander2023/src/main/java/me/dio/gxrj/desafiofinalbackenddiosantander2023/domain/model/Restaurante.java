package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model;


import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Restaurante extends Conta {
    
    @Id
    @GeneratedValue
    private UUID id;
    @Column( nullable = false )
    private String cnpj;
    @Column( nullable = false )
    private String descricao;
    // Todo: Mapear
    private List<Funcionamento> horarioFuncionamento;
    // Todo: Mapear
    private Endereco endereco;
}
