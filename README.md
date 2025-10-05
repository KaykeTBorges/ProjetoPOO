Perfeito — ótimo detalhe! 🙌
Como o seu projeto Swing tem uma **tela principal** que inicia toda a versão gráfica (provavelmente chamada `TelaPrincipal.java`), o README precisa refletir isso.
Aqui vai a **versão revisada e correta do README**, já mencionando a **tela principal como ponto de entrada da interface Swing** 👇

---

# 🏦 Sistema Bancário em Java

Este projeto é um **sistema bancário desenvolvido em Java**, aplicando os conceitos de **Programação Orientada a Objetos (POO)** e integrando uma **interface gráfica com Swing**.

O sistema permite o **cadastro de clientes e gerentes**, além da execução de operações bancárias como **depósito, saque, transferência e emissão de extrato** — tudo de forma organizada e modular.

---

## 🚀 Funcionalidades

### 👤 **Cliente**

* Criar conta com nome, CPF, telefone e senha
* Realizar **depósito, saque e transferência**
* Consultar **saldo e extrato detalhado**
* Interface disponível em **modo texto (console)** e **versão gráfica (Swing)**

### 👔 **Gerente**

* Cadastrar novos gerentes
* Listar e remover contas
* Consultar informações de clientes e extratos
* Acesso via login com ID e senha

---

## 🧩 Estrutura de Classes (Resumo)

```
Pessoa (abstrata)
 ├── Cliente
 │     └── Contas
 └── Funcionario (abstrata)
        └── Gerente

Banco3 – gerencia contas, clientes e gerentes
Transacao – registra operações bancárias
App – interface via console
view/ – telas gráficas em Swing
```

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 17
* **IDE recomendada:** Eclipse ou IntelliJ IDEA
* **Bibliotecas:**

  * `javax.swing` – construção da interface gráfica
  * `java.util` – coleções (`ArrayList`, `HashMap`, `HashSet`)
  * `java.time` – controle de data/hora nas transações
  * `java.util.regex` – validação de CPF e telefone

---

## 📦 Estrutura de Pacotes

```
src/
 ├── main2/
 │   ├── App.java
 │   ├── Banco3.java
 │   ├── Contas.java
 │   ├── Cliente.java
 │   ├── Gerente.java
 │   ├── Pessoa.java
 │   ├── Funcionario.java
 │   └── Transacao.java
 └── view/
     ├── TelaPrincipal.java          ← Ponto de entrada da interface Swing
     ├── TelaCadastroCliente.java
     ├── TelaMenuCliente.java
     ├── TelaLoginCliente.java
     ├── TelaCadastroGerente.java
     ├── TelaMenuGerente.java
     └── TelaLoginGerente.java
```

---

## ▶️ Como Executar

### 💻 **Versão Console**

1. Abra o projeto na sua IDE.
2. Compile todas as classes.
3. Execute a classe **`App.java`** (pacote `main2`) para iniciar o sistema no console.

### 🪟 **Versão Gráfica (Swing)**

1. Abra o projeto na IDE.
2. Compile as classes do pacote `view`.
3. Execute a classe **`TelaPrincipal.java`** — ela é o ponto de entrada da interface Swing e permite navegar pelas telas de login, cliente e gerente.

---

## 📚 Conceitos Aplicados

* **Abstração e Herança** (`Pessoa`, `Cliente`, `Funcionario`, `Gerente`)
* **Encapsulamento** (proteção de atributos sensíveis como senha e saldo)
* **Polimorfismo** (uso de classes genéricas para diferentes tipos de conta)
* **Composição** (Cliente possui uma Conta, Conta possui Transações)

---

## ✍️ Autor

**Kayke Borges**
📧 [inserir e-mail, se desejar]
📁 [link do repositório GitHub ou Google Drive]

---

Quer que eu gere agora esse README como um **arquivo `.md` formatado para GitHub**, com os emojis, blocos de código e identação certinha para você baixar e anexar?
