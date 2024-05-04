package com.facturacion.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.facturacion.controllers.dto.VentaDTO;
import com.facturacion.models.entity.Venta;
import com.facturacion.services.VentaService;

import jakarta.transaction.Transactional;

@CrossOrigin(origins = "http://localhost:5173") //Acceder desde react en local
@RestController
@RequestMapping("/ventas")
public class VentaController {
    @Autowired
    private VentaService ventaService;
    
    
    
    @GetMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Transactional
    public ResponseEntity<List<VentaDTO>> getAllSalesWithOriginalPrices() {
         try {
             List<VentaDTO> ventas = ventaService.getAllSalesWithOriginalPrices();
             return new ResponseEntity<>(ventas, HttpStatus.OK); // Codigo 200
         } catch (Exception e) {
             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Error 500
         }
    }
    
    @GetMapping(value = "/all", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Transactional
    public ResponseEntity<List<Venta>> getAllVentas() {
         try {
 			List<Venta> ventas = ventaService.getAllSales();
 			return new ResponseEntity<>(ventas, HttpStatus.OK); // Codigo 200

 		} catch (Exception e) {
 			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Error 500
 		}
    }
    
    
    @PostMapping(value = "/create", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<VentaDTO> crearVenta(@RequestBody VentaDTO ventaDTO) {
         ventaService.crearVenta(ventaDTO);
         return new ResponseEntity<>(ventaDTO, HttpStatus.CREATED); // Codigo 201
    }
    
    @DeleteMapping(value = "/{id}/delete")
	public ResponseEntity<Void> deleteSale(@PathVariable("id") Integer id) {
		boolean deletedSale = ventaService.deleteSaleById(id);
		if (deletedSale) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Codigo 204
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Error 404
		}
	}
}

