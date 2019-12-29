package com.jwt.jwttokenkotlin.services.impl

import com.jwt.jwttokenkotlin.exception.messege.InternalErrorServer
import com.jwt.jwttokenkotlin.exception.log.Log
import com.jwt.jwttokenkotlin.model.Usuario
import com.jwt.jwttokenkotlin.model.UsuarioBuilder
import com.jwt.jwttokenkotlin.repository.UsuarioRepository
import com.jwt.jwttokenkotlin.representation.UserResp
import com.jwt.jwttokenkotlin.services.UsuarioServices
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsuariosServiceImpl(val usuarioRepository: UsuarioRepository) : UsuarioServices {

    companion object : Log()

    override fun buscarPorId(id: String): Optional<Usuario>? = usuarioRepository.findById(id)

    override fun create(usuario: Usuario): Usuario = usuarioRepository.save(usuario)

    override fun listUsers(pageable: Pageable): Page<UserResp> {
        val users = usuarioRepository.findAll(pageable)
        return UsuarioBuilder.builder().users(users).build()
    }

    override fun updateUser(id: String, usuario: Usuario): Usuario {
        validatorId(id)
        usuario.id = id
        return usuarioRepository.save(usuario)
    }

    override fun deleteUser(id: String): Nothing {
        validatorId(id)
        val idUser = usuarioRepository.findById(id)
        usuarioRepository.delete(idUser.get())
    }

    /**
     Se o id não estiver presente retorno um erro interno

     **/
    private fun validatorId(id: String): Nothing {
        log.info("========= Error interno no Servidor =========")
        throw InternalErrorServer("Error ===> id = " + id)
    }
}




