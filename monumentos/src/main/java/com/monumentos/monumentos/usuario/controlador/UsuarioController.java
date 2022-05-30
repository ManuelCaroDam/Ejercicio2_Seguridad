package com.monumentos.monumentos.usuario.controlador;

import com.monumentos.monumentos.seguridad.jwt.JwtProvider;
import com.monumentos.monumentos.usuario.modelo.Usuario;
import com.monumentos.monumentos.usuario.servicio.UsuarioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioServicio usuarioServicio;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @PostMapping("/register")

    public ResponseEntity<?> newUser(@RequestBody NewUserRequest newUserRequest) {

    }



}
