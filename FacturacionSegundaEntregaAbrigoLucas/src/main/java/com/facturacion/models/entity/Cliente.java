package com.facturacion.models.entity;

import java.util.ArrayList;
import java.util.List;

import com.facturacion.models.abstractClass.Persona;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "clientes")
public class Cliente extends Persona {
		
		
		@Id
		@Column(unique = true)
		private Integer id;
	  	@Column(name = "Telefono")
	  	private Integer telefono;
	  	@Column(name = "direccion")
		private String direccion;
	  	
	  	@OneToMany(mappedBy = "cliente")
	    private List<Venta> ventas = new ArrayList<>();
	  	
	  	
		// Constructor
	  	
	  	public Cliente () {
	    	super();
	    }
	  	

		public Cliente (Integer dni, String nombre, String apellido, String email, Integer telefono, String direccion) {
	    	super(dni, nombre, apellido, email);
	    	this.id = dni;
	        this.telefono = telefono;
	        this.direccion = direccion;
	    }
	    


		public List<Venta> getVentas() {
	        return ventas;
	    }

	    public void setVentas(List<Venta> ventas) {
	        this.ventas = ventas;
	    }
	    
	    public Integer getId() {
			return id;
		}


		public void setId(Integer id) {
			this.id = id;
		}


		public Integer getTelefono() {
			return telefono;
		}


		public void setTelefono(Integer telefono) {
			this.telefono = telefono;
		}


		public String getDireccion() {
			return direccion;
		}


		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}
	    
		
	 
}