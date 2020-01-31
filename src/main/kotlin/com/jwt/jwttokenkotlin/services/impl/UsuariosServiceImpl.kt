package com.jwt.jwttokenkotlin.services.impl

import com.jwt.jwttokenkotlin.model.Usuario
import com.jwt.jwttokenkotlin.model.UsuarioBuilder
import com.jwt.jwttokenkotlin.repository.UsuarioRepository
import com.jwt.jwttokenkotlin.representation.UserResp
import com.jwt.jwttokenkotlin.services.UsuarioServices
import com.jwt.jwttokenkotlin.services.ValidatorService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsuariosServiceImpl(val validatorService: ValidatorService,
                          val usuarioRepository: UsuarioRepository) : UsuarioServices {

    override fun email(email: String): Boolean {
        val findByEmail = usuarioRepository.findByEmail(email)
        if(findByEmail==null){
            println("Error")
        }
        return findByEmail.admin
    }

    override fun buscarPorId(id: String): Optional<Usuario>? = usuarioRepository.findById(id)

    override fun create(usuario: Usuario): Usuario = usuarioRepository.save(usuario)

    override fun listUsers(pageable: Pageable): Page<UserResp> {
        val users = usuarioRepository.findAll(pageable)
        return UsuarioBuilder.builder().users(users).build()
    }

    override fun updateUser(id: String, usuario: Usuario): Usuario {
        validatorService.validatorId(id)
        usuario.id = id
        return usuarioRepository.save(usuario)
    }

    override fun deleteUser(id: String) {
        validatorService.validatorId(id)
        val idUser = usuarioRepository.findByIdOrNull(id)
        if (idUser != null) {
            usuarioRepository.delete(idUser)
        }
    }

}




