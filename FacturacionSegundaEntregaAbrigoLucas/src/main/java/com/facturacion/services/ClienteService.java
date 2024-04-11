package com.facturacion.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.facturacion.models.entity.Cliente;
import com.facturacion.repositories.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    
    public List<Cliente> getAllClients() {
		return clienteRepository.findAll();
	}
	
	public Cliente getClientByDNI(Integer dni) {
		return clienteRepository.findById(dni).orElse(null);
	}

    public Cliente createClient(Cliente cliente) {
    	cliente.setId(cliente.getDni());
        return clienteRepository.save(cliente);
    }

    public Cliente updateClientByDni(Integer dni, Cliente cliente) {
    	try {
			if (clienteRepository.existsById(dni)) {
				cliente.setDni(dni);
				cliente.setId(cliente.getDni());
				return clienteRepository.save(cliente);
			}
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
    	return null;
    }
    
	
	public boolean deleteClientByDNI(Integer dni) {
		try {
			clienteRepository.deleteById(dni);
			return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}

    // Otros métodos
}
