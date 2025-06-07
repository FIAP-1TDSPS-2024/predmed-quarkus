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

## 🌐 API em Produção

A API está disponível em:

```
https://predmed-quarkus-01-8e46d0e5271b.herokuapp.com/
```

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

# Executar em modo dev
./mvnw quarkus:dev
```

A API local ficará disponível em `http://localhost:8080`.

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