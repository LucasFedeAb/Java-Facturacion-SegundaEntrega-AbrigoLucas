package com.facturacion.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.facturacion.models.entity.Producto;
import com.facturacion.repositories.ProductoRepository;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;
    
    public List<Producto> getAllProducts() {
		return productoRepository.findAll();
	}
	
	public Producto getProductById(Integer id) {
		return productoRepository.findById(id).orElse(null);
	}

    public Producto createProduct(Producto producto) {
        return productoRepository.save(producto);
    }

    
    public Producto updateProductById(Integer id, Producto producto) {
    	try {
			if (productoRepository.existsById(id)) {
				producto.setId(id);
				return productoRepository.save(producto);
			}
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
    	return null;
    }
    
    public Producto updateProduct(Producto producto) {
    	return productoRepository.save(producto);
    }
    
    public boolean deleteProductById(Integer id) {
		try {
			productoRepository.deleteById(id);
			return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}
    
    
    
    // Otros métodos
}

