package com.facturacion.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.facturacion.models.entity.Venta;
import com.facturacion.services.VentaService;

@RestController
@RequestMapping("/ventas")
public class VentaController {
    @Autowired
    private VentaService ventaService;
    
    @GetMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<Venta>> getAllVentas() {
         try {
 			List<Venta> ventas = ventaService.getAllSales();
 			return new ResponseEntity<>(ventas, HttpStatus.OK); // Codigo 200

 		} catch (Exception e) {
 			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Error 500
 		}
    }

    @PostMapping(value = "/create", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Venta> crearVenta(@RequestBody Venta venta) {
         ventaService.crearVenta(venta);
         return new ResponseEntity<>(venta, HttpStatus.CREATED); // Codigo 201
    }
  
}

