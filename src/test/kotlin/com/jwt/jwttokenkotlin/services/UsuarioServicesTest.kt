package com.jwt.jwttokenkotlin.services

import com.jwt.jwttokenkotlin.model.Usuario
import com.jwt.jwttokenkotlin.repository.UsuarioRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest
class UsuarioServicesTest {

    @MockBean
    private val usuarioRepository: UsuarioRepository? = null

    @Autowired
    private val usuarioServices: UsuarioServices? = null

    private val username: String = "Eduardo"
    private val email: String = "eduardo@gmail.com"
    private val password: String = "123456"
    private val id: String = "1"


    @Before
    @Throws(Exception::class)
    fun setUp() {
        BDDMockito.given(usuarioRepository?.save(Mockito.any(Usuario::class.java)))
                .willReturn(usuario())
        BDDMockito.given(usuarioRepository?.findById(id)).willReturn(Optional.ofNullable(usuario()))
    }

    @Test
    fun createUsuario() {
        val usuario: Usuario? = this.usuarioServices?.create(usuario())
        Assert.assertNotNull(usuario)
    }

    @Test
    fun testBuscarFuncionarioPorId() {
        val usuario: Optional<Usuario>? = this.usuarioServices?.buscarPorId(id)
        Assert.assertNotNull(usuario)
    }

    private fun usuario(): Usuario =
            Usuario(username, email,password, false, id)
}