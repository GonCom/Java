package entidades;

import javax.persistence.Entity;


public class ProductoCarro extends Producto{
	private int cantidad;
	
	public ProductoCarro() {
		super();
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public String toString(){
		return "ID: " + this.getId() + " Nombre : " + this.getNombre() + " Precio normal: " + this.getPrecio_normal()
				+ " Cantidad: " + this.getCantidad();
	}
	
}//fin ProductoCarro
