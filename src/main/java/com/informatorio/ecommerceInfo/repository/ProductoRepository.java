
package com.informatorio.ecommerceInfo.repository;


import com.informatorio.ecommerceInfo.domain.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long> {

    List<Producto> findByNombreContaining(String nombre);

    List<Producto> findByPublicado(Boolean publicado);

}