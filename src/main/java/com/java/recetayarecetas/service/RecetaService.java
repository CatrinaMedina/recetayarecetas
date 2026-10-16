package com.java.recetayarecetas.service;

import com.java.recetayarecetas.model.Receta;

import java.util.List;
import java.util.Optional;

public interface RecetaService {

    Receta guardar(Receta receta);

    List<Receta> obtenerTodos();

    Optional<Receta> buscaPorId(Long id);

    boolean eliminar(Long id);

    Optional<Receta> actualizar(Long id, Receta datos);

    List<Receta> buscarPorEstado(String estado);
}