package com.jwt.jwttokenkotlin.services

import com.jwt.jwttokenkotlin.dtos.UsuarioDtoId

interface ValidatorService {
    fun validatorId(id: String): String
}