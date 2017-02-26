package entidades;

import javax.persistence.Entity;


public class Producto {
	private int id;
	private String nombre;
	private int precio_normal;
	private int precio_minimo;
	private int cantidad;
	public Producto() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPrecio_normal() {
		return precio_normal;
	}

	public void setPrecio_normal(int precio_normal) {
		this.precio_normal = precio_normal;
	}

	public int getPrecio_minimo() {
		return precio_minimo;
	}

	public void setPrecio_minimo(int precio_minimo) {
		this.precio_minimo = precio_minimo;
	}
	
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public boolean equals(Object q) {
		if (q == null)
			return false;
		if (q == this)
			return true;
		if (!(q instanceof Producto))
			return false;
		Producto p = (Producto) q;
		if (id == (p.getId()))
			return true;
		else
			return false;
	}
	

}//fin Producto
