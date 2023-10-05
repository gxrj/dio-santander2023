package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model;

import lombok.Getter;

@Getter
public enum StatusPedido {
    PENDENTE( "pendente" ),
    CONCLUIDO( "concluido" );

    private final String valor;

    StatusPedido( String valor ) {
        this.valor = valor;
    }

    @Override public String toString() { return valor; }

    public static StatusPedido fromString( String valor ) {
        return switch( valor.toLowerCase() ) {
            case "concluido", "concluído" -> StatusPedido.CONCLUIDO;
            default -> StatusPedido.PENDENTE;
        };
    }
}
