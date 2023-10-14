package me.dio.gxrj.desafiofinalbackenddiosantander2023.integration.tests.repository;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Bairro;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Cidade;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Cliente;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Endereco;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Estado;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Telefone;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.repository.ClienteRepository;

@DataJpaTest
public class ClienteRepositoryIntTest {
    
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ClienteRepository repository;
    // Todo: Injetar o passwordEncoder

    @Test
    @DisplayName( "Testa o metodo findByCpf" )
    void testa_findByCpf() {
        var clientes = populaDatabase_RetornaEntidades();

        // Busca o cliente Maria
        var cliente = repository.findByCpf( "01294891547" ).get();
        assertThat( cliente.getNome() ).isEqualTo( "Maria" );
        // Assegura que o cliente buscado é igual ao cliente de index 2 
        assertThat( cliente ).isEqualTo( clientes.get(2) ); 
    }

    @Test
    @DisplayName( "Testa o metodo findByLogin" )
    public void testa_findByLogin() {
        var clientes = populaDatabase_RetornaEntidades();

        // Busca o cliente Ana
        var cliente = repository.findByLogin( "userAna" ).get();
        assertThat( cliente.getNome() ).isNotEqualTo( "Maria" );
        // Assegura que o cliente buscado é igual ao cliente de index 0 
        assertThat( cliente ).isEqualTo( clientes.get(0) ); 
    }

    private List<Cliente> populaDatabase_RetornaEntidades() {
        var nomes = List.of( "Ana", "Joao", "Maria" );
        var cpfs = List.of( "31864375094", "91349075350", "01294891547" );
        var logins = List.of( "userAna", "user2", "user3" );
        var telefoneBuilder = Telefone.builder().ddd( 11 );
        var telefones = List.of( 
                telefoneBuilder.numero( "988501274" ).build(),
                telefoneBuilder.numero( "999726100" ).build(),
                telefoneBuilder.numero( "997038653" ).build() 
        );
        var enderecos = gerarEnderecos();
        var clientes = new ArrayList<Cliente>();
        
        for( int i = 0; i < nomes.size(); i++ ) {
            var cliente = Cliente.builder()
                            .cpf( cpfs.get(i) )
                            .login( logins.get(i) )
                            .nome( nomes.get(i) )
                            .telefones( List.of( telefones.get(i) ) )
                            .senha( "123" )
                            .endereco( enderecos.get(i) )
                            .build();
            clientes.add( cliente );
        }

        return repository.saveAll( clientes );
    }

    private List<Endereco> gerarEnderecos() {
        var bairros = gerarBairros();

        var logradouros = List.of( "avenida 1", "rua 2", "rua c" );
        var numeros = List.of( "12", "1015", "627" );
        var ceps = List.of( "10213000", "1947512", "1501923" );
        var enderecos = new ArrayList<Endereco>();

        for( int i = 0; i < bairros.size(); i++ ) {
            var endereco = Endereco.builder()
                                .bairro( bairros.get(i) )
                                .logradouro( logradouros.get(i) )
                                .numero( numeros.get(i) )
                                .cep( ceps.get(i) )
                                .build();
            enderecos.add( endereco );
        }

        return enderecos;       
    }

    private List<Bairro> gerarBairros() {
        var cidade = Cidade.builder()
                        .nome( "Guarulhos" )
                        .estado( Estado.SP )
                        .build();

        cidade = entityManager.persist( cidade );

        var bairros = new ArrayList<Bairro>();
        var bairroBuilder = Bairro.builder().cidade( cidade );
        var nomesBairros = List.of( "Vila Augusta", "ViLa Rosalia", "vila galvao" );

        for( var nome : nomesBairros ) {
            var bairro = bairroBuilder.nome( nome ).build();
            bairro = entityManager.persist( bairro );
            bairros.add( bairro );
        }

        return bairros;
    }
}
