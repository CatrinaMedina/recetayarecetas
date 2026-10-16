
package com.java.recetayarecetas.service;

import com.java.recetayarecetas.model.Receta;
import com.java.recetayarecetas.model.DetalleReceta;
import com.java.recetayarecetas.repository.RecetaRepository;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecetaServiceImpl implements RecetaService {

    private final RecetaRepository repository;

    public RecetaServiceImpl(RecetaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Receta guardar(Receta receta) {

        validarReceta(receta, true);

        receta.setId(null);
        receta.setEstado("PENDIENTE_RESERVA");

        for (DetalleReceta detalle : receta.getDetalles()) {
            detalle.setId(null);
            detalle.setReceta(receta);
        }

        return repository.save(receta);
    }

    @Override
    public List<Receta> obtenerTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<Receta> buscaPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public boolean eliminar(Long id) {

        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public Optional<Receta> actualizar(Long id, Receta datos) {

        validarReceta(datos, true);

        return repository.findById(id)
            .map(receta -> {

                receta.setMedicoId(datos.getMedicoId());
                receta.setPacienteId(datos.getPacienteId());
                receta.setSucursalId(datos.getSucursalId());

                if (datos.getDetalles() != null) {

                    List<DetalleReceta> nuevosDetalles = new ArrayList<>();

                    for (DetalleReceta detalle : datos.getDetalles()) {

                        DetalleReceta nuevo = new DetalleReceta();
                        nuevo.setMedicamentoId(detalle.getMedicamentoId());
                        nuevo.setCantidad(detalle.getCantidad());
                        nuevosDetalles.add(nuevo);
                    }

                    receta.setDetalles(nuevosDetalles);
                }

                return repository.save(receta);
            });
    }

    @Override
    public List<Receta> buscarPorEstado(String estado) {
        return repository.findByEstado(estado);
    }

    private void validarReceta(Receta receta, boolean exigirDetalles) {

        if (receta == null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Los datos de la receta son obligatorios");
        }

        if (receta.getMedicoId() == null
                || receta.getMedicoId() <= 0
                || receta.getPacienteId() == null
                || receta.getPacienteId() <= 0
                || receta.getSucursalId() == null
                || receta.getSucursalId() <= 0) {

            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Medico, paciente y sucursal deben tener IDs validos");
        }

        List<DetalleReceta> detalles = receta.getDetalles();

        if (exigirDetalles && (detalles == null || detalles.isEmpty())) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "La receta debe contener medicamentos");
        }

        if (detalles != null) {

            if (detalles.isEmpty()) {
                throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La lista de medicamentos no puede estar vacia");
            }

            for (DetalleReceta detalle : detalles) {

                if (detalle == null
                        || detalle.getMedicamentoId() == null
                        || detalle.getMedicamentoId() <= 0
                        || detalle.getCantidad() == null
                        || detalle.getCantidad() <= 0) {

                    throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Medicamento o cantidad invalida");
                }
            }
        }
    }
}
