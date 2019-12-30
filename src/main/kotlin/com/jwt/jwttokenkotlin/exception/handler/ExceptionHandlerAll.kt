package com.jwt.jwttokenkotlin.exception.handler

import com.jwt.jwttokenkotlin.exception.detail.Detail
import com.jwt.jwttokenkotlin.exception.detail.Detail.Builder
import com.jwt.jwttokenkotlin.exception.messege.InternalErrorServer
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*


@ControllerAdvice
class ExceptionHandlerAll : ResponseEntityExceptionHandler() {

    @ExceptionHandler(InternalErrorServer::class)
    fun handleExceptionInternal(ex: InternalErrorServer): ResponseEntity<Detail> {

        val errorDetails = Builder()
                .timestamp(Date().time)
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .title("Error interno no server")
                .detail(ex.message)
                .developerMessage(ex.javaClass.name)
                .field("Campo id")
                .fieldMessage("O id não pode ser null")
                .build()
        return ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR)
    }

}