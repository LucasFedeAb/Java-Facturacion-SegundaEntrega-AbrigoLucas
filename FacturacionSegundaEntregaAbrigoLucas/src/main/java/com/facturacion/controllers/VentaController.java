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
import com.facturacion.models.entity.TimeApi;
import com.facturacion.models.entity.Venta;
import com.facturacion.services.TimeAPIService;
import com.facturacion.services.VentaService;

@CrossOrigin(origins = "http://localhost:5173") //Acceder desde react en local
@RestController
@RequestMapping("/ventas")
public class VentaController {
    @Autowired
    private VentaService ventaService;
    @Autowired
    private TimeAPIService timeAPIService;
    
    @GetMapping("/current-datetime")
    public TimeApi getCurrentDateTime() {
        return timeAPIService.getCurrentDateTime();
    }
    
    @GetMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<Venta>> getAllVentas() {
         try {
 			List<Venta> ventas = ventaService.getAllSales();
 			return new ResponseEntity<>(ventas, HttpStatus.OK); // Codigo 200

 		} catch (Exception e) {
 			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Error 500
 		}
    }
    
    
    
    @GetMapping(value = "/{id}/venta", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity <Venta> getSaleById(@PathVariable("id") Integer id) {
    	try {
    		Venta venta = ventaService.getSaleById(id);
			if (venta != null) {
				return new ResponseEntity<>(venta, HttpStatus.OK); // Codigo 200
			} else {
				return new ResponseEntity<>(venta, HttpStatus.NOT_FOUND); // Codigo 404
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Error 500
		}
    }
    
    @GetMapping(value = "/{dni}/cliente/venta", produces = { MediaType.APPLICATION_JSON_VALUE })
    //@Transactional
    public ResponseEntity <List<Venta>> getSalesByClientDni(@PathVariable("dni") Integer dni) {
    	try {
    		List<Venta> ventas = ventaService.getSalesByClientDni(dni);
			if (ventas != null) {
				return new ResponseEntity<>(ventas, HttpStatus.OK); // Codigo 200
			} else {
				return new ResponseEntity<>(ventas, HttpStatus.NOT_FOUND); // Codigo 404
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Error 500
		}
    }
    
    @PostMapping(value = "/create", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<VentaDTO> createSale(@RequestBody VentaDTO ventaDTO) {
    	try {
            VentaDTO ventaCreada = ventaService.createSale(ventaDTO);
            if(ventaCreada != null) {
            	System.out.println("Venta creada con éxito");
            }
            return new ResponseEntity<>(ventaCreada, HttpStatus.CREATED); // 201 - Created
        } catch (RuntimeException e) {
        	 System.out.println("Error al crear la venta: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 - Bad Request
        }
    }

    @DeleteMapping(value = "/{id}/delete")
	public ResponseEntity<Void> deleteSale(@PathVariable("id") Integer id) {
		boolean deletedSale = ventaService.deleteSaleById(id);
		if (deletedSale) {
			System.out.println("Venta id:" + id + " eliminada con éxito");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Codigo 204
		} else {
			System.out.println("Venta id:" + id + " no encontrada");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Error 404
		}
	}
    
}