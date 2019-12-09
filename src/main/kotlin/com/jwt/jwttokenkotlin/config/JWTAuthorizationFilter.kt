package com.jwt.jwttokenkotlin.config

import com.jwt.jwttokenkotlin.services.CustomUserDetailService
import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JWTAuthorizationFilter(authenticationManager: AuthenticationManager?,
                             val customUserDetailService: CustomUserDetailService) : BasicAuthenticationFilter(authenticationManager) {


    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {

        val header = request.getHeader(SecurityConstants().HEADER_STRING)
        if (header == null || !header.startsWith(SecurityConstants().TOKEN_PREFIX)) {
            chain.doFilter(request, response)
            return
        }
        val authenticationToken = getAuthenticationToken(request)
        SecurityContextHolder.getContext().authentication = authenticationToken
        chain.doFilter(request, response)
    }

    private fun getAuthenticationToken(request: HttpServletRequest): UsernamePasswordAuthenticationToken? {
        val token = request.getHeader(SecurityConstants().HEADER_STRING) ?: return null
        val username = Jwts.parser().setSigningKey(SecurityConstants().SECRET)
                .parseClaimsJws(token.replace(SecurityConstants().TOKEN_PREFIX, ""))
                .getBody()
                .getSubject()
        val userDetails = customUserDetailService.loadUserByUsername(username)
        return if (username != null) UsernamePasswordAuthenticationToken(username, null, userDetails!!.authorities) else null
    }
}