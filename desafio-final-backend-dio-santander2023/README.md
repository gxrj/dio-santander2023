## Desafio final - DIO Santander Java Backend 2023

[&larr; Voltar](../README.md)

Solução: Desenvolver um protótipo de aplicação que simule o iFood

Trata-se de uma aplicação multi-tenant em que o database e o schema são compartilhados

### Observações

- O sistema de pagamentos foi desconsiderado devido sua complexidade que pode ser implementados em futuras versões, seja integrando uma API de pagamentos terceirizada ou desenvolvendo um subsistema de pagamentos próprio.

Segue abaixo o diagrama de classes de um MVP.

### Diagrama de classes
```mermaid
classDiagram
    class Pedido{
        -List~Item~ itens
        -StatusPedido status
        -DateTime data
        -double total
        -bool pagamentoConfirmado
        -Cliente cliente
        -Restaurante restaurante
    }
    class Item{
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
        -Endereco endereco
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
    }
    class Localidade{
        <<abstract>>
        -String nome
    }
    class Conta{
        <<abstract>>
        -String login
        -String senha
        -String nomeUsuario
    }
    class Restaurante{
        -String cnpj
        -String descricao
        -List~Funcionamento~ horarioFuncionamento
        -Endereco endereco
    }
    class Funcionamento{
        -DiaSemana dia
        -Time inicio
        -Time fim
        -Restaurante restaurante
    }
    class DiaSemana{
        <<enumeration>>
        DOMINGO
        SEGUNDA
        TERCA
        QUARTA
        QUINTA
        SEXTA
        SABADO
    }

    Pedido "1..*" ..> "1" Cliente
    Pedido "1..*" ..> "1" Restaurante
    Pedido ..> StatusPedido
    Pedido --o Item
    Restaurante --* Item
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
    Funcionamento ..> DiaSemana

```

[&larr; Voltar](../README.md)