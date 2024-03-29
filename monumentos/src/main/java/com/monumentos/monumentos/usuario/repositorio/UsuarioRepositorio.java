package com.monumentos.monumentos.usuario.repositorio;

import com.monumentos.monumentos.usuario.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepositorio extends JpaRepository <Usuario,Long> {

    Optional<Usuario> findFirstByUsername(String username);
}
