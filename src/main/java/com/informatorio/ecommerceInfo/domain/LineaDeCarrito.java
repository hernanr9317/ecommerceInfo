package com.informatorio.ecommerceInfo.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class LineaDeCarrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    Carrito carrito;

    @ManyToOne(fetch = FetchType.LAZY)
    Producto producto;

    private Integer cantidad;

    @Transient
    private BigDecimal subtotal;

    private BigDecimal precioUnitario;

    public  BigDecimal calcularSubtotal(){
        BigDecimal total = this.getProducto().getPrecioUnitario().multiply(new BigDecimal(this.getCantidad()));
        return total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
