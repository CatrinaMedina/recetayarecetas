
package com.java.recetayarecetas.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long medicoId;
    private Long pacienteId;
    private Long sucursalId;
    private String estado;

    @OneToMany(mappedBy = "receta",
               cascade = CascadeType.ALL,
               orphanRemoval = true,
               fetch = FetchType.EAGER)
    private List<DetalleReceta> detalles = new ArrayList<>();

    public Receta() {
    }

    public Receta(Long medicoId, Long pacienteId, Long sucursalId) {
        this.medicoId = medicoId;
        this.pacienteId = pacienteId;
        this.sucursalId = sucursalId;
        this.estado = "PENDIENTE_RESERVA";
    }

    public Long getId() {
        return this.id;
    }

    public Long getMedicoId() {
        return this.medicoId;
    }

    public Long getPacienteId() {
        return this.pacienteId;
    }

    public Long getSucursalId() {
        return this.sucursalId;
    }

    public String getEstado() {
        return this.estado;
    }

    public List<DetalleReceta> getDetalles() {
        return this.detalles;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMedicoId(Long medicoId) {
        this.medicoId = medicoId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public void setSucursalId(Long sucursalId) {
        this.sucursalId = sucursalId;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setDetalles(List<DetalleReceta> detalles) {
        this.detalles.clear();

        if (detalles != null) {
            for (DetalleReceta detalle : detalles) {
                detalle.setReceta(this);
                this.detalles.add(detalle);
            }
        }
    }
}
