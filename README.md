# Java Spring Boot Back-End Projects

A collection of Spring Boot applications demonstrating various features and use cases, including REST APIs, web applications, security, AI integration, and database management.

## 📋 Table of Contents

- [Overview](#overview)
- [Projects](#projects)
  - [1. API Gestor Financeiro](#1-api-gestor-financeiro)
  - [2. Shopping Delivery](#2-shopping-delivery)
  - [3. Spring AI](#3-spring-ai)
  - [4. Voice Transcription](#4-voice-transcription)
  - [5. Web API](#5-web-api)
  - [6. Web Security](#6-web-security)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)

## Overview

This repository contains **6 independent Spring Boot projects**, each demonstrating different aspects of backend development:

- **RESTful APIs** with comprehensive CRUD operations
- **Authentication & Authorization** using JWT and Spring Security
- **AI Integration** with OpenAI (chat, image generation, transcription)
- **Web Applications** with server-side rendering (Thymeleaf)
- **Database Management** with JPA and multiple database options
- **API Documentation** with OpenAPI/Swagger

## Projects

### 1. API Gestor Financeiro

**Location:** `api.gestor.financeiro/`

**Description:** Personal finance management API for tracking income, expenses, accounts, and credit cards.

**Key Features:**
- User registration and authentication (JWT-based)
- Income and expense tracking
- Account and credit card management
- Transaction filtering and reporting
- Role-based access control

**Technology Stack:**
- Java 17
- Spring Boot 3.2.5
- Spring Security + JWT (Auth0)
- Spring Data JPA
- H2 Database (development)
- PostgreSQL (production)
- OpenAPI/Swagger UI
- Docker support

**Main Components:**
- Controllers: Authentication, Usuario, Conta, Cartao, Lancamento, TipoLancamento, FonteLancamento
- Security: JWT token service, security filters, token blacklist
- Models: Usuario, Conta, Cartao, Lancamento, TipoLancamento, FonteLancamento
- Custom exceptions for business logic validation

**Documentation:** See `api.gestor.financeiro/README.md` for detailed information.

---

### 2. Shopping Delivery

**Location:** `shopping-delivery/shopping-delivery/`

**Description:** E-commerce delivery API for managing customers, products, orders, and deliveries.

**Key Features:**
- Customer management with CPF and email validation
- Address management (multiple addresses per customer)
- Product/item catalog with inventory
- Order management with status tracking
- Order items with automatic subtotal calculations

**Technology Stack:**
- Java 17
- Spring Boot 3.3.7
- Spring Data JPA
- Flyway (database migrations)
- H2 Database
- OpenAPI/Swagger

**Main Components:**
- Controllers: Cliente, Endereco
- Models: Cliente, Endereco, Item, Pedido, ItemPedido, StatusPedido (enum)
- Services: ClienteService, EnderecoService, ItemService, PedidoService, ItemPedidoService
- Database schema with foreign key relationships

**Order Status Flow:**
- PENDENTE → APROVADO → ENTREGUE
- CANCELADO (at any stage)

---

### 3. Spring AI

**Location:** `spring-ai/`

**Description:** Demonstration of Spring AI framework integration with OpenAI for chat, image generation, and recipe creation.

**Key Features:**
- Chat completion with GPT models (configurable options)
- Image generation using DALL-E
- Recipe generation based on ingredients, cuisine, and dietary restrictions
- Configurable AI parameters (temperature, model selection)

**Technology Stack:**
- Java 21
- Spring Boot 3.4.3
- Spring AI 1.0.0-M8
- OpenAI API integration

**Main Components:**
- Controller: GenerativeAIController
- Services: ChatService, ImageService, RecipeService
- Endpoints:
  - `/ask-ai` - Simple chat completion
  - `/ask-ai-options` - Chat with configurable options
  - `/recipe-creator` - Generate recipes
  - `/generate-image` - Generate images

**Configuration:** Requires `OPEN_AI_API_KEY` environment variable.

---

### 4. Voice Transcription

**Location:** `voice-transcription/`

**Description:** Voice-to-text transcription service using OpenAI's transcription capabilities.

**Key Features:**
- Audio file transcription
- Voice-to-text conversion
- REST API for transcription requests

**Technology Stack:**
- Java 21
- Spring Boot 3.4.5
- Spring AI 1.0.0-RC1
- OpenAI API

**Main Components:**
- Controller: TranscriptionController
- Service: TranscriptionService
- Configuration: WebConfig for CORS and file handling

---

### 5. Web API

**Location:** `web-api/`

**Description:** Web application with server-side rendering using Thymeleaf for managing customers, items, and orders.

**Key Features:**
- Customer management
- Item/product catalog
- Order management
- Bootstrap-based UI
- Server-side rendering with Thymeleaf

**Technology Stack:**
- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- Thymeleaf (templates)
- Bootstrap 5.3.2
- jQuery 3.7.1
- MySQL Database

**Main Components:**
- Controllers: IndexController, ClienteController, ItemController, PedidoController
- Models: Cliente, Item, Pedido
- Services: ClienteService, ItemService, PedidoService
- HTML templates with Bootstrap styling

---

### 6. Web Security

**Location:** `web-security/`

**Description:** Spring Security demonstration with user authentication and authorization.

**Key Features:**
- User registration and login
- Spring Security configuration
- Thymeleaf integration with Spring Security
- User management

**Technology Stack:**
- Java 17
- Spring Boot 3.2.0
- Spring Security
- Spring Data JPA
- Thymeleaf with Spring Security extras
- MySQL Database

**Main Components:**
- Configuration: WebSecurityConfig
- Controller: AuthenticationController
- Model: User
- Service: AuthenticationService

---

## Technology Stack

### Common Technologies
- **Java:** 17 (most projects) / 21 (AI projects)
- **Spring Boot:** 3.2.0 - 3.4.5
- **Maven:** Build tool (all projects)
- **Lombok:** Boilerplate reduction
- **Spring Data JPA:** Database access

### Database Options
- **H2:** In-memory database (development)
- **PostgreSQL:** Production database (api.gestor.financeiro)
- **MySQL:** Production database (web-api, web-security)

### Security
- **Spring Security:** Authentication and authorization
- **JWT (Auth0):** Token-based authentication
- **Thymeleaf Security Extras:** Security integration in views

### AI & Integration
- **Spring AI:** AI framework integration
- **OpenAI API:** Chat, image generation, transcription

### Documentation
- **OpenAPI/Swagger:** API documentation (api.gestor.financeiro, shopping-delivery)

### Frontend (Web Projects)
- **Thymeleaf:** Server-side templating
- **Bootstrap:** CSS framework
- **jQuery:** JavaScript library

### DevOps
- **Docker:** Containerization (api.gestor.financeiro)
- **Flyway:** Database migrations (shopping-delivery)

## Project Structure

Each project follows the standard Spring Boot structure:

```
project-name/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/sskings/
│   │   │       └── [package]/
│   │   │           ├── [Application].java
│   │   │           ├── controllers/
│   │   │           ├── services/
│   │   │           ├── repositories/
│   │   │           ├── models/
│   │   │           └── [other packages]
│   │   └── resources/
│   │       ├── application.properties / application.yml
│   │       └── [other resources]
│   └── test/
│       └── java/
├── pom.xml
├── mvnw / mvnw.cmd
└── README.md (if available)
```

## Getting Started

### Prerequisites
- Java 17 or 21 (depending on project)
- Maven 3.6+ (or use Maven Wrapper included in projects)
- Database (H2, PostgreSQL, or MySQL - depending on project)
- OpenAI API Key (for AI projects: spring-ai, voice-transcription)

### Running a Project

1. **Navigate to the project directory:**
   ```bash
   cd [project-name]
   ```

2. **Configure application properties:**
   - Edit `src/main/resources/application.properties` or `application.yml`
   - Set database connection details if needed
   - For AI projects, set `OPEN_AI_API_KEY` environment variable

3. **Build and run:**
   ```bash
   ./mvnw spring-boot:run
   ```
   Or on Windows:
   ```cmd
   mvnw.cmd spring-boot:run
   ```

4. **Access the application:**
   - REST APIs: `http://localhost:8080`
   - Swagger UI (if available): `http://localhost:8080/swagger-ui.html`
   - Web applications: `http://localhost:8080`

### Docker (api.gestor.financeiro)

```bash
cd api.gestor.financeiro
docker-compose up
```

## Notes

- Each project is **independent** and can be run separately
- Projects use different Spring Boot versions - check individual `pom.xml` files
- Database configurations vary by project - check `application.properties` or `application.yml`
- AI projects require valid OpenAI API keys
- Some projects include test suites in `src/test/java`

## License

This repository contains demonstration projects for learning and development purposes.
