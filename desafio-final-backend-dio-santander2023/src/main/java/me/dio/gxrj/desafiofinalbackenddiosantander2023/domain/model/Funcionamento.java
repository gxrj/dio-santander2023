package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model;


import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class Funcionamento {

    @Enumerated( EnumType.STRING )
    private DiaSemana dia;
    @Column( name = "abetura", nullable = false, columnDefinition = "TIME" )
    private LocalTime horarioAbertura;
    @Column( name = "fechamento", nullable = false, columnDefinition = "TIME" ) 
    private LocalTime horarioFechamento;
    @Column( name = "funciona_em_feriados", columnDefinition = "boolean default false" )
    private Boolean funcionaFeriados;
}