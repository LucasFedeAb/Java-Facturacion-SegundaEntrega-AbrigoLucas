package com.facturacion.services;

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
import com.facturacion.models.entity.TimeApi;
import com.facturacion.models.entity.Venta;
import com.facturacion.repositories.ProductoRepository;
import com.facturacion.repositories.VentaRepository;

@Service
public class VentaService {
    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private TimeAPIService timeAPIService;


    // Metodo para crear venta
    public VentaDTO createSale(VentaDTO ventaDTO) {
        Venta venta = new Venta();
        
        // Crar fecha Venta
        TimeApi timeApi = timeAPIService.getCurrentDateTime();
        venta.setFechaVenta(timeApi.getDatetime());
        venta.setFechaVenta(timeAPIService.formatterDateTime(venta.getFechaVenta()));
        
        double montoTotalVenta = 0.0;
        
        // Obtener cliente
        ClienteDTO clienteDTO = ventaDTO.getCliente();
        Cliente cliente = new Cliente(clienteDTO.getDni(), clienteDTO.getNombre(), clienteDTO.getApellido(), clienteDTO.getEmail(), clienteDTO.getTelefono(), clienteDTO.getDireccion());
        venta.setCliente(cliente);
        ventaDTO.setCliente(clienteDTO);
        
        List<ItemVentaDTO> itemsDTO = ventaDTO.getItems();
        for (ItemVentaDTO itemDTO : itemsDTO) {
            // Obtener producto
            ProductoDTO productoDTO = productoService.getProductDTOById(itemDTO.getProducto().getId());
            if (productoDTO == null) {
                throw new RuntimeException("El producto con ID " + itemDTO.getProducto().getId() + " no existe");
            }
            
            // Verificar cantidad y stock disponible
            if (itemDTO.getCantidad() > productoDTO.getStock()) {
                throw new RuntimeException("La cantidad de producto solicitada es mayor que el stock disponible");
            }
            
            // Actualizar stock del producto
            updateStock(productoDTO.getId(), itemDTO.getCantidad());
            // Calcular monto total de la venta
            montoTotalVenta += productoDTO.getPrecio() * itemDTO.getCantidad();
            
            if (montoTotalVenta <= 0) {
                throw new RuntimeException("El monto total de la venta no es válido");
            }
            
            // Crear ItemVenta y almacenar los datos relevantes del producto en el momento de la venta
            ItemVenta itemVenta = new ItemVenta();
            itemVenta.setProductId(productoDTO.getId());
            itemVenta.setCantidad(itemDTO.getCantidad());
            itemVenta.setNombre(productoDTO.getNombre());
            itemVenta.setDescripcion(productoDTO.getDescripcion());
            itemVenta.setPrecioVenta(productoDTO.getPrecio());
            itemVenta.setStock(productoDTO.getStock());       
            
            // Asociar ItemVenta con Venta
            itemVenta.setVenta(venta);
            venta.getItems().add(itemVenta);
        }
        
        //Obtener monto total de venta
        venta.setMontoTotalVenta(montoTotalVenta);
        
        // Guardar la venta en la base de datos
        venta = ventaRepository.save(venta);
        
        // Actualizar el ID de venta en el DTO
        ventaDTO.setId(venta.getId());
        ventaDTO.setFechaVenta(venta.getFechaVenta());
        ventaDTO.setMontoTotalVenta(venta.getMontoTotalVenta());
        
        return ventaDTO;
    }
    
    
    
    public void updateStock(Integer productoId, int cantidad) {
        Producto producto = productoRepository.findById(productoId).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        producto.setStock(producto.getStock() - cantidad);
        productoRepository.save(producto);
    }
    
    
    // Obtener todas las ventas
    public List<Venta> getAllSales (){
    	return ventaRepository.findAll();
    }
    
    // Obtener lista de ventas por DNI de cliente
    public List<Venta> getSalesByClientDni(Integer clienteDni) {
        return ventaRepository.findByClienteDni(clienteDni);
    }
        
    // Obtener venta por id
    public Venta getSaleById(Integer id) {
			return ventaRepository.findById(id).orElse(null);
	}

    // Eliminar venta por id
    public boolean deleteSaleById(Integer id) {
		try {
			ventaRepository.deleteById(id);
			return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}
    

}
