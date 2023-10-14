package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model;


import java.time.DayOfWeek;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@AllArgsConstructor
@Builder @Getter @Setter
public class ExpedienteDiario {

    @Enumerated( EnumType.STRING )
    private DayOfWeek dia;
    @Column( name = "abetura", nullable = false, columnDefinition = "TIME" )
    private LocalTime inicio;
    @Column( name = "fechamento", nullable = false, columnDefinition = "TIME" ) 
    private LocalTime fim;

    public ExpedienteDiario() {}
}
