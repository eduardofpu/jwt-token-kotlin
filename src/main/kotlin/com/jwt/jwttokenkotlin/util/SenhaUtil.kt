package com.jwt.jwttokenkotlin.util

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class SenhaUtil {

    fun gerarBcrypt(senha: String?): String = BCryptPasswordEncoder().encode(senha)

}