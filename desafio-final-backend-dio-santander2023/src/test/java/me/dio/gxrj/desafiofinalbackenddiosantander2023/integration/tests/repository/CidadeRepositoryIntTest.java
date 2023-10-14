package me.dio.gxrj.desafiofinalbackenddiosantander2023.integration.tests.repository;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Cidade;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Estado;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.repository.CidadeRepository;

@DataJpaTest
public class CidadeRepositoryIntTest {
    
    @Autowired
    private CidadeRepository repository;

    @Test
    @DisplayName( "Testa o metodo findByNomeContainingIgnoreCase" )
    void testa_findByNomeContainingIgnoreCase() {
        popularDatabase();

        var saoCaetanoDoSul = repository.findByNomeIgnoreCase( "sAo CaETANO dO sUl" );

        assertThat( saoCaetanoDoSul.get( 0 ).getNome() ).isEqualTo( "Sao Caetano do Sul" );
    }

    @Test
    @DisplayName( "Testa o metodo " )
    void test_findByEstado () {
        popularDatabase();

        var cidadesObtidas = repository.findByEstado( Estado.SP );
        assertThat( cidadesObtidas ).hasSize( 4 );

        var cidadesEsperadas = List.of( "Sao Caetano do Sul", "Santos", "Guarulhos", "Guaruja" );
        assertThat( cidadesObtidas ).map( Cidade::getNome ).asList().isEqualTo( cidadesEsperadas );
    }

    private void popularDatabase() {
        var saoCaetanoDoSul = Cidade.builder().nome( "Sao Caetano do Sul" ).estado( Estado.SP ).build();
        var natal = Cidade.builder().nome( "Natal" ).estado( Estado.RN ).build();
        var santos = Cidade.builder().nome( "Santos" ).estado( Estado.SP ).build();
        var jucurucu = Cidade.builder().nome( "Jucurucu" ).estado( Estado.BA ).build();
        var belem = Cidade.builder().nome( "Belem" ).estado( Estado.PA ).build();
        var guarulhos = Cidade.builder().nome( "Guarulhos" ).estado( Estado.SP ).build();
        var guaruja = Cidade.builder().nome( "Guaruja" ).estado( Estado.SP ).build();
        var balnearioCamboriu = Cidade.builder().nome( "Balneario Camboriu" ).estado( Estado.SC ).build();

        var cidades = List.of( saoCaetanoDoSul, natal, santos, jucurucu, belem, guarulhos, guaruja, balnearioCamboriu );

        repository.saveAll( cidades );
    }
}
