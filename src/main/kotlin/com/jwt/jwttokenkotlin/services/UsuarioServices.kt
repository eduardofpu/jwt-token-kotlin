package com.jwt.jwttokenkotlin.services

import com.jwt.jwttokenkotlin.model.UserLogin
import com.jwt.jwttokenkotlin.model.Usuario
import com.jwt.jwttokenkotlin.representation.UserResp
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface UsuarioServices {
    fun create(usuario: Usuario): Usuario
    fun buscarPorId(id: String): Optional<Usuario>?
    fun listUsers(pageable: Pageable): Page<UserResp>
    fun updateUser(id: String, usuario: Usuario): Usuario
    fun deleteUser(id: String)
    fun email(email: String): Boolean
}