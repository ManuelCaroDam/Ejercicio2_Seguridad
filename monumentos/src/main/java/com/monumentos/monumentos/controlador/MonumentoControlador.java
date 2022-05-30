package com.monumentos.monumentos.controlador;

import com.monumentos.monumentos.modelo.Monumento;
import com.monumentos.monumentos.repositorio.MonumentoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/monumento")
@RequiredArgsConstructor

public class MonumentoControlador {

    private final MonumentoRepositorio repositorio;

    @GetMapping("/")

    public ResponseEntity<List<Monumento>> todosLosMonumentos() {

        List<Monumento> result = repositorio.findAll();

        if (result.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Monumento> monumentoPorId(@PathVariable Long id) {
        return ResponseEntity.of(repositorio.findById(id));
    }

    @GetMapping("/nombre/{nom}")
    public ResponseEntity<List<Monumento>> MonumentoPorNombre(@PathVariable("nom") String nombre) {
        List<Monumento> result =
                repositorio.findByNombreContainsIgnoreCase(nombre);

        if (result.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(result);

    }

    @GetMapping("/nombre/{nomPais}")
    public ResponseEntity<List<Monumento>> MonumentoPorPais(@PathVariable("nom") String pais) {
        List<Monumento> result =
                repositorio.findByPaisContainsIgnoreCase(pais);

        if (result.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(result);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Monumento> editarMonumento(
            @PathVariable Long id, @RequestBody Monumento aEditar
    ) {

        return repositorio.findById(id)
                .map(p -> {
                    p.setCodigoPais(aEditar.getCodigoPais());
                    p.setPais(aEditar.getPais());
                    p.setCiudad(aEditar.getCiudad());
                    p.setLocalizacion(aEditar.getLocalizacion());
                    p.setNombre(aEditar.getNombre());
                    p.setDescripcion(aEditar.getDescripcion());
                    p.setUrl(aEditar.getUrl());
                    p.setCategoria(aEditar.getCategoria());


                    return repositorio.save(p);
                })
                .map(p -> ResponseEntity.ok(p))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")

    public ResponseEntity<Monumento> crearMonumento(@RequestBody Monumento nuevo) {
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
