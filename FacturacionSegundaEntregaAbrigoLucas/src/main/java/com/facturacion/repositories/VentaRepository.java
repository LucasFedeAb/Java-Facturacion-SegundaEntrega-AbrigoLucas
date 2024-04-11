package com.facturacion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.facturacion.models.entity.Venta;

@Repository
public interface VentaRepository extends JpaRepository <Venta, Integer> {

}
