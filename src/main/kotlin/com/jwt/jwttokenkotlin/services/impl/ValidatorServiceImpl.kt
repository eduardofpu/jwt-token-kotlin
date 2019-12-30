package com.jwt.jwttokenkotlin.services.impl

import com.jwt.jwttokenkotlin.exception.log.Log
import com.jwt.jwttokenkotlin.exception.messege.InternalErrorServer
import com.jwt.jwttokenkotlin.services.ValidatorService
import org.springframework.stereotype.Service

@Service
class ValidatorServiceImpl : ValidatorService {

    companion object : Log()

    /**
    Se o id não estiver presente retorna um erro interno

     **/
    override fun validatorId(id: String): Nothing {
        log.info("========= Error interno no Servidor =========")
        throw InternalErrorServer("Error ===> id = " + id)
    }
}