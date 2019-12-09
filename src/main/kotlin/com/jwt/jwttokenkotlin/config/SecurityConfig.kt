package com.jwt.jwttokenkotlin.config


import com.jwt.jwttokenkotlin.services.CustomUserDetailService
import lombok.SneakyThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.cors.CorsConfiguration

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig(val customUserDetailService: CustomUserDetailService) : WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.cors().configurationSource { request -> CorsConfiguration().applyPermitDefaultValues() }
                .and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, SecurityConstants().SIGN_UP_URL).permitAll()
                .antMatchers("/*/protected/**").hasAnyRole("USER")
                .antMatchers("/*/admin/**").hasAnyRole("ADMIN")
                .and()
                .addFilter(JWTAuthenticationFilter(authenticationManager()))
                .addFilter(JWTAuthorizationFilter(authenticationManager(), customUserDetailService))
    }

    @SneakyThrows
    @Autowired
    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!.userDetailsService<CustomUserDetailService>(customUserDetailService).passwordEncoder(BCryptPasswordEncoder())

    }
}