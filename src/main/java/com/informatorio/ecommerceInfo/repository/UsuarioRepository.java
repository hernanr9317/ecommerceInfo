package com.informatorio.ecommerceInfo.repository;

import com.informatorio.ecommerceInfo.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findByCiudad(String ciudad);

    List<Usuario> findByFechaDeCreacionAfter(LocalDateTime dateTime);


}
