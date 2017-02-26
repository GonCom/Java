package entidades;

import java.sql.Date;
import javax.persistence.Entity;


public class Pedido {
	private int idpedido;
	private int idcliente;
	private String estado;
	private String cobrado;
	private Date fecha;
	private String direccionenvio;
	
	public Pedido() {
		
	}
	
	public int getIdpedido() {
		return idpedido;
	}

	public void setIdpedido(int idpedido) {
		this.idpedido = idpedido;
	}

	public int getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCobrado() {
		return cobrado;
	}

	public void setCobrado(String cobrado) {
		this.cobrado = cobrado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDireccionenvio() {
		return direccionenvio;
	}

	public void setDireccionenvio(String direccionenvio) {
		this.direccionenvio = direccionenvio;
	}

	

}
