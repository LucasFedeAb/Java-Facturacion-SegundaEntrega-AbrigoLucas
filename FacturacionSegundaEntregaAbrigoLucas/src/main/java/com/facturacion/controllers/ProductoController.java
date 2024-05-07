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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.facturacion.models.entity.Producto;
import com.facturacion.services.ProductoService;

@CrossOrigin(origins = "http://localhost:5173") //Acceder desde react en local
@RestController
@RequestMapping("/products")
public class ProductoController {
    @Autowired
    private ProductoService productoService;
    
    @PostMapping(value = "/create", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Producto> createProduct(@RequestBody Producto producto) {
    	productoService.createProduct(producto);
		return new ResponseEntity<>(producto, HttpStatus.CREATED); // Codigo 201
	}
    
    @PutMapping(value = "/{id}/update", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Producto> updateProduct(@PathVariable("id") Integer id, @RequestBody Producto producto) {
    	Producto productUpdated = productoService.updateProductById(id, producto);
		if (productUpdated != null) {
			return new ResponseEntity<>(productUpdated, HttpStatus.OK); // Codigo 200
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Error 404
		}
	}
    
    @GetMapping (value = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Producto>> getAllProducts() {
		try {
			List<Producto> products = productoService.getAllProducts();
			return new ResponseEntity<>(products, HttpStatus.OK); // Codigo 200

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Error 500
		}

	}
    
    @GetMapping(value = "/product/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Producto> getProductById(@PathVariable("id") Integer id) {
		try {
			Producto product = productoService.getProductById(id);
			if (product != null) {
				return new ResponseEntity<>(product, HttpStatus.OK); // Codigo 200
			} else {
				return new ResponseEntity<>(product, HttpStatus.NOT_FOUND); // Codigo 404
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Error 500
		}

	}

    
    @DeleteMapping(value = "/{id}/delete")
	public ResponseEntity<Void> deleteProduct(@PathVariable("id") Integer id) {
		boolean deletedProduct = productoService.deleteProductById(id);
		if (deletedProduct) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Codigo 204
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Error 404
		}
	}
}