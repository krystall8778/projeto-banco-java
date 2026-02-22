# Projeto Banco em Java

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![JUnit](https://img.shields.io/badge/JUnit-Tests-green)

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

## Qualidade de Software e Testes

Neste projeto, dei uma atenção especial à confiabilidade das operações. Foi meu primeiro contato prático com **testes unitários** e o framework **JUnit 5**.

- **O que foi testado:** Validei cenários críticos como saques acima do limite, transferências entre contas inativas e cálculos de rendimento.
- **Aprendizado:** Entendi como os testes ajudam a evitar que novas funcionalidades quebrem o que já estava funcionando.

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
 |
 └── test/java/ProjetoBanco
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

##  Próximos Passos (Melhorias Futuras)

O projeto está em constante evolução. Algumas melhorias planejadas são:
- [ ] **Precisão Financeira:** Substituir o tipo `double` por `BigDecimal` para evitar problemas de arredondamento.
- [ ] **Persistência:** Implementar um banco de dados (MySQL ou PostgreSQL) para salvar os dados permanentemente.
- [ ] **API REST:** Transformar o projeto em um serviço web usando Spring Boot.
- [ ] **Interface Gráfica:** Criar uma interface web ou desktop para facilitar o uso.

---

## Autora

Laryssa Martins da Silva  
Estudante de Engenharia de Software