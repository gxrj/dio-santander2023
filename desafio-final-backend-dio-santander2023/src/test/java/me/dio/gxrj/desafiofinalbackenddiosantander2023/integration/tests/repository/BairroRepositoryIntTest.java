package me.dio.gxrj.desafiofinalbackenddiosantander2023.integration.tests.repository;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Bairro;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Cidade;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Estado;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.repository.BairroRepository;

@DataJpaTest
@TestMethodOrder( MethodOrderer.OrderAnnotation.class )
public class BairroRepositoryIntTest {
    
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private BairroRepository repository;
    
    @Test
    @DisplayName( "Testa o metodo findByNomeContaingIgnoreCase" )
    void testa_findByNomeContaingIgnoreCase() {
        List<Bairro> entrada = new ArrayList<>();
        var cidades = populaDatabase_RetornaEntidades();
        var nomesBairro = List.of( "Vila Augusta", "ViLa Rosalia", "vila galvao" );

        for( var el : nomesBairro ) 
            entrada.add( 
                Bairro.builder().nome( el ).cidade( cidades.get( 0 ) ).build() );

        repository.saveAll( entrada );
        
        var saida = repository.findByNomeContainingIgnoreCase( "vil" );
        // Assegura que tamanho da saida seja igual ao tamanho da entrada
        assertThat( saida ).hasSize( entrada.size() ); 
        // Assegura que cada elemento de entrada tenha o mesmo nome de cada elemento de saida, dada a respectiva ordem
        assertThat( saida )
            .map( Bairro::getNome )
            .asList()
            .isEqualTo( saida.stream().map( Bairro::getNome ).toList() ); 
    }

    @Test
    @DisplayName( "Testa o metodo findByNomeContaingIgnoreCase" )
    void testa_findByCidade_Nome() {
        var cidades = populaDatabase_RetornaEntidades();
    
        var nomesBairrosGuarulhos = List.of( "Vila Augusta", "ViLa Rosalia", "vila galvao" );
        var nomesBairrosSantos = List.of( "Areia Branca", "Caneleira" );

        // Persiste os bairros no database
        var bairrosGuarulhos = persistirBairros( nomesBairrosGuarulhos, cidades.get( 0 ) );
        var bairrosSantos = persistirBairros( nomesBairrosSantos, cidades.get( 1 ) );

        assertThat( bairrosGuarulhos ).map( Bairro::getNome ).isEqualTo( nomesBairrosGuarulhos );
        assertThat( bairrosSantos ).map( Bairro::getNome ).isEqualTo( nomesBairrosSantos );
    }

    private List<Cidade> populaDatabase_RetornaEntidades() {
        
        var guarulhos = Cidade.builder()
                            .nome( "Guarulhos" )
                            .estado( Estado.SP )
                            .build();

        var santos = Cidade.builder()
                    .nome( "Santos" )
                    .estado( Estado.SP )
                    .build();

        guarulhos = entityManager.persist( guarulhos );
        santos = entityManager.persist( santos );

        return List.of( guarulhos, santos ); 
    }

    private List<Bairro> persistirBairros( List<String> bairros, Cidade cidade ) {
        List<Bairro> saida = new ArrayList<>();

        for( var el : bairros ) 
            saida.add( 
                Bairro.builder().nome( el ).cidade( cidade ).build() );

        return repository.saveAll( saida );
    }

}
