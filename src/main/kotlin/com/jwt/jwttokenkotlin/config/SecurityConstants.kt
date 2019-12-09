package com.jwt.jwttokenkotlin.config

class SecurityConstants {
    val SECRET = "DevDojoFoda"
    val TOKEN_PREFIX = "Bearer"
    val HEADER_STRING = "Authorization"
    val SIGN_UP_URL = "/users/sign-up"
    val EXPIRATION_TIME = 86400000L // expira em um dia

}

//fun main(args: Array<String>) {
//    //Converter milli segundos em um dia
//    System.out.println(TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS))
//}