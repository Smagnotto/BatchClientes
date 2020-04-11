# Upload Inicial de Clientes
Sistema desenvolvido com **Spring-Batch** para realizar o upload inicial de possíveis clientes na base de dados do novo cartão de crédito da **Fiap**.(Trabalho de Spring Framework)

## Inicio
Estas instruções irão lhe ensinar como executar uma versão local para densenvolvimento.

### Pré-requisitos
IDE Java (Eclipse, IntelliJ, etc.).

### Installing
Copiar a versão mais recente do repositório:
```
https://github.com/Smagnotto/BatchClientes
```

Utilizar ela em sua IDE.

## Executando os testes

Utilizar os testes unitários já disponíveis com o código, ou utilizar a biblioteca do Postman dentro do projeto. 

```
https://github.com/Smagnotto/BatchClientes/tree/master/postman
```

## Implantação

Para implantação em ambiente produtivo, é necessária a troca do banco de dados de H2 para o de sua preferência.
Além da inibição da opção ddl-auto do hibertane, pois não é ma boa prática para produção.


## Construído com
* [Maven](https://maven.apache.org/) - Gerenciador de dependências
* [Spring](https://spring.io) - Java Framework
* [H2 Database Engine](https://www.h2database.com) - Banco de dados

## Autores
Nome                  | RM
--------------------- | ------
Bruno Gea             | 333475
Diego Smagnotto       | 333886
Eduardo Matoso        | 333906
Felipe da Costa Silva | 333862
Tiago Langoni         | 333947