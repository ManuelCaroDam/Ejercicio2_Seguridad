package com.monumentos.monumentos.controlador;

import com.monumentos.monumentos.modelo.Categoria;
import com.monumentos.monumentos.modelo.Monumento;
import com.monumentos.monumentos.repositorio.CategoriaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoriaControlador {

    private final CategoriaRepositorio repositorio;

    @GetMapping("/")

    public ResponseEntity<List<Categoria>> todosLasCategorias() {

        List<Categoria> result = repositorio.findAll();

        if (result.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(result);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Categoria> categoriaPorId(@PathVariable Long id) {
        return ResponseEntity.of(repositorio.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> editarCategoria(
            @PathVariable Long id, @RequestBody Categoria aEditar
    ) {

        return repositorio.findById(id)
                .map(p -> {

                    p.setNombre(aEditar.getNombre());
                    p.setUrl(aEditar.getUrl());



                    return repositorio.save(p);
                })
                .map(p -> ResponseEntity.ok(p))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")

    public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria nuevo) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(repositorio.save(nuevo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity borrar(@PathVariable Long id) {
        if (repositorio.existsById(id))
            repositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
