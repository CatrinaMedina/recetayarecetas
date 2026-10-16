
package com.java.recetayarecetas.controller;

import com.java.recetayarecetas.model.Receta;
import com.java.recetayarecetas.service.RecetaService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recetas")
public class RecetaController {

    private final RecetaService recetaService;

    public RecetaController(RecetaService recetaService) {
        this.recetaService = recetaService;
    }

    @PostMapping
    public ResponseEntity<Receta> guardar(@RequestBody Receta receta) {

        Receta nuevaReceta = recetaService.guardar(receta);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaReceta);
    }

    @GetMapping
    public List<Receta> obtenerTodos() {
        return recetaService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receta> buscaPorId(@PathVariable Long id) {

        return recetaService.buscaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receta> actualizar(
            @PathVariable Long id,
            @RequestBody Receta receta) {

        return recetaService.actualizar(id, receta)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        if (recetaService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/estado/{estado}")
    public List<Receta> buscarPorEstado(@PathVariable String estado) {
        return recetaService.buscarPorEstado(estado);
    }
}
