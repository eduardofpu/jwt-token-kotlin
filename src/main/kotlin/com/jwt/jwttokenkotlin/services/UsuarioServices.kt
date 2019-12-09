package com.jwt.jwttokenkotlin.services

import com.jwt.jwttokenkotlin.model.Usuario
import java.util.*

interface UsuarioServices {
    fun create(usuario: Usuario): Usuario
    fun buscarPorId(id: String): Optional<Usuario>?
}