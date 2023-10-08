package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model;


public enum Estado {

    AC, AL, AP, AM, BA, CE, DF, ES, GO,
    MA, MG, MS, MT, PA, PB, PE, PI, PR,
    RJ, RN, RO, RS, RR, SC, SE, SP, TO;

    public static Estado fromString( String nome ) {
        return switch( nome.toUpperCase() ) {
            case "AC", "ACRE" -> Estado.AC;
            case "AL", "ALAGOAS" -> Estado.AL;
            case "AP", "AMAPA" -> Estado.AL; 
            case "AM", "AMAZONAS" -> Estado.AM; 
            case "BA", "BAHIA" -> Estado.BA;
            case "CE", "CEARA" -> Estado.CE; 
            case "DF", "DISTRITO FEDERAL" -> Estado.DF;
            case "ES", "ESPIRITO SANTO" -> Estado.ES;
            case "GO", "GOIAS" -> Estado.GO;
            case "MA", "MARANHAO" -> Estado.MA;
            case "MG", "MINAS GERAIS" -> Estado.MG;
            case "MS", "MATO GROSSO DO SUL" -> Estado.MS;
            case "MT", "MATO GROSSO" -> Estado.MT;
            case "PA", "PARA" -> Estado.PA;
            case "PB", "PARAIBA" -> Estado.PB;
            case "PE", "PERNAMBUCO" -> Estado.PE;
            case "PI", "PIAUI" -> Estado.PI;
            case "PR", "PARANA" -> Estado.PR;
            case "RJ", "RIO DE JANEIRO" -> Estado.RJ;
            case "RN", "RIO GRANDE DO NORTE" -> Estado.RN;
            case "RO", "RONDONIA" -> Estado.RO;
            case "RS", "RIO GRANDE DO SUL" -> Estado.RS; 
            case "RR", "RORAIMA" -> Estado.RR;
            case "SC", "SANTA CATARINA" -> Estado.SC; 
            case "SE", "SERGIPE" -> Estado.SE;
            case "SP", "SAO PAULO" -> Estado.SP;
            case "TO", "TOCANTINS" -> Estado.TO;
            default -> null;
        };
    }
}
