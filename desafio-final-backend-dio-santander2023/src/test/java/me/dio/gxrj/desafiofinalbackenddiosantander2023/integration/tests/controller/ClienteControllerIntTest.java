package me.dio.gxrj.desafiofinalbackenddiosantander2023.integration.tests.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Bairro;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Cidade;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Cliente;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Endereco;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Estado;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Telefone;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.service.ClienteService;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.service.EnderecoService;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerIntTest {
    
    @Autowired
    private MockMvc mvc;
    @Autowired
    private EnderecoService enderecoService;
    @Autowired
    private ClienteService clienteService;
    
    private static ObjectMapper mapper = new ObjectMapper();

    private Cidade cidade;
    private Bairro bairro;
    private Cliente cliente;

    @BeforeEach
    void setup() {
        cidade = Cidade.builder().nome( "Guarulhos" ).estado( Estado.SP ).build();
        cidade.setId( 1L );
        cidade = enderecoService.salvarCidade( cidade );
        bairro = Bairro.builder().nome( "Vila Aurora" ).cidade( cidade ).build();
        bairro.setId( 1L );
        bairro = enderecoService.salvarBairro( bairro );

        var endereco = Endereco.builder()
                        .logradouro( "Rua C" )
                        .referencia( "casa" )
                        .numero( "1027" )
                        .cep( "09123000" )
                        .bairro( bairro )
                        .build();

        var telefones = List.of( new Telefone( 11, "999751240" ) );

        cliente = Cliente.builder()
                        .nome( "Ana" )
                        .cpf( "01597243103" )
                        .login( "user" )
                        .senha( "123" )
                        .endereco( endereco )
                        .telefones( telefones )
                        .build();
    }

    @Test
    @DisplayName( "Testa o endpoint http POST '/clientes'" )
    void testaEndpointParaCriacaoDeCliente() throws Exception {
        var entidadeJson = mapper.writeValueAsString( cliente );

        mvc.perform( 
              post( "/clientes" )
                .contentType( MediaType.APPLICATION_JSON_VALUE )
                .content( entidadeJson ) 
          )
          .andExpect( status().is2xxSuccessful() );
    }

    @Test
    @DisplayName( "Testa o endpoint http PUT '/clientes/{id}'" )
    void testaEnpointParaEdicaoDeCliente() throws Exception {
        cliente = clienteService.criar( cliente );
        cliente.setNome( "Ana Paula" );

        var entidadeJson = mapper.writeValueAsString( cliente );

        mvc.perform( 
            put( "/clientes/"+cliente.getId() ) 
              .contentType( MediaType.APPLICATION_JSON_VALUE )
              .content( entidadeJson )
          )
          .andExpect( status().isOk() );
    }

    @Test
    @DisplayName( "Testa o endepont http GET '/clientes/{id}'" )
    void testaEndpointParaObterClientePorId() throws Exception {
        cliente = clienteService.criar( cliente );

        mvc.perform(
            get( "/clientes/"+cliente.getId() )
              .contentType( MediaType.APPLICATION_JSON_VALUE )
          )
          .andExpect( status().isOk() );
    }

    @Test
    @DisplayName( "Testa o endpoint http DELETE '/clientes/{id}'" )
    void testaEndpointParaExcluirCliente() throws Exception {
        cliente = clienteService.criar( cliente );

        mvc.perform( 
            delete( "/clientes/"+cliente.getId() )
              .contentType( MediaType.APPLICATION_JSON_VALUE ) 
          )
          .andExpect( status().is2xxSuccessful() );
    }
}
