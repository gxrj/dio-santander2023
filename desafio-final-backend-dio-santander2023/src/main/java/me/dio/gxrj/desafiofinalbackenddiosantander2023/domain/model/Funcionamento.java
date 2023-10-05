package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model;


import java.time.LocalTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class Funcionamento {

    @Id
    @GeneratedValue
    private UUID id;
    @Enumerated( EnumType.STRING )
    private DiaSemana dia;
    @Column( name = "abetura", nullable = false, columnDefinition = "TIME" )
    private LocalTime horarioAbertura;
    @Column( name = "fechamento", nullable = false, columnDefinition = "TIME" ) 
    private LocalTime horarioFechamento;
    @Column( name = "funciona_em_feriados", columnDefinition = "boolean default false" )
    private Boolean funcionaFeriados;
}
