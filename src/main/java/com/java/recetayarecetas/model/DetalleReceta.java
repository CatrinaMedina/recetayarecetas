
package com.java.recetayarecetas.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class DetalleReceta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long medicamentoId;
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "receta_id")
    @JsonIgnore
    private Receta receta;

    public DetalleReceta() {
    }

    public DetalleReceta(Long medicamentoId, Integer cantidad) {
        this.medicamentoId = medicamentoId;
        this.cantidad = cantidad;
    }

    public Long getId() {
        return this.id;
    }

    public Long getMedicamentoId() {
        return this.medicamentoId;
    }

    public Integer getCantidad() {
        return this.cantidad;
    }

    public Receta getReceta() {
        return this.receta;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMedicamentoId(Long medicamentoId) {
        this.medicamentoId = medicamentoId;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }
}
