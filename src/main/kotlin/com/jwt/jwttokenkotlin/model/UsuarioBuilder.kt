package com.jwt.jwttokenkotlin.model

import com.jwt.jwttokenkotlin.representation.UserResp
import org.springframework.data.domain.Page


class UsuarioBuilder() {

    var users: Page<Usuario>? = null

     fun users(users: Page<Usuario>): UsuarioBuilder {
        this.users = users
        return this
    }

    companion object {
        fun builder(): UsuarioBuilder {
            return UsuarioBuilder()
        }
    }

    fun build(): Page<UserResp> {
        return this.users!!.map { usuario ->
            UserResp(
                    usuario.username!!,
                    usuario.email!!,
                    usuario.id
            )
        }
    }

}