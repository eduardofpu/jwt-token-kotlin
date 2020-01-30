package com.jwt.jwttokenkotlin.controller

import com.jwt.jwttokenkotlin.Errors.Response
import com.jwt.jwttokenkotlin.dtos.UsuarioDtos
import com.jwt.jwttokenkotlin.model.UserLogin
import com.jwt.jwttokenkotlin.model.Usuario
import com.jwt.jwttokenkotlin.representation.UserResp
import com.jwt.jwttokenkotlin.services.UsuarioServices
import com.jwt.jwttokenkotlin.util.SenhaUtil
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(path = ["v1"])
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

    @PostMapping("/email")
    @ResponseStatus(HttpStatus.OK)
    fun listUsers(@RequestBody email: UserLogin): Boolean {

        val userPrincipal = email.email
        return  usuarioServices.email(userPrincipal)
    }

    @GetMapping("admin/users")
    fun listUsers(pageable: Pageable, authentication:Authentication): Page<UserResp> {

        println(authentication.principal)

        return  usuarioServices.listUsers(pageable)
    }
    @PutMapping("admin/edit/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    fun updateUser(@PathVariable("id") id: String, @RequestBody usuardio: Usuario, authentication:Authentication): Usuario{
        return usuarioServices.updateUser(id, usuardio)
    }

    @DeleteMapping("admin/del/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(@PathVariable("id") id: String){
        usuarioServices.deleteUser(id)
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