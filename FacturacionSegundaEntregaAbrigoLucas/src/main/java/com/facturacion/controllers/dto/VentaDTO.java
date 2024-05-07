package com.facturacion.controllers.dto;

import java.util.Date;
import java.util.List;

public class VentaDTO {
	
	private Integer id;
	private String fechaVenta;
    private double montoTotalVenta;
    private ClienteDTO cliente;
    private List<ItemVentaDTO> items;
    
    
	public VentaDTO() {
		super();
	}
	
	 public VentaDTO(Integer id, String fechaVenta, double montoTotalVenta, ClienteDTO cliente, List<ItemVentaDTO> items) {
	        this.id = id;
	        this.fechaVenta = fechaVenta;
	        this.montoTotalVenta = montoTotalVenta;
	        this.cliente = cliente;
	        this.items = items;
	 }
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public String getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(String fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}

	public double getMontoTotalVenta() {
		return montoTotalVenta;
	}
	public void setMontoTotalVenta(double montoTotalVenta) {
		this.montoTotalVenta = montoTotalVenta;
	}
	public List<ItemVentaDTO> getItems() {
		return items;
	}
	public void setItems(List<ItemVentaDTO> items) {
		this.items = items;
	}

}