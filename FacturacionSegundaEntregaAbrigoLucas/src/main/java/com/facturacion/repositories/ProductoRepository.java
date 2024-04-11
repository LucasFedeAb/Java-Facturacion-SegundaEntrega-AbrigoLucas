package com.facturacion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.facturacion.models.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
