package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model;

import lombok.Getter;

@Getter
public enum DiaSemana {
    
    DOMINGO( "Domingo" ),
    SEGUNDA( "Segunda" ),
    TERCA( "Terca" ),
    QUARTA( "Quarta" ),
    QUINTA( "Quinta" ),
    SEXTA( "Sexta" ),
    SABADO( "Sabado" );

    private final String valor;

    DiaSemana( String valor ) {
        this.valor = valor;
    }

    @Override public String toString() { return valor; }

    public static DiaSemana fromString( String valor ) {
        return switch( valor.toLowerCase() ) {
            case "domingo" -> DiaSemana.DOMINGO;
            case "segunda" -> DiaSemana.SEGUNDA;
            case "terca", "terça" -> DiaSemana.TERCA;
            case "quarta" -> DiaSemana.QUARTA;
            case "quinta" -> DiaSemana.QUINTA;
            case "sexta" -> DiaSemana.SEXTA;
            case "sabado", "sábado" -> DiaSemana.SABADO;
            default -> null;
        };
    }
}
