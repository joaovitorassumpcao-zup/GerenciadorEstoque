# Gerenciador de Estoque

Este projeto é um sistema de gerenciamento de estoque desenvolvido com o Spring Framework e um banco de dados PostgreSQL. Ele permite a criação, atualização, leitura e exclusão de produtos no estoque por meio de requisições HTTP.

## 🚀 Funcionalidades

- **CRUD de Produtos**: Permite criar, ler, atualizar e excluir produtos.
- **Listagem de Produtos**: Lista todos os produtos disponíveis no estoque.
- **Detalhes do Produto**: Obtém detalhes de um produto específico pelo ID.
- **Atualização de Produto**: Atualiza um produto existente no estoque pelo ID.
- **Exclusão de Produto**: Exclui um produto do estoque pelo ID.

## 📌 Endpoints

### Produtos

- `GET /produtos`: Lista todos os produtos.
- `GET /produtos/{id}`: Obtém um produto específico pelo ID.
- `POST /produtos`: Cria um novo produto.
- `PUT /produtos/{id}`: Atualiza um produto existente pelo ID.
- `DELETE /produtos/{id}`: Exclui um produto pelo ID.

## 📖 Documentação da API

A documentação completa da API, incluindo detalhes de cada endpoint, parâmetros e respostas, está disponível no Swagger UI:

[Swagger UI - Gerenciador de Estoque](http://localhost:8080/swagger-ui.html)

## 🛠 Tecnologias Utilizadas

- **Spring Boot**: Framework para facilitar o bootstrapping e o desenvolvimento de novas aplicações Spring.
- **Spring Data JPA**: Facilita a interação com o banco de dados usando JPA.
- **H2**: Banco de dados relacional.
- **Swagger**: Ferramenta para documentação de APIs RESTful.

## 👤 Autor

João Vitor Cunha Assumpção

