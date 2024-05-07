package com.facturacion.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.facturacion.controllers.dto.ProductoDTO;
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
	
	public ProductoDTO getProductDTOById(Integer id) {
	    Producto producto = productoRepository.findById(id).orElse(null);
	    if (producto != null) {
	        return new ProductoDTO(producto);
	    } else {
	        return null;
	    }
	}

    public Producto createProduct(Producto producto) {
    	if(producto.getPrecio() <= 0) {
    		throw new RuntimeException("El precio debe ser mayor a 0");
    	} else if (producto.getStock() <= 0) {
    		throw new RuntimeException("El stock debe ser mayor a 0");
    	}
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
    
}

