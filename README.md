# Controle Financeiro API

API REST de controle financeiro pessoal, desenvolvida com Spring Boot.
É a evolução de um projeto anterior em Java puro com JDBC, agora com arquitetura em camadas,
DTOs, validação e tratamento global de erros.

## Tecnologias

- Java 21
- Spring Boot 4.1.2 (Web, Data JPA, Validation)
- PostgreSQL
- BCrypt (spring-security-crypto) para hash de senhas
- Lombok
- Maven

## Como rodar

1. Crie um banco PostgreSQL chamado `controle_financeiro` (porta 5433).
2. Defina as variáveis de ambiente:
    - `DB_PASSWORD`: senha do seu PostgreSQL
    - `DB_USER`: usuário (opcional; o padrão é `postgres`)
3. Execute a classe `ControleFinanceiroApplication` pela IDE.

A aplicação sobe na porta 8080, e o Hibernate cria as tabelas automaticamente.

## Endpoints

### Usuário

| Método | Caminho | Descrição |
|---|---|---|
| POST | `/usuarios` | Cadastra um usuário |
| GET | `/usuarios` | Lista os usuários |
| GET | `/usuarios/{id}` | Busca um usuário por id |
| PUT | `/usuarios/{id}` | Atualiza nome e e-mail (só usuário ativo) |
| PATCH | `/usuarios/{id}/inativar` | Inativa o usuário |
| PATCH | `/usuarios/{id}/reativar` | Reativa um usuário inativo |

## Status

Em desenvolvimento. Já implementado: cadastro e gestão de usuários.
Próximas etapas: categorias, transações, metas, Swagger e autenticação com JWT.