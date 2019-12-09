package com.jwt.jwttokenkotlin.controller

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.jwt.jwttokenkotlin.dtos.UsuarioDtos
import com.jwt.jwttokenkotlin.model.Usuario
import com.jwt.jwttokenkotlin.services.UsuarioServices
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class UsuarioControllerTest {

    @Autowired
    private val mvc: MockMvc? = null

    @MockBean
    private val usuarioServices: UsuarioServices? = null

    private val urlBase: String = "/v1/register"
    private val username: String = "Eduardo"
    private val email: String = "eduardo@gmail.com"
    private val password: String = "123456"
    private val id: String = "1"


    @Test
    @Throws(Exception::class)
    fun testProibidoCadastrarLancamento() {
        val usuario: Usuario = obterDadosUsuario()

        BDDMockito.given(usuarioServices?.create(obterDadosUsuario()))
                .willReturn(usuario)

        mvc!!.perform(MockMvcRequestBuilders.post(urlBase)
                .content(obterJsonRequisicaoPost())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.data.username").value(username))
                .andExpect(jsonPath("$.data.email").value(email))
//                .andExpect(jsonPath("$.data.password").value(password))
                .andExpect(jsonPath("$.erros").isEmpty())
    }


    @Throws(JsonProcessingException::class)
    private fun obterJsonRequisicaoPost(): String {
        val usuarioDtos: UsuarioDtos = UsuarioDtos(username,email,password,true ,id)
        val mapper = ObjectMapper()
        return mapper.writeValueAsString(usuarioDtos)
    }

    private fun obterDadosUsuario(): Usuario =
            Usuario(username, email, password, true, id)

    private fun usuario(): Usuario =
            Usuario(username, email, password, true , id)
}