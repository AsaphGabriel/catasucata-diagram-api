# 🛒 API E-Commerce Cata Sucata

API RESTful desenvolvida em **Java com Spring Boot** para gerenciamento de um sistema de e-commerce de sucatas. O sistema gerencia o ciclo de vida de produtos (itens), clientes, endereços e processamento de pedidos com controle automático de estoque.

---

## 🚀 Tecnologias

* **Java 17** (JDK)
* **Spring Boot 3**
* **MySQL** (Banco de Dados)
* **Hibernate/JPA** (ORM)
* **Maven** (Gerenciamento de Dependências)

---

## ⚙️ Configuração e Instalação

Siga os passos abaixo para rodar o projeto localmente.

### 1. Configuração do Banco de Dados (XAMPP/MySQL)

Certifique-se de que o seu serviço MySQL (via XAMPP ou instalado nativamente) esteja rodando na porta **3306**.

1.  Abra seu gerenciador de banco de dados (MySQL Workbench, DBeaver, phpMyAdmin ou Terminal).
2.  Crie o banco de dados e as tabelas executando o script abaixo:

```sql
-- Criação do Database
CREATE DATABASE IF NOT EXISTS catasucata;
USE catasucata;

-- 1. Tabela Item (Produto)
CREATE TABLE IF NOT EXISTS item (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255),
    preco DOUBLE PRECISION NOT NULL,
    estoque INT, 
    PRIMARY KEY (id)
) ENGINE=InnoDB;

-- 2. Tabela Cliente
CREATE TABLE IF NOT EXISTS cliente (
    id_cliente BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    data_cadastro DATETIME(6),
    PRIMARY KEY (id_cliente)
) ENGINE=InnoDB;

-- 3. Tabela Pedido
CREATE TABLE IF NOT EXISTS pedido (
    id_pedido BIGINT NOT NULL AUTO_INCREMENT,
    data_pedido DATETIME(6),
    status VARCHAR(50) NOT NULL,
    valor_total DOUBLE PRECISION,
    cliente_id BIGINT, 
    PRIMARY KEY (id_pedido),
    FOREIGN KEY (cliente_id) REFERENCES cliente (id_cliente)
) ENGINE=InnoDB;

-- 4. Tabela Item-Pedido (Relacionamento)
CREATE TABLE IF NOT EXISTS item_pedido (
    pedido_id BIGINT NOT NULL,
    item_id BIGINT NOT NULL,
    quantidade INT NOT NULL,
    preco_unitario DOUBLE PRECISION,
    PRIMARY KEY (pedido_id, item_id),
    FOREIGN KEY (pedido_id) REFERENCES pedido (id_pedido),
    FOREIGN KEY (item_id) REFERENCES item (id)
) ENGINE=InnoDB;

-- 5. Tabela Endereco
CREATE TABLE IF NOT EXISTS endereco (
    id_endereco BIGINT NOT NULL AUTO_INCREMENT,
    rua VARCHAR(255),
    cidade VARCHAR(255),
    cep VARCHAR(10),
    cliente_id BIGINT,
    PRIMARY KEY (id_endereco),
    FOREIGN KEY (cliente_id) REFERENCES cliente (id_cliente)
)
```
2. Configuração do Projeto
Verifique o arquivo src/main/resources/application.properties. Ele já está configurado para conexão padrão local (root sem senha):

Properties

spring.datasource.url=jdbc:mysql://localhost:3306/catasucata
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Atualiza o esquema do banco automaticamente se houver mudanças nas Entities
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
3. Executando no IntelliJ IDEA
Abra o IntelliJ e selecione File > Open.

Navegue até a pasta do projeto (onde está o pom.xml) e clique em OK.

Aguarde o Maven baixar todas as dependências (indexação).

Localize a classe principal: src/main/java/com/example/catasucata/CatasucataApplication.java.

Clique com o botão direito nela e selecione Run 'CatasucataApplication'.

A API estará disponível em: http://localhost:8080

📖 Documentação da API (Endpoints)
Abaixo estão os exemplos de uso para testar o fluxo completo de uma venda: Cadastrar Cliente -> Cadastrar Endereço -> Cadastrar Produto -> Realizar Pedido.

1. Clientes
Cadastrar Cliente

Método: POST

URL: /clientes

JSON
```JSON
{
  "nome": "Carla Fontes",
  "email": "carla.fontes@email.com",
  "cpf": "837387273",
  "telefone": "315995654321",
  "senha": "senhaSegura123"
}
```
Listar Clientes

Método: GET

URL: /clientes

2. Endereços
Cadastrar Endereço para Cliente

Método: POST

URL: /enderecos/{id_do_cliente}

Exemplo: /enderecos/1

JSON
```JSON
{
    "rua": "Rua JK",
    "cidade": "Belo Horizonte",
    "cep": "30140001"
}
```
3. Itens (Produtos)
Cadastrar Novo Item

Método: POST

URL: /itens

JSON
```JSON
{
  "nome": "Sucata de Eletrônicos",
  "descricao": "Componentes antigos",
  "preco": 75.00,
  "estoque": 150
}
```
Listar Itens

Método: GET

URL: /itens

4. Pedidos (Checkout)
Esta é a funcionalidade principal. Ao criar um pedido, a API verifica se há estoque disponível, calcula o valor total e abate a quantidade do estoque do item.

Criar Pedido

Método: POST

URL: /pedidos/{id_do_cliente}

Exemplo: /pedidos/1

Corpo: Lista de itens com ID e Quantidade.

JSON
```JSON
[
  {
    "item": { "id": 2 },
    "quantidade": 2
  }
]
```
🧪 Testando com Postman / Insomnia
Para testar, siga a ordem lógica:

Crie um Cliente e anote o id retornado.

Crie um Item e anote o id retornado.

Use o id do cliente na URL e o id do item no JSON para criar um Pedido.

Verifique se o estoque do item foi reduzido consultando novamente o endpoint GET /itens.
