## Desafio final - DIO Santander Java Backend 2023

[&larr; Voltar](../README.md)<br><br>
[&uarr; Ir para o projeto](src/main)

Solução: Desenvolver um protótipo de aplicação que simule o iFood

Trata-se de uma aplicação multi-tenant em que o database e o schema são compartilhados

### Observações

- O sistema de pagamentos foi desconsiderado devido sua complexidade que pode ser implementados em futuras versões, seja integrando uma API de pagamentos terceirizada ou desenvolvendo um subsistema de pagamentos próprio.

- Aspectos de geolocalização como latitude, longitude e por consequência raio de distância foram desconsiderados também devido ao tempo disponível e complexidade.

Segue abaixo o diagrama de classes de um MVP.

### Diagrama de classes
```mermaid
classDiagram
    class Pedido{
        -List~ItemPedido~ itens
        -StatusPedido status
        -DateTime data
        -double total
        -bool pagamentoConfirmado
        -Cliente cliente
        -Restaurante restaurante
    }
    class ItemPedido{
        -Pedido pedido
        -ItemCardapio itemCardapio
        -int quantidade
        -double precoUnd
    }
    class ItemCardapio{
        -String descricao
        -double preco
        -bool emEstoque
        -Restaurante restaurante
    }
    class StatusPedido{
        <<enumeration>>
        CONCLUIDO
        PENDENTE
    }
    class Cliente{
        -String cpf
    }
    class Telefone{
        -int ddd
        -String numero
        -Cliente cliente
    }
    class Endereco{
        -String cep
        -String logradouro
        -String numero
        -String referencia
        -Bairro bairro
    }
    class Bairro{
        -Cidade cidade
    }
    class Cidade{
        -Estado estado
    }
    class Estado{
        <<enumeration>>
        AC
        AL
        AP
        AM
        BA
        CE
        DF
        ES
        GO
        MA
        MG
        MS
        MT
        PA
        PB
        PE
        PI
        PR
        RJ
        RN
        RO
        RS
        RR
        SC
        SE
        SP
        TO
    }
    class Localidade{
        <<abstract>>
        #String nome
    }
    class Conta{
        <<abstract>>
        #String login
        #String senha
        #String nome
        #Endereco endereco
    }
    class Restaurante{
        -String cnpj
        -String descricao
        -List~Funcionamento~ horarioFuncionamento
    }
    class Funcionamento{
        -DayOfWeek dia
        -LocalTime horarioAbertura
        -LocalTime horarioFechamento
        -bool funcionaFeriados
        -Restaurante restaurante
    }

    Pedido "1..*" ..> "1" Cliente
    Pedido "1..*" ..> "1" Restaurante
    Pedido ..> StatusPedido
    Pedido "1" --* "1..*" ItemPedido
    ItemPedido "0..*" ..> "1" ItemCardapio 
    Restaurante --* ItemCardapio
    Restaurante --* Funcionamento
    Restaurante ..> Endereco
    Conta <|-- Cliente
    Conta <|-- Restaurante
    Cliente ..> Endereco
    Cliente ..* Telefone
    Endereco ..> Bairro
    Bairro ..> Cidade
    Cidade ..> Estado
    Localidade <|-- Bairro
    Localidade <|-- Cidade
    Localidade <|-- Estado

```

[&larr; Voltar](../README.md)