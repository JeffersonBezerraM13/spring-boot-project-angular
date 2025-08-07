# HelpBridge API

<details>
<summary><strong>🇧🇷 Português</strong></summary>

## 📚 Sobre o Projeto

**HelpBridge** é uma API REST para gerenciamento de chamados de suporte técnico em qualquer negócio. O sistema permite que **clientes** acompanhem chamados e seus respectivos **técnicos**, enquanto **administradores técnicos** podem atualizar o status e controlar as requisições. Todos os chamados são persistidos, permitindo histórico e reabertura futura.

### ✨ Principais Funcionalidades

- 👨‍🔧 Gerenciamento completo de técnicos e técnicos administradores
- 👥 Cadastro e manutenção de clientes
- 📞 Registro, consulta e atualização de chamados
- 🔐 Segurança baseada em JWT
- 📑 Documentação interativa com Swagger (OpenAPI)

### 🏗️ Arquitetura e Tecnologias

#### Stack Principal

- **Java 21**
- **Spring Boot 3.5.3**
- **Spring Security + JWT**
- **Spring Data JPA**
- **H2 e MySQL** (runtime)
- **SpringDoc OpenAPI 2.8.9**

#### Ferramentas de Desenvolvimento

- **Maven**
- **Swagger UI**
- **Validação com Jakarta Validation**
- **Testes com Spring Boot Starter Test**

### 🚀 Como Executar

#### Pré-requisitos

- Java 21+
- Maven 3.9+

#### Passos para execução

1. Clone o repositório:
```bash
git clone https://github.com/JeffersonBezerraM13/spring-boot-project-angular.git
cd spring-boot-project-angular
```

2. Instale as dependências:
```bash
./mvnw clean install
```

3. Configure o banco de dados (H2 ou MySQL)

4. Execute a aplicação:
```bash
./mvnw spring-boot:run
```

> Por padrão, o perfil de desenvolvimento usa H2.

### 📁 Estrutura do Projeto

```
src/main/java/br/dcx/ufpb/jefferson/springbootprojecthelpbridge/
├── config/           # Configurações globais e beans
├── domain.entities/  # Entidades JPA
├── repositories/     # Interfaces de repositórios
├── resources/        # Utilitários e resposta padrão
├── security/         # JWT, filtros e autenticação
├── services/         # Lógica de negócio
└── SpringBootAngularProjectHelpDeskApplication.java
```

![Estrutura do Projeto](./project-structure.png)

### 🔌 Endpoints da API

Os principais endpoints estão documentados no Swagger.

- `/clients` — Cadastro e gerenciamento de clientes
- `/technicians` — Ações para técnicos e administradores
- `/calls` — Chamados de atendimento técnico

📄 Acesse o Swagger UI:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

![Swagger](./swagger-endpoints.png)

### 🧪 Testes

```bash
# Executar todos os testes
./mvnw test
```

### 📄 Licença

Este projeto está sob a licença **MIT**. Consulte o arquivo `LICENSE` para mais detalhes.

### 👤 Autor

**Jefferson Bezerra**  
📧 jeffebezerram13@gmail.com  
🔗 GitHub: [@JeffersonBezerraM13](https://github.com/JeffersonBezerraM13)

</details>
