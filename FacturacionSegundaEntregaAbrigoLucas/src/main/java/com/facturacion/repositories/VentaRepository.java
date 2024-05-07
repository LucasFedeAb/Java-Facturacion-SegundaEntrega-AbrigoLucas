package com.facturacion.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.facturacion.models.entity.Venta;

@Repository
public interface VentaRepository extends JpaRepository <Venta, Integer> {
	List<Venta> findByClienteId(Integer clienteId);
	List<Venta> findByClienteDni(Integer clienteId);
}
