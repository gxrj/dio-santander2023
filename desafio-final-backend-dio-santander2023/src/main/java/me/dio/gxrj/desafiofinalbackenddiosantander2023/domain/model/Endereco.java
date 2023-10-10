package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Builder @Getter @Setter
public class Endereco {
    
    @Column( nullable = false )
    private String cep;

    @Column( nullable = false )
    private String logradouro;

    @Column( nullable = false )
    private String numero;

    @Column
    private String referencia;

    @ManyToOne
    @JoinColumn( name = "bairro_id", nullable = false, referencedColumnName = "id" )
    private Bairro bairro;
}
