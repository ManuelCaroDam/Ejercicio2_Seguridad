package com.monumentos.monumentos.usuario.controlador;

import com.monumentos.monumentos.seguridad.jwt.JwtProvider;
import com.monumentos.monumentos.usuario.modelo.Usuario;
import com.monumentos.monumentos.usuario.servicio.UsuarioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Usuario nuevo = usuarioServicio.nuevoUsuario(newUserRequest);

        String jwt = doLogin(newUserRequest.getUsername(),
                newUserRequest.getPassword());

        return ResponseEntity.status(HttpStatus.CREATED)
                        .body(JwtUserReponse.builder()
                                .id(nuevo.getId())
                                .username(nuevo.getUsername())
                                .token(jwt)
                                .build());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        String jwt = doLogin(loginRequest.getUsername(), loginRequest.getPassword());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(JwtUserReponse.builder()
                        .username(loginRequest.getUsername())
                        .token(jwt)
                        .build());

    }


    private String doLogin(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username, password
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtProvider.generateToken(authentication);
    }


}
