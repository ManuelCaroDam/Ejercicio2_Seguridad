package com.monumentos.monumentos.usuario.servicio;

import com.monumentos.monumentos.usuario.controlador.NewUserRequest;

import com.monumentos.monumentos.usuario.modelo.Usuario;
import com.monumentos.monumentos.usuario.repositorio.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServicio extends ServicioBase <Usuario, Long, UsuarioRepositorio> {

    private final PasswordEncoder passwordEncoder;

    public Optional<Usuario> findByUsername(String username) {
        return repositorio.findFirstByUsername(username);
    }

    public Usuario nuevoUsuario(NewUserRequest request) {

        return save(Usuario.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build());
    }
}
