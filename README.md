# Projeto Banco em Java

Sistema bancário orientado a objetos desenvolvido em Java com foco
na aplicação prática de conceitos fundamentais de POO, como
abstração, herança, polimorfismo e uso de interfaces.

O projeto também inclui testes automatizados e uma classe `Main`
para simulação das operações via console.

---

## Objetivo

Este projeto foi desenvolvido com o propósito de consolidar conhecimentos em:

- Modelagem de domínio
- Herança e polimorfismo
- Encapsulamento e regras de negócio
- Separação de responsabilidades
- Testes automatizados com JUnit
- Estruturação de projetos com Maven
- Versionamento com Git

---

## Funcionalidades

- Abertura de conta
- Depósito
- Saque (com suporte a limite especial na Conta Corrente)
- Transferência entre contas
- Cobrança de taxa mensal (Conta Corrente)
- Aplicação de rendimento (Conta Poupança)
- Bloqueio de operações em contas inativas
- Testes automatizados validando regras de negócio

---

## Estrutura do Projeto

```
src/
 ├── main/java/ProjetoBanco
 │   ├── Conta.java
 │   ├── ContaCorrente.java
 │   ├── ContaPoupanca.java
 │   ├── Main.java
 │   └── interfaces/
 │       ├── AplicarRendimento.java
 │       ├── CobrancaMensal.java
 │       └── OperacoesBancarias.java
 └── test/java/ProjetoBanco
     ├── ContaTest.java
     ├── ContaCorrenteTest.java
     └── ContaPoupancaTest.java
```

---

## Decisões de Design

### Classe Abstrata `Conta`

A classe `Conta` foi definida como abstrata para centralizar
atributos e comportamentos comuns entre os diferentes tipos de conta,
como número, titular, saldo e controle de ativação.

Essa abordagem evita duplicação de código e fortalece a consistência
das regras de negócio.

---

### Controle de Estado da Conta

As contas são criadas inicialmente como inativas e precisam
ser abertas explicitamente através do método `abrirConta()`.

Essa decisão foi tomada para representar o ciclo de vida real
de uma conta bancária e impedir movimentações indevidas.

---

### Uso de Interfaces

As interfaces `OperacoesBancarias`, `CobrancaMensal`
e `AplicarRendimento` foram utilizadas para:

- Separar responsabilidades
- Garantir implementação de comportamentos específicos
- Permitir maior flexibilidade e escalabilidade

Exemplos:

- Apenas `ContaCorrente` implementa cobrança mensal
- Apenas `ContaPoupanca` implementa aplicação de rendimento

---

## Tecnologias Utilizadas

- Java
- Maven
- JUnit
- Git

---

## Como Executar

### Executar a simulação (Main)

Pela IDE:
- Executar a classe `Main`

Via terminal:

```bash
mvn compile
mvn exec:java -Dexec.mainClass="ProjetoBanco.Main"
```

### Executar testes

```bash
mvn test
```

---

## Autora

Laryssa Martins da Silva  
Estudante de Engenharia de Software