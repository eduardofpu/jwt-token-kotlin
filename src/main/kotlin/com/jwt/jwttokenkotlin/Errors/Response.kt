package com.jwt.jwttokenkotlin.Errors

data class Response<T> (
        val erros: ArrayList<String> = arrayListOf(),
        var data: T? = null
)