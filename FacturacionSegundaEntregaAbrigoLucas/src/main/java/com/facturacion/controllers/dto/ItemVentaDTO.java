package com.facturacion.controllers.dto;

public class ItemVentaDTO {
	
	private int cantidad;
    private ProductoDTO producto;
    
	public ItemVentaDTO() {
		super();
	}
	
	public ItemVentaDTO( int cantidad, ProductoDTO producto) {
		super();
		this.cantidad = cantidad;
		this.producto = producto;
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