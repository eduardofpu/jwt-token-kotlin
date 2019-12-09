package com.jwt.jwttokenkotlin.controller

import com.jwt.jwttokenkotlin.Errors.Response
import com.jwt.jwttokenkotlin.dtos.UsuarioDtos
import com.jwt.jwttokenkotlin.model.Usuario
import com.jwt.jwttokenkotlin.services.UsuarioServices
import com.jwt.jwttokenkotlin.util.SenhaUtil
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("v1")
class UsuarioController(val usuarioServices: UsuarioServices) {

    /**
     *  @PostMapping("/protected/auth/register")  para USER    verificar em SecurityConfig
     *  @PostMapping("/admin/auth/register")      PARA ADMIN   verificar em SecurityConfig
     *  @PreAuthorize("hasRole('USER')")          O MESMO QUE  @PostMapping("/protected/register")
     *  @PreAuthorize("hasRole('ADMIN')")         O MESMO QUE  @PostMapping("/admin/register")
     *  **/

    @PostMapping("/register")
    fun create(@Valid @RequestBody usuarioDtos: UsuarioDtos,
               result: BindingResult): ResponseEntity<Response<UsuarioDtos>> {
        val response: Response<UsuarioDtos> = Response<UsuarioDtos>()
        validarUsuario(usuarioDtos, result)

        if (result.hasErrors()) {
            for (erro in result.allErrors) response.erros.add(erro.defaultMessage.toString())
            return ResponseEntity.badRequest().body(response)
        }

        val usuario: Usuario = converterDtoParaUsuario(usuarioDtos, result)
        usuarioServices.create(usuario)
        response.data = converterUsuarioDtos(usuario)
        return ResponseEntity.ok(response)
    }

    private fun validarUsuario(usuarioDtos: UsuarioDtos, result: BindingResult) {
        if (usuarioDtos.username == null) {
            result.addError(ObjectError("usuario",
                    "Usuário não informado."))
            return
        }
    }

    private fun converterUsuarioDtos(usuario: Usuario): UsuarioDtos =
            UsuarioDtos(usuario.username, usuario.email, "private", usuario.admin, "private")

    private fun converterDtoParaUsuario(usuarioDtos: UsuarioDtos,
                                        result: BindingResult): Usuario {
        if (usuarioDtos.username == null) {
            result.addError(ObjectError("usuario",
                    "Usuário não encontrado."))
        }
        return Usuario(usuarioDtos.username, usuarioDtos.email, SenhaUtil().gerarBcrypt(usuarioDtos.password), usuarioDtos.admin, usuarioDtos.id)
    }
}