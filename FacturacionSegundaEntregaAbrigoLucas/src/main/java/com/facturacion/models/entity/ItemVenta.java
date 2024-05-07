package com.facturacion.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ItemVenta {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer itemVentaId;
	private Integer productId;
	private int cantidad;
	private String nombre; 
    private String descripcion;
    private double precioVenta;
    private int stock;
    
    
    //@ManyToOne
    //@JoinColumn(name = "producto_id")
    //private Producto producto;
    
    @ManyToOne
    @JoinColumn(name = "venta_id")
    @JsonBackReference
    private Venta venta;

	public ItemVenta() {
		super();
	}


	//Getters y Setters
	
	public Integer getItemVentaId() {
		return itemVentaId;
	}


	public void setItemVentaId(Integer itemVentaId) {
		this.itemVentaId = itemVentaId;
	}

	public Integer getProductId() {
		return productId;
	}


	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
    public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public double getPrecioVenta() {
		return precioVenta;
	}


	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}
}