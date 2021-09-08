package com.informatorio.ecommerceInfo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String generado;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @CreationTimestamp
    private LocalDateTime fechaDeCreacion;

    @Transient
    private BigDecimal total;

    private Boolean estado;

    @JsonIgnore
    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineaDeCarrito> lineasDeCarrito = new ArrayList<>();


    public void agregarLineDeCarrito(LineaDeCarrito lineaDeCarrito) {
        lineasDeCarrito.add(lineaDeCarrito);
        lineaDeCarrito.setCarrito(this);
    }

    public void removerLineDeCarrito(LineaDeCarrito lineaDeCarrito) {
        lineasDeCarrito.remove(lineaDeCarrito);
        lineaDeCarrito.setCarrito(null);
    }

    public void calcularTotal(){
        List <LineaDeCarrito> lineas = this.getLineasDeCarrito();
        this.setTotal(BigDecimal.ZERO);
        for (LineaDeCarrito linea: lineas){
            this.setTotal(this.getTotal().add(linea.calcularSubtotal()));
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenerado() {
        return generado;
    }

    public void setGenerado(String generado) {
        this.generado = generado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(LocalDateTime fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public List<LineaDeCarrito> getLineasDeCarrito() {
        return lineasDeCarrito;
    }

    public void setLineasDeCarrito(List<LineaDeCarrito> lineasDeCarrito) {
        this.lineasDeCarrito = lineasDeCarrito;
    }
}
