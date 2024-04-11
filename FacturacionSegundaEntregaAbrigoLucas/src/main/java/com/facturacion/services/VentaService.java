package com.facturacion.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facturacion.models.entity.ItemVenta;
import com.facturacion.models.entity.Producto;
import com.facturacion.models.entity.Venta;
import com.facturacion.repositories.VentaRepository;

@Service
public class VentaService {
    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductoService productoService;

    public Venta crearVenta(Venta venta) {
        // Actualizar el stock de los productos vendidos
        for (ItemVenta item : venta.getItems()) {
            Producto producto = item.getProducto();
            producto.setStock(producto.getStock() - item.getCantidad());
            productoService.updateProduct(producto);
            //productoService.actualizarProducto(producto);
        }
        return ventaRepository.save(venta);
    }
    
    public List<Venta> getAllSales (){
    	return ventaRepository.findAll();
    }

}

