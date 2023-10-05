package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class Endereco {
    
    @Column( nullable = false )
    private String cep;
    @Column( nullable = false )
    private String logradouro;
    @Column( nullable = false )
    private String numero;
    @Column
    private String referencia;
    @Column( nullable = false )
    private Bairro bairro;
}
