package com.monumentos.monumentos.repositorio;

import com.monumentos.monumentos.modelo.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepositorio extends JpaRepository <Categoria,Long> {
}
