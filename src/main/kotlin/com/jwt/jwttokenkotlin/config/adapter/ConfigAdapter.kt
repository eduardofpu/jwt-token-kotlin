package com.jwt.jwttokenkotlin.config.adapter

import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.PageRequest
import org.springframework.data.web.PageableHandlerMethodArgumentResolver
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class ConfigAdapter : WebMvcConfigurer {

    override fun addArgumentResolvers(argumentResolvers: MutableList<HandlerMethodArgumentResolver>) {

        val pageResolver = PageableHandlerMethodArgumentResolver()
        pageResolver.setFallbackPageable(PageRequest(0,10))
        argumentResolvers!!.add(pageResolver)
    }
}