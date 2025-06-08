# PredMed - API Quarkus para Triagem e Diagnóstico Médico

[![Build](https://img.shields.io/badge/build-passing-brightgreen)](https://maven.apache.org/)
[![Quarkus](https://img.shields.io/badge/quarkus-3.23.0-blue)](https://quarkus.io/)

## 🩺 Sobre o Projeto

**PredMed** é uma API desenvolvida com [Quarkus](https://quarkus.io/) voltada para:

- **Triagem manual** de pacientes  
- **Diagnóstico médico assistido por IA**  
- **Gerenciamento seguro de usuários e permissões**

Seu objetivo é fornecer uma base moderna, performática e extensível para soluções médicas baseadas em dados e inteligência artificial.

---

## 🔌 API Endpoints (Backend)

### Autenticação

- **POST /api/login** — Autenticação de usuários com JWT

### Usuarios

- **GET /api/usuarios** — Listar todos os usuarios
- **GET /api/usuarios/{id}** — Buscar usuario por ID
- **POST /api/usuarios** — Cadastrar novo usuario
- **PUT /api/usuarios/{id}** — Atualizar dados do usuario
- **DELETE /api/usuarios/{id}** — Excluir usuario

### Pacientes

- **GET /api/pacientes** — Listar todos os pacientes
- **GET /api/pacientes/{id}** — Buscar paciente por ID
- **POST /api/pacientes** — Cadastrar novo paciente
- **PUT /api/pacientes/{id}** — Atualizar dados do paciente
- **DELETE /api/pacientes/{id}** — Excluir paciente

### Triagem
- **GET /api/triagem/{id}** - Buscar triagem por id
- **GET /api/triagem/paciente/{id}** — Listar triagens de um paciente
- **POST /api/triagem/{pacienteId}** — Criar nova triagem para paciente
- **PUT /api/pacientes/{id}** — Atualizar dados da triagem
- **DELETE /api/pacientes/{id}** — Excluir triagem 

### Diagnóstico
  
- **GET /api/diagnostico/{id}** — Buscar diagnostico por id
- **POST /api/diagnostico/{triagemId}** — Gerar diagnóstico para triagem

---

## ⚙️ Tecnologias e Dependências

- **Quarkus 3.23.0**
- **JWT (Autenticação)**
- **Hibernate ORM com Panache**
- **Oracle JDBC**
- **RESTEasy com Jackson**
- **Apache HttpClient**
- **Gson para JSON**

Veja o `pom.xml` completo [aqui](https://github.com/FIAP-1TDSPS-2024/predmed-quarkus/blob/main/pom.xml).

---

## 🚀 Como Executar Localmente

### Pré-requisitos

- JDK 18+  
- Maven 3.9+  
- Banco Oracle configurado (ou container)

### Passos

```bash
# Clone o repositório
git clone https://github.com/FIAP-1TDSPS-2024/predmed-quarkus.git
cd predmed-quarkus

# Atualize as credenciais do banco de dados OracleSQL no arquivo `./src/main/resources/application.properties`

```
quarkus.datasource.username=seu_usuario
quarkus.datasource.password=sua_senha
```

# Configure a chave privada JWT como variável de ambiente:

- Obs: A chave privada está no caminho `./server/java/src/main/resources/META-INF/resources/privateKey.pem`

**Windows (PowerShell)**:

```powershell
$env:JWT_PRIVATE_KEY = cat "C:\caminho\para\sua\chave.key" -Raw
```

**macOS/Linux (Bash/ZSH)**:

```bash
export JWT_PRIVATE_KEY=$(cat "/caminho/para/sua/chave.key")
```

# Inicialize o projeto

```
./mvnw quarkus:dev
```

# O projeto será executado na porta 8080 - [http://localhost:8080](http://localhost:8080)

> ⚠️ **Importante:** para que o JWT seja aceito ao rodar a aplicação localmente, é necessário alterar o valor de `issuer` na configuração de segurança do projeto para `http://localhost:8080`. Caso contrário, a geração e validação do token não funcionarão corretamente.

---

## 🔐 Autenticação

A autenticação é baseada em JWT. Envie o token no cabeçalho `Authorization`:

```
Authorization: Bearer <seu_token_jwt>
```

---

## 📂 Estrutura do Projeto

- `src/main/java`: código principal da aplicação  
- `src/test/java`: testes unitários e de integração  
- `resources/META-INF`: configurações de segurança, JWT, roles etc.
