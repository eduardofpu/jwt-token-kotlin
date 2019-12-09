package com.jwt.jwttokenkotlin.repository

import com.jwt.jwttokenkotlin.model.Usuario
import org.springframework.data.mongodb.repository.MongoRepository


interface UsuarioRepository: MongoRepository<Usuario, String> {
    fun findByUsername(username: String?): Usuario
    fun findByEmail(email: String?): Usuario
}