package com.monumentos.monumentos.repositorio;

import com.monumentos.monumentos.modelo.Categoria;
import com.monumentos.monumentos.modelo.Monumento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MonumentoRepositorio extends JpaRepository <Monumento, Long> {

    List<Monumento> findByNombreContainsIgnoreCase(String nombre);
    List<Monumento> findByPaisContainsIgnoreCase(String pais);
    List<Monumento> findByCategoria(Categoria categoria);

}
