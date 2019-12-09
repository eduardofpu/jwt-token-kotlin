package com.jwt.jwttokenkotlin

import com.jwt.jwttokenkotlin.model.Usuario
import com.jwt.jwttokenkotlin.repository.UsuarioRepository
import com.jwt.jwttokenkotlin.util.SenhaUtil
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JwtTokenKotlinApplication(val usuarioRepository: UsuarioRepository) : CommandLineRunner {

    override fun run(vararg args: String?) {
        usuarioRepository.deleteAll()

        val gerarBcrypt = SenhaUtil().gerarBcrypt("123456")
        val usuario: Usuario = Usuario("oda", "oda@gmail.com", gerarBcrypt,true)
        usuarioRepository.save(usuario)

        System.out.println("Nome: " + usuario.username)

    }
}

/**
show databases
show tables
use jwt
db.usuario.find().pretty()
 **/
fun main(args: Array<String>) {
    runApplication<JwtTokenKotlinApplication>(*args)
}
