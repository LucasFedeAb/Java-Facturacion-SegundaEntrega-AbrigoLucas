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

import com.facturacion.models.entity.Cliente;
import com.facturacion.services.ClienteService;

@CrossOrigin(origins = "http://localhost:5173") //Acceder desde react en local
@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;


    @PostMapping(value = "/create", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Cliente> createClient(@RequestBody Cliente cliente) {
		clienteService.createClient(cliente);
		return new ResponseEntity<>(cliente, HttpStatus.CREATED); // Codigo 201
	}
    
    @PutMapping(value="/{id}/update", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Cliente> updateCliente(@PathVariable("id") Integer dni, @RequestBody Cliente cliente) {
    	Cliente clienteUpdated = clienteService.updateClientByDni(dni, cliente);
    	
			if (clienteUpdated != null) {
				return new ResponseEntity<>(cliente, HttpStatus.OK); // Codigo 200
			} else {
				return new ResponseEntity<>(cliente, HttpStatus.NOT_FOUND); // Codigo 404
			}
    }
    
    
    @GetMapping (value = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Cliente>> listarClientes() {
		try {
			List<Cliente> clientes = clienteService.getAllClients();
			return new ResponseEntity<>(clientes, HttpStatus.OK); // Codigo 200

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Error 500
		}
	}
    
    @GetMapping(value = "/cliente/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Cliente> getClientByDNI(@PathVariable("id") Integer dni) {
		try {
			Cliente cliente = clienteService.getClientByDNI(dni);
			if (cliente != null) {
				return new ResponseEntity<>(cliente, HttpStatus.OK); // Codigo 200
			} else {
				return new ResponseEntity<>(cliente, HttpStatus.NOT_FOUND); // Codigo 404
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Error 500
		}

	}
    
    @DeleteMapping(value = "/{id}/delete")
	public ResponseEntity<Void> deleteClient(@PathVariable("id") Integer dni) {
		boolean deletedClient = clienteService.deleteClientByDNI(dni);
		if (deletedClient) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Codigo 204
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Error 404
		}
	}
    
}