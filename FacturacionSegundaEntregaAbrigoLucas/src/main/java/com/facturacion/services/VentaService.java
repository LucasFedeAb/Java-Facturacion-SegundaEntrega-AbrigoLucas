package com.facturacion.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.facturacion.controllers.dto.ClienteDTO;
import com.facturacion.controllers.dto.ItemVentaDTO;
import com.facturacion.controllers.dto.ProductoDTO;
import com.facturacion.controllers.dto.VentaDTO;
import com.facturacion.models.entity.Cliente;
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
    
    //@Autowired
    //private ClienteService clienteService;
    
    public VentaDTO crearVenta(VentaDTO ventaDTO) {
        Venta venta = new Venta();
        venta.setFechaVenta(new Date());
        double montoTotalVenta = 0.0;
        
        // Obtener cliente
        ClienteDTO clienteDTO = ventaDTO.getCliente();
        Cliente cliente = new Cliente(clienteDTO.getDni(), clienteDTO.getNombre(), clienteDTO.getApellido(), clienteDTO.getEmail(), clienteDTO.getTelefono(), clienteDTO.getDireccion());
        venta.setCliente(cliente);
        
        List<ItemVentaDTO> itemsDTO = ventaDTO.getItems();
        for (ItemVentaDTO itemDTO : itemsDTO) {
            // Obtener producto
            ProductoDTO productoDTO = productoService.getProductDTOById(itemDTO.getProducto().getId());
            if (productoDTO == null) {
                throw new RuntimeException("El producto con ID " + itemDTO.getProducto().getId() + " no existe.");
            }
            
            // Actualizar stock del producto
            Producto producto = new Producto(productoDTO.getNombre(), productoDTO.getDescripcion(), productoDTO.getPrecio(), productoDTO.getStock() - itemDTO.getCantidad());
            producto.setId(productoDTO.getId()); // Asignar el ID del producto existente
            productoService.updateProduct(producto);
            
            // Calcular monto total de la venta
            montoTotalVenta += productoDTO.getPrecio() * itemDTO.getCantidad();
            
            // Crear ItemVenta
            ItemVenta itemVenta = new ItemVenta();
            itemVenta.setProducto(producto);
            itemVenta.setCantidad(itemDTO.getCantidad());
            
            // Asociar ItemVenta con Venta
            itemVenta.setVenta(venta);
            
            venta.getItems().add(itemVenta);
        }
        
        venta.setMontoTotalVenta(montoTotalVenta);
        
        // Guardar la venta en la base de datos
        venta = ventaRepository.save(venta);
        
        // Actualizar el ID de venta en el DTO
        ventaDTO.setId(venta.getId());
        // Actualizar la fecha de venta en el DTO
        ventaDTO.setFechaVenta(venta.getFechaVenta());
        // Actualizar el monto total de la venta en el DTO
        ventaDTO.setMontoTotalVenta(venta.getMontoTotalVenta());

        return ventaDTO;
    }

    public List<VentaDTO> getAllSalesWithOriginalPrices() {
        List<Venta> ventas = ventaRepository.findAll();
        List<VentaDTO> ventasDTO = new ArrayList<>();

        for (Venta venta : ventas) {
            VentaDTO ventaDTO = new VentaDTO();

            List<ItemVenta> itemsVenta = venta.getItems();
            List<ItemVentaDTO> itemsVentaDTO = new ArrayList<>();
            for (ItemVenta itemVenta : itemsVenta) {
                // Obtener el precio original del producto en el momento de la venta
                ProductoDTO productoDTO = productoService.getProductDTOById(itemVenta.getProducto().getId());
                double precioOriginal = productoDTO.getPrecio();

                ItemVentaDTO itemVentaDTO = new ItemVentaDTO();
            
                itemsVentaDTO.add(itemVentaDTO);
            }
            ventaDTO.setItems(itemsVentaDTO);
            ventasDTO.add(ventaDTO);
        }

        return ventasDTO;
    }
    
    
 // Obtener ventas por ID de cliente
    public List<Venta> getAllSales (){
    	return ventaRepository.findAll();
    }
    
    // Obtener ventas por ID de cliente
    public List<Venta> getVentasByClienteId(Integer clienteId) {
        // Implementa la lógica para obtener las ventas de un cliente específico desde el repositorio
        return ventaRepository.findByClienteId(clienteId);
    }

    // Eliminar venta
    public boolean deleteSaleById(Integer id) {
		try {
			ventaRepository.deleteById(id);
			return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}
    
}
