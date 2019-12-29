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

        val gerarBcryptAdmin = SenhaUtil().gerarBcrypt("admin")
        val usuarioAdmin: Usuario = Usuario("Admin", "admin@admin.com", gerarBcryptAdmin,true)
        usuarioRepository.save(usuarioAdmin)

        val gerarBcryptUser = SenhaUtil().gerarBcrypt("user")
        val user: Usuario = Usuario("User", "user@user.com", gerarBcryptUser,false)
        usuarioRepository.save(user)

        System.out.println("Nome do Administrador: " + usuarioAdmin.username)
        System.out.println("Usuário comum: " + user.username)

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
