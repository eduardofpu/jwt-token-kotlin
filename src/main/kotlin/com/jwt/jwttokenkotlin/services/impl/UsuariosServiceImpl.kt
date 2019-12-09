package com.jwt.jwttokenkotlin.services.impl

import com.jwt.jwttokenkotlin.model.Usuario
import com.jwt.jwttokenkotlin.repository.UsuarioRepository
import com.jwt.jwttokenkotlin.services.UsuarioServices
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsuariosServiceImpl(val usuarioRepository: UsuarioRepository) : UsuarioServices {
    override fun buscarPorId(id: String): Optional<Usuario>? = usuarioRepository.findById(id)

    override fun create(usuario: Usuario): Usuario = usuarioRepository.save(usuario)
}