package com.informatorio.ecommerceInfo.controller;

import com.informatorio.ecommerceInfo.domain.Usuario;
import com.informatorio.ecommerceInfo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping(value = "/usuario")
    public Usuario createUsuario(@RequestBody Usuario usuario)
        {return usuarioRepository.save(usuario);}

    @GetMapping(value = "/usuario/{id}")
    public Usuario getUsuarioPorId(@PathVariable("id") Long id){
        return usuarioRepository.findById(id).get();
    }

    @DeleteMapping(value = "usuario/{id}")
    public void borrarUsuarioPorId(@PathVariable("id") Long id){
        usuarioRepository.deleteById(id);
    }

    @PutMapping(value = "/usuario/{id}")
    public Usuario modificarUsuario(@PathVariable("id")Long id, @RequestBody Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findById(id).get();
        if(usuario.getNombre()!=null) usuarioExistente.setNombre(usuario.getNombre());
        if(usuario.getApellido()!=null) usuarioExistente.setApellido(usuario.getApellido());
        if(usuario.getEmail()!=null) usuarioExistente.setEmail(usuario.getEmail());
        if(usuario.getPassword()!=null) usuarioExistente.setPassword(usuario.getPassword());
        if(usuario.getCiudad()!=null) usuarioExistente.setCiudad(usuario.getCiudad());
        if(usuario.getProvincia()!=null) usuarioExistente.setProvincia(usuario.getProvincia());
        if(usuario.getPais()!=null) usuarioExistente.setPais(usuario.getPais());
        return usuarioRepository.save(usuarioExistente);
    }

    @GetMapping(value= "/usuario")
    public ResponseEntity<?> buscarPorCiudad(
            @RequestParam(name = "fechaDeCreacion", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDeCreacion,
            @RequestParam(name = "ciudad", required = false) String ciudad)
    {
        if (fechaDeCreacion != null) {
            return new ResponseEntity<>(usuarioRepository.findByFechaDeCreacionAfter(fechaDeCreacion.atStartOfDay()), HttpStatus.OK);
        } else if (Objects.nonNull(ciudad)) {
            return new ResponseEntity<>(usuarioRepository.findByCiudad(ciudad), HttpStatus.OK);
        }
        return new ResponseEntity<>(usuarioRepository.findAll(), HttpStatus.OK);
    }

}





