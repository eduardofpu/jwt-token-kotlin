package com.jwt.jwttokenkotlin.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.NotEmpty

@Document
data class Usuario(
        val username: String,
        val email: String?,
        val password: String?,
        @NotEmpty
        val admin: Boolean = false,
        @Id val id: String? = null
)