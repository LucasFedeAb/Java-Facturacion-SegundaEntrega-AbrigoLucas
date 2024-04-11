package com.facturacion.models.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table( name = "ventas")
public class Venta {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
    private Date fechaVenta;

    private double montoTotalVenta;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "venta")
    private List<ItemVenta> items = new ArrayList<>();

    public Venta() {
    }

    //Getters y Setters
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Date getFechaVenta() {
		return fechaVenta;
	}


	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}


	public double getMontoTotalVenta() {
		return montoTotalVenta;
	}


	public void setMontoTotalVenta(double montoTotalVenta) {
		this.montoTotalVenta = montoTotalVenta;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public List<ItemVenta> getItems() {
		return items;
	}


	public void setItems(List<ItemVenta> items) {
		this.items = items;
	}


	@PrePersist
    public void prePersist() {
        this.fechaVenta = new Date();
    }

}
