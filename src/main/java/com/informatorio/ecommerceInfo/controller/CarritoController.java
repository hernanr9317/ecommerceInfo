package com.informatorio.ecommerceInfo.controller;

import com.informatorio.ecommerceInfo.domain.Carrito;
import com.informatorio.ecommerceInfo.domain.LineaDeCarrito;
import com.informatorio.ecommerceInfo.domain.Producto;
import com.informatorio.ecommerceInfo.domain.Usuario;
import com.informatorio.ecommerceInfo.dto.OperacionCarrito;
import com.informatorio.ecommerceInfo.repository.CarritoRepository;
import com.informatorio.ecommerceInfo.repository.ProductoRepository;
import com.informatorio.ecommerceInfo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CarritoController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private ProductoRepository productoRepository;




    @GetMapping(value= "/carrito")
    public ResponseEntity<?> listarCarritos() {
        return new ResponseEntity<>(carritoRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/usuario/{id}/carrito")
    public Carrito agregarCarrito(@PathVariable Long id, @RequestBody Carrito carrito){
        Usuario usuario = usuarioRepository.findById(id).get();
        carrito.setUsuario(usuario);
       return carritoRepository.save(carrito);
    }

    @PutMapping("/usuario/{id}/carrito/{idCarrito}")
    public ResponseEntity<?> agregarProducto(@PathVariable("id") Long userId, @PathVariable("idCarrito") Long idCarrito, @Valid @RequestBody OperacionCarrito operacionCarrito) {
        Carrito carrito = carritoRepository.getById(idCarrito);
        Producto producto = productoRepository.getById(operacionCarrito.getProductoId());
        LineaDeCarrito lineaDeCarrito = new LineaDeCarrito();
        lineaDeCarrito.setCarrito(carrito);
        lineaDeCarrito.setProducto(producto);
        lineaDeCarrito.setCantidad(operacionCarrito.getCantidad());
        lineaDeCarrito.setPrecioUnitario(producto.getPrecioUnitario());
        carrito.setTotal(lineaDeCarrito.calcularSubtotal());
        carrito.agregarLineDeCarrito(lineaDeCarrito);
        return new ResponseEntity<>(carritoRepository.save(carrito), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "carrito/{id}")
    public void borrarCarrito(@PathVariable("id") Long id){
        carritoRepository.deleteById(id);
    }

    @PutMapping(value = "/usuario/{id}/carrito/{idCarrito}/{estado}")
    public Carrito cerrarCarrito(@PathVariable("id") Long userId, @PathVariable("idCarrito") Long idCarrito, @PathVariable Boolean estado){
        Carrito carrito = carritoRepository.getById(idCarrito);
        carrito.setEstado(estado);
        return carritoRepository.save(carrito);
    }

}
