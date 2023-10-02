## Desafio final - DIO Santander Java Backend 2023

Solução: Desenvolver um protótipo de aplicação que simule o iFood

Trata-se de uma aplicação multi-tenant em que o database e o schema são compartilhados

Segue abaixo o diagrama de classes de um MVP.

### Diagrama de classes
```mermaid
classDiagram
    class Pedido{
        -List~Item~ itens
        -StatusPedido status
        -DateTime data
        -double total
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
        -String nome
        -String cpf
        -String email
        -String senha
        -Endereco endereco
    }
    class Telefone{
        -int ddd
        -String numero
        -Cliente cliente
    }
    class CartaoCredito{
        -String numero
        -Date validade
        -int codVefiricacao
        -Cliente titular
    }
    class Restaurante{
        -String cnpj
        -String nome
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
    class Endereco{
        -String cep
        -String logradouro
        -String numero
        -String referencia
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
```