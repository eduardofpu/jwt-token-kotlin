# jwt-token-kotlin

```
Objetivo desse projeto

Contruir uma API kotlin que realiza authentication e authorization com token

```
## Fronte
```
Essa api pode ser consumida pelo postman ou  pelo front   react-jwt: https://github.com/eduardofpu/react-jwt

```
## Requisitos
```
Java 1.8

Ter o mongodb instalado
abrir o terminal:
1 - primeira aba sudo mongod
2 - na outra aba digite apenas mongo

execute:
show databases
use jwt
show tables
db.usuario.find().pretty()
```

# Recursos para testar a api
```
POST  http://localhost:8080/login
POST    http://localhost:8080/v1/register
```
#### Criar usuário para authenticar na api

```
POST  http://localhost:8080/login

{
	"email":"oda@gmail.com",
	"password":"123456"
}

Body  ou header

BearereyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyb2RyaWd1ZXMiLCJleHAiOjE1NTQzMDAyOTV9.Socbf9mvPcjhagJH7fGRPAXWnJfpeB1WBDnDBR9Er4y9aQjsfoVM1CIkEix0lFNCIhNKTjcNDjAmQhAzdlj4SQ

```
#### Insira  Usuários
```

POST http://localhost:8080/v1/register

{
	"username":"teste",
	"email":"teste@gmail.com",
	"password":"123456",
	"admin":null
}

Obs: admin null  é salvo como false, isso foi implementado como tratamento padrão da api será reconhecido como ROLE_USER 
se passar true será reconhecido como ROLE_ADMIN

```

