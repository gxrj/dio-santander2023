package me.dio.gxrj.desafiofinalbackenddiosantander2023.integration.tests.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.service.EnderecoService;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Bairro;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Cidade;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Estado;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class EnderecoControllerIntTest {
    
    @Autowired
    private MockMvc mvc;
    @Autowired
    private EnderecoService enderecoService;

    private static ObjectMapper mapper = new ObjectMapper();

    private List<Cidade> cidades;
    private List<Bairro> bairros;

    @BeforeEach
    void setup() {
        cidades = new ArrayList<>();
        bairros = new ArrayList<>();
        cidades.addAll(
            List.of( 
                Cidade.builder().nome( "Guarulhos" ).estado( Estado.SP ).build(),
                Cidade.builder().nome( "Santos" ).estado( Estado.SP ).build(),
                Cidade.builder().nome( "Osasco" ).estado( Estado.SP ).build(),
                Cidade.builder().nome( "Guaruja" ).estado( Estado.SP ).build()
            )
        );
        bairros.addAll( 
            List.of(
                Bairro.builder().nome( "Vila Aurora" ).cidade( cidades.get( 0 ) ).build(),
                Bairro.builder().nome( "Vila Augusta" ).cidade( cidades.get( 0 ) ).build(),
                Bairro.builder().nome( "Vila Galvao" ).cidade( cidades.get( 0 ) ).build(),
                Bairro.builder().nome( "Vila Itaim" ).cidade( cidades.get( 0 ) ).build(),
                Bairro.builder().nome( "Vila Laurita" ).cidade( cidades.get( 0 ) ).build()
            )
        );
    }

    @Test
    @DisplayName( "Testa o endpoint http POST '/endereco/cidade'" )
    void testaEndpointParaCriacaoDeCidade() throws Exception {
        var entidadeJson = mapper.writeValueAsString( cidades.get( 0 ) );
        mvc.perform( 
            post( "/endereco/cidade" ) 
                .contentType( MediaType.APPLICATION_JSON_VALUE )
                .content( entidadeJson )
        )
        .andExpect( status().is2xxSuccessful() );
    }

    @Test
    @DisplayName( "Testa o endpoint http POST '/endereco/cidades'" )
    void testaEndpointParaCriacaoDeCidades() throws Exception {
        var entidadesJson = mapper.writeValueAsString( cidades );

        mvc.perform(
            post( "/endereco/cidades" )
                .contentType( MediaType.APPLICATION_JSON_VALUE )
                .content( entidadesJson )
        )
        .andExpect( status().is2xxSuccessful() );
    }


    @Test
    @DisplayName( "Testa o endpoint http GET '/endereco/cidade/{id}'" )
    void testEndpointParaObterCidade() throws Exception {
        cidades = enderecoService.salvarMultiplasCidades( cidades );
        mvc.perform(
            get( "/endereco/cidade/"+cidades.get( 0 ).getId() )
                .contentType( MediaType.APPLICATION_JSON_VALUE )
        )
        .andExpect( status().is2xxSuccessful() );
    }

    @Test
    @DisplayName( "Testa o endpoint http GET '/endereco/cidades?estado=SP'" )
    void testEndpointParaObterCidades() throws Exception {
        cidades = enderecoService.salvarMultiplasCidades( cidades );
        mvc.perform(
            get( "/endereco/cidades?estado=SP" )
                .contentType( MediaType.APPLICATION_JSON_VALUE )
        )
        .andExpect( status().is2xxSuccessful() );
    }

    @Test
    @DisplayName( "Testa o endpoint http PUT '/endereco/cidade/{id}'" )
    void testaEndpointParaEdicaoDeCidade() throws Exception {
        cidades = enderecoService.salvarMultiplasCidades( cidades );
        var cidade = cidades.get( 1 );
        cidade.setNome( "Assis" );

        var entidadeJson = mapper.writeValueAsString( cidade );

        mvc.perform(
            put( "/endereco/cidade/"+cidade.getId() )
                .contentType( MediaType.APPLICATION_JSON_VALUE )
                .content( entidadeJson )
        )
        .andExpect( status().is2xxSuccessful() );
    }

    @Test
    @DisplayName( "Testa o endpoint http DELETE '/endereco/cidade/{id}'" )
    void testaEndpointParaRemocaoDeCidade() throws Exception {
        cidades = enderecoService.salvarMultiplasCidades( cidades );
        var cidade = cidades.get( 1 );

        mvc.perform(
            delete( "/endereco/cidade/"+cidade.getId() )
                .contentType( MediaType.APPLICATION_JSON_VALUE )
        )
        .andExpect( status().is2xxSuccessful() );
    }

    @Test
    @DisplayName( "Testa o endpoint http POST '/endereco/bairro'" )
    void testaEndpointParaCriacaoDeBairro() throws Exception {
        var cidade = enderecoService.salvarCidade( cidades.get( 0 ) );
        cidades.set( 0, cidade );
        bairros.get( 0 ).setCidade( cidade );
        var entidadeJson = mapper.writeValueAsString( bairros.get( 0 ) );

        mvc.perform( 
            post( "/endereco/bairro" ) 
                .contentType( MediaType.APPLICATION_JSON_VALUE )
                .content( entidadeJson )
        )
        .andExpect( status().is2xxSuccessful() );
    }

    @Test
    @DisplayName( "Testa o endpoint http POST '/endereco/bairros'" )
    void testaEndpointParaCriacaoDeBairros() throws Exception {
        cidades = enderecoService.salvarMultiplasCidades( cidades );

        var entidadesJson = mapper.writeValueAsString( bairros );

        mvc.perform(
            post( "/endereco/bairros" )
                .contentType( MediaType.APPLICATION_JSON_VALUE )
                .content( entidadesJson )
        )
        .andExpect( status().is2xxSuccessful() );
    }

    @Test
    @DisplayName( "Testa o endpoint http GET '/endereco/bairro/{id}'" )
    void testaEndpointParaObterBairro() throws Exception {
        cidades = enderecoService.salvarMultiplasCidades( cidades );
        bairros = enderecoService.salvarMultiplosBairros( bairros );

        mvc.perform(
            get( "/endereco/bairro/"+bairros.get( 0 ).getId() )
                .contentType( MediaType.APPLICATION_JSON_VALUE )
        )
        .andExpect( status().is2xxSuccessful() );
    }

    @Test
    @DisplayName( "Testa o endpoint http GET '/endereco/bairros?cidade=Guarulhos'" )
    void testaEndpointParaObterBairros() throws Exception {
        cidades = enderecoService.salvarMultiplasCidades( cidades );
        bairros = enderecoService.salvarMultiplosBairros( bairros );

        mvc.perform(
            get( "/endereco/bairros?cidade=Guarulhos" )
                .contentType( MediaType.APPLICATION_JSON_VALUE )
        )
        .andExpect( status().is2xxSuccessful() ); 
    }

    @Test
    @DisplayName( "Testa o endpoint http PUT '/endereco/bairro/{id}'" )
    void testaEndpointParaEdicaoDeBairro() throws Exception {
        cidades = enderecoService.salvarMultiplasCidades( cidades );
        bairros = enderecoService.salvarMultiplosBairros( bairros );

        var bairro = bairros.get( 0 );
        bairro.setNome( "Macedo ");

        var entidadeJson = mapper.writeValueAsString( bairro );

        mvc.perform(
            put( "/endereco/bairro/"+bairro.getId() )
                .contentType( MediaType.APPLICATION_JSON_VALUE )
                .content( entidadeJson )
        )
        .andExpect( status().is2xxSuccessful() );
    }

    @Test
    @DisplayName( "Testa o endpoint http DELETE '/endereco/bairro/{id}'" )
    void testaEndpointParaRemocaoDeBairro() throws Exception {
        cidades = enderecoService.salvarMultiplasCidades( cidades );
        bairros = enderecoService.salvarMultiplosBairros( bairros );
        var bairro = bairros.get( 0 );

        mvc.perform(
            delete( "/endereco/bairro/"+bairro.getId() )
                .contentType( MediaType.APPLICATION_JSON_VALUE )
        )
        .andExpect( status().is2xxSuccessful() );
    }
}
