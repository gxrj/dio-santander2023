package me.dio.gxrj.desafiofinalbackenddiosantander2023.integration.tests.repository;


import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
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
    
    @BeforeEach
    @DisplayName( "Semeia o banco de dados com a cidade de Guarulhos/SP" )
    void setup() {
        
        var cidade = Cidade.builder()
                            .nome( "Guarulhos" )
                            .estado( Estado.SP )
                            .build();

        entityManager.persist( cidade );
    }

    @Test
    @Order(1)
    @DisplayName( "Testando a insercao de bairro no db" )
    void testaGravacaoDoBairroNoDB() {
        var cidade = entityManager.find( Cidade.class, 1L );
        assert cidade != null : "Cidade nao encontrada na busca pela entidade no database!";

        var bairro = Bairro.builder()
                        .nome( "bairro 1" )
                        .cidade( cidade )
                        .build();

        assertNull( bairro.getId(), "O atributo id deve ser nulo antes de salvar no database!" );

        var resultado = repository.save( bairro );

        assert resultado.getId().equals( 1L ) : "O atributo id nao pode ser nulo apos salvar entidade no database!";
    }

    @Test
    @Order(2)
    @DisplayName( "Testa o metodo findByNomeContaingIgnoreCase" )
    void testa_findByNomeContaingIgnoreCase() {
        var cidade = entityManager.find( Cidade.class, 2L );
        assert cidade != null : "Cidade nao encontrada na busca pela entidade no database!";

        var nomesBairro = List.of( "Vila Augusta", "ViLa Rosalia", "vila galvao" );
        List<Bairro> entrada = new ArrayList<>();

        for( var el : nomesBairro ) 
            entrada.add( 
                Bairro.builder().nome( el ).cidade( cidade ).build() );

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
    @Order(3)
    @DisplayName( "Testa o metodo findByNomeContaingIgnoreCase" )
    void testa_findByCidade_Nome() {

        var santos = Cidade.builder()
                    .nome( "Santos" )
                    .estado( Estado.SP )
                    .build();

        santos = entityManager.persist( santos );

        var guarulhos = entityManager.find( Cidade.class, 3L );
        assert guarulhos != null : "Cidade nao encontrada na busca pela entidade no database!";

        var nomesBairrosGuarulhos = List.of( "Vila Augusta", "ViLa Rosalia", "vila galvao" );
        var nomesBairrosSantos = List.of( "Areia Branca", "Caneleira" );
        var bairrosGuarulhos = persistirBairros( nomesBairrosGuarulhos, guarulhos );
        var bairrosSantos = persistirBairros( nomesBairrosSantos, santos );

        assertThat( bairrosGuarulhos ).map( Bairro::getNome ).isEqualTo( nomesBairrosGuarulhos );
        assertThat( bairrosSantos ).map( Bairro::getNome ).isEqualTo( nomesBairrosSantos );
    }

    private List<Bairro> persistirBairros( List<String> bairros, Cidade cidade ) {
        List<Bairro> saida = new ArrayList<>();

        for( var el : bairros ) 
            saida.add( 
                Bairro.builder().nome( el ).cidade( cidade ).build() );

        return repository.saveAll( saida );
    }

}
