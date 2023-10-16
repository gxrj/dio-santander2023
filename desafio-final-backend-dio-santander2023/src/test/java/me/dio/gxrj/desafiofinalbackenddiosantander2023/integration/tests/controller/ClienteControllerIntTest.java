package me.dio.gxrj.desafiofinalbackenddiosantander2023.integration.tests.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Bairro;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Cidade;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Estado;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.service.EnderecoService;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerIntTest {
    
    @Autowired
    private MockMvc mvc;
    @Autowired
    private EnderecoService enderecoService;
    
    private Cidade cidade;
    private Bairro bairro;

    @BeforeEach
    void setup() {
      cidade = Cidade.builder().nome( "Guarulhos" ).estado( Estado.SP ).build();
      cidade.setId( 1L );
      cidade = enderecoService.salvarCidade( cidade );
      bairro = Bairro.builder().nome( "Vila Aurora" ).cidade( cidade ).build();
      bairro.setId( 1L );
      bairro = enderecoService.salvarBairro( bairro );
    }

    @Test
    @DisplayName( "Testa o endpoint http POST '/cliente'" )
    void testaEndpointParaCriacaoDeCliente() throws Exception {
        var clienteJson = """
            {
                "senha": "123",
                "login": "user",
                "nome": "Ana",
                "endereco": {
                  "cep": "09123000",
                  "logradouro": "Rua C",
                  "numero": "1027",
                  "referencia": "casa",
                  "bairro": {
                    "id":
        """+ bairro.getId() + """
                    ,
                    "nome": "Vila Aurora",
                    "cidade": {
                      "id":
        """ + cidade.getId() + """
                      ,
                      "nome": "Guarulhos",
                      "estado": "SP"
                    }
                  }
                },
                "cpf": "01597243103",
                "telefones": [
                  {
                    "ddd": 11,
                    "numero": "999751240"
                  }
                ]
              }
            """;

        mvc.perform( 
            post( "/cliente" )
            .contentType( MediaType.APPLICATION_JSON_VALUE )
            .content( clienteJson ) 
        ).andExpect( status().is2xxSuccessful() );

    }
}
