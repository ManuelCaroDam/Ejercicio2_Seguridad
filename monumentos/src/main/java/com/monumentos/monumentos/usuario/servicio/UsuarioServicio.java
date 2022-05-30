package com.monumentos.monumentos.usuario.servicio;

import com.monumentos.monumentos.usuario.controlador.NewUserRequest;
import com.monumentos.monumentos.usuario.modelo.Usuario;
import com.monumentos.monumentos.usuario.repositorio.UsuarioRepositorio;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServicio extends ServicioBase <Usuario, Long, UsuarioRepositorio> {

    public Optional<Usuario> findByUsername(String username) {
        return repositorio.findFirstByUsername(username);
    }

    public Usuario nuevoUsuario(NewUserRequest request) {

        return save(Usuario.builder().build()
                .username(request.getUsername())
    }

}
