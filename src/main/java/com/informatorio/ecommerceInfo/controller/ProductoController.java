package com.informatorio.ecommerceInfo.controller;

import com.informatorio.ecommerceInfo.domain.Producto;
import com.informatorio.ecommerceInfo.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @PostMapping(value = "/producto")
    public Producto createProducto(@RequestBody Producto producto)
    {return productoRepository.save(producto);}

    @GetMapping(value = "/producto/{id}")
    public Producto getProductoPorId(@PathVariable("id") Long id){
        return productoRepository.findById(id).get();
    }

    @DeleteMapping(value = "producto/{id}")
    public void borrarProductoPorId(@PathVariable("id") Long id){
        productoRepository.deleteById(id);
    }

    @PutMapping(value = "/producto/{id}")
    public Producto modificarProducto(@PathVariable("id")Long id, @RequestBody Producto producto) {
        Producto productoExistente = productoRepository.findById(id).get();
        if(producto.getNombre()!=null) productoExistente.setNombre(producto.getNombre());
        if(producto.getDescripcion()!=null) productoExistente.setDescripcion(producto.getDescripcion());
        if(producto.getPrecioUnitario()!=null) productoExistente.setPrecioUnitario(producto.getPrecioUnitario());
        if(producto.getContenido()!=null) productoExistente.setContenido(producto.getContenido());
        if(producto.getPublicado()!=null) productoExistente.setPublicado(producto.getPublicado());
        return productoRepository.save(productoExistente);
    }

    @GetMapping(value= "/producto")
    public ResponseEntity<?> buscarProducto(
            @RequestParam(name = "nombre", required = false) String nombre,
            @RequestParam(name = "publicado", required = false) Boolean publicado)
    {
        if (Objects.nonNull(nombre)) {
            return new ResponseEntity<>(productoRepository.findByNombreContaining(nombre), HttpStatus.OK);
        }else if(Objects.nonNull(publicado)){
            return new ResponseEntity<>(productoRepository.findByPublicado(publicado), HttpStatus.OK);
        }
        return new ResponseEntity<>(productoRepository.findAll(), HttpStatus.OK);
    }

}
