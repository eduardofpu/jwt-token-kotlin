package com.jwt.jwttokenkotlin.config

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import com.jwt.jwttokenkotlin.model.Usuario
import com.fasterxml.jackson.databind.ObjectMapper
import com.jwt.jwttokenkotlin.model.UserLogin
import org.springframework.security.core.Authentication
import javax.security.sasl.AuthenticationException
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletRequest
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import lombok.SneakyThrows
import org.springframework.security.core.userdetails.User
import java.util.*
import javax.servlet.FilterChain

// Authentication com token
class JWTAuthenticationFilter: UsernamePasswordAuthenticationFilter {

    constructor(authenticationManager: AuthenticationManager) : super() {
        this.authenticationManager = authenticationManager
    }

    @Throws(AuthenticationException::class)
    @SneakyThrows
    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {

        val params = request.inputStream

        val user = ObjectMapper().readValue(params, UserLogin::class.java)
        return authenticationManager.authenticate(UsernamePasswordAuthenticationToken(user.email, user.password))
    }

     @SneakyThrows
     override fun successfulAuthentication(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain?, authResult: Authentication) {

        val username = (authResult.principal as User).getUsername()
        val token = Jwts
                .builder()
                .setSubject(username)
                .setExpiration(Date(System.currentTimeMillis() + SecurityConstants().EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants().SECRET)
                .compact()
        //Para pegar o token do body ao invés de pegar o token no header
        val bearerToken = SecurityConstants().TOKEN_PREFIX + token
        response.writer.write(bearerToken)
        response.addHeader(SecurityConstants().HEADER_STRING, bearerToken)
    }


}