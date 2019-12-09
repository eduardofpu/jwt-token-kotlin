package com.jwt.jwttokenkotlin.services

import com.jwt.jwttokenkotlin.repository.UsuarioRepository
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import java.util.*

@Component
class CustomUserDetailService(val usuarioRepository: UsuarioRepository) : UserDetailsService {
    override fun loadUserByUsername(email: String?): UserDetails? {
        val user = Optional.ofNullable(usuarioRepository.findByEmail(email))
                .orElseThrow({ UsernameNotFoundException("User not found") })
        val authorityListAdmin = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN")
        val authorityListUser = AuthorityUtils.createAuthorityList("ROLE_USER")

        return User(user.email, user.password.toString(), if (user.admin!!) authorityListAdmin else authorityListUser)
    }
}