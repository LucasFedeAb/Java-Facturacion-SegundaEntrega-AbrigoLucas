package com.facturacion.controllers.dto;

public class ItemVentaDTO {
	private Integer id;
    private int cantidad;
    private ProductoDTO producto;
    
	public ItemVentaDTO() {
		super();
	}
	
	public ItemVentaDTO(Integer id, int cantidad, ProductoDTO producto) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.producto = producto;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public ProductoDTO getProducto() {
		return producto;
	}
	public void setProducto(ProductoDTO producto) {
		this.producto = producto;
	}
    
}
