# HelpBridge API

<details>
<summary><strong>🇧🇷 Português</strong></summary>

## Sobre o Projeto

**HelpBridge** é uma API REST para gerenciamento de chamados de suporte técnico em qualquer negócio. O sistema permite que **clientes** acompanhem chamados e seus respectivos **técnicos**, enquanto **administradores técnicos** podem atualizar o status e controlar as requisições. Todos os chamados são persistidos, permitindo histórico e reabertura futura.

### Principais Funcionalidades

- Gerenciamento completo de técnicos e técnicos administradores
- Cadastro e manutenção de clientes
- Registro, consulta e atualização de chamados
-  Segurança baseada em JWT
-  Documentação interativa com Swagger (OpenAPI)

###  Arquitetura e Tecnologias

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

###  Como Executar

#### Pré-requisitos

- Java 21+
- Maven 3.9+

#### Passos

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

###  Estrutura do Projeto

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

###  Endpoints da API

Os principais endpoints estão documentados no Swagger:  
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

####  Clientes

| Método | Endpoint         | Descrição                                   |
|--------|------------------|---------------------------------------------|
| GET    | `/clients/{id}`  | Retorna os dados de um cliente específico.  |
| PUT    | `/clients/{id}`  | Atualiza os dados de um cliente existente.  |
| DELETE | `/clients/{id}`  | Remove um cliente pelo ID.                  |
| GET    | `/clients`       | Lista todos os clientes cadastrados.        |
| POST   | `/clients`       | Cria um novo cliente.                       |

####  Técnicos

| Método | Endpoint              | Descrição                                    |
|--------|-----------------------|----------------------------------------------|
| GET    | `/technicians/{id}`   | Retorna os dados de um técnico específico.   |
| PUT    | `/technicians/{id}`   | Atualiza os dados de um técnico existente.   |
| DELETE | `/technicians/{id}`   | Remove um técnico pelo ID.                   |
| GET    | `/technicians`        | Lista todos os técnicos cadastrados.         |
| POST   | `/technicians`        | Cria um novo técnico.                        |

#### Chamados

| Método | Endpoint       | Descrição                               |
|--------|----------------|-------------------------------------------|
| GET    | `/calls/{id}`  | Busca um chamado específico pelo ID.     |
| PUT    | `/calls/{id}`  | Atualiza um chamado existente.           |
| GET    | `/calls`       | Lista todos os chamados cadastrados.     |
| POST   | `/calls`       | Cria um novo chamado.                    |

### Testes

```bash
./mvnw test
```

### Licença

Este projeto está sob a licença **MIT**. Consulte o arquivo `LICENSE` para mais detalhes.

### Autor

**Jefferson Bezerra**  
📧 jeffebezerram13@gmail.com  
🔗 GitHub: [@JeffersonBezerraM13](https://github.com/JeffersonBezerraM13)

</details>

---

<details>
<summary><strong>🇺🇸 English</strong></summary>

## About the Project

**HelpBridge** is a REST API for managing technical support tickets for any business. The system allows **clients** to track their tickets and the assigned **technicians**, while **technical administrators** can update ticket statuses and handle requests. All tickets are persisted, enabling a history and future reopening.

### Main Features

-  Full management of technicians and technical administrators
- Client registration and maintenance
- Ticket creation, consultation, and update
-  JWT-based authentication and security
- Interactive documentation using Swagger (OpenAPI)

###  Architecture & Technologies

#### Main Stack

- **Java 21**
- **Spring Boot 3.5.3**
- **Spring Security + JWT**
- **Spring Data JPA**
- **H2 and MySQL**
- **SpringDoc OpenAPI 2.8.9**

#### Development Tools

- **Maven**
- **Swagger UI**
- **Validation with Jakarta Validation**
- **Testing with Spring Boot Starter Test**

### How to Run

#### Requirements

- Java 21+
- Maven 3.9+

#### Steps

1. Clone the repository:
```bash
git clone https://github.com/JeffersonBezerraM13/spring-boot-project-angular.git
cd spring-boot-project-angular
```

2. Install dependencies:
```bash
./mvnw clean install
```

3. Configure the database (H2 or MySQL)

4. Run the application:
```bash
./mvnw spring-boot:run
```

### Project Structure

```
src/main/java/br/dcx/ufpb/jefferson/springbootprojecthelpbridge/
├── config/           # Global configuration and beans
├── domain.entities/  # JPA entities
├── repositories/     # Repository interfaces
├── resources/        # Utilities and standard response models
├── security/         # JWT, filters, and authentication
├── services/         # Business logic layer
└── SpringBootAngularProjectHelpDeskApplication.java
```

### API Endpoints

Main endpoints are documented via Swagger:  
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

####  Clients

| Method | Endpoint         | Description                            |
|--------|------------------|----------------------------------------|
| GET    | `/clients/{id}`  | Retrieves client data by ID.           |
| PUT    | `/clients/{id}`  | Updates an existing client.            |
| DELETE | `/clients/{id}`  | Deletes a client by ID.                |
| GET    | `/clients`       | Lists all registered clients.          |
| POST   | `/clients`       | Creates a new client.                  |

####  Technicians

| Method | Endpoint              | Description                             |
|--------|-----------------------|-----------------------------------------|
| GET    | `/technicians/{id}`   | Retrieves technician data by ID.        |
| PUT    | `/technicians/{id}`   | Updates an existing technician.         |
| DELETE | `/technicians/{id}`   | Deletes a technician by ID.             |
| GET    | `/technicians`        | Lists all registered technicians.       |
| POST   | `/technicians`        | Creates a new technician.               |

#### Calls

| Method | Endpoint       | Description                         |
|--------|----------------|-------------------------------------|
| GET    | `/calls/{id}`  | Retrieves a ticket by ID.           |
| PUT    | `/calls/{id}`  | Updates an existing ticket.         |
| GET    | `/calls`       | Lists all registered tickets.       |
| POST   | `/calls`       | Creates a new support ticket.       |

### Testing

```bash
./mvnw test
```

### License

This project is licensed under the **MIT** license. See the `LICENSE` file for details.

### Author

**Jefferson Bezerra**  
📧 jeffebezerram13@gmail.com  
🔗 GitHub: [@JeffersonBezerraM13](https://github.com/JeffersonBezerraM13)

</details>

---

## Tech Stack Summary

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)  
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-6DB33F?style=for-the-badge&logo=spring&logoColor=white)  
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)  
![H2 Database](https://img.shields.io/badge/H2-1.4.200-0066CC?style=for-the-badge&logo=h2&logoColor=white)  
![Maven](https://img.shields.io/badge/Maven-3.9+-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)  
![Swagger](https://img.shields.io/badge/Swagger-UI-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)

---

## Futuras Implementações

Docker & Docker Compose – Containerização da aplicação

---

## Status do Projeto

**Em andamento**