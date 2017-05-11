package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the FACTURA database table.
 * 
 */
@Entity
@NamedQuery(name="Factura.findAll", query="SELECT f FROM Factura f")
public class Factura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long codfactura;

	private String descripcion;

	@Temporal(TemporalType.DATE)
	private Date fechafactura;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="CODCLIENTE")
	private Cliente cliente;

	public Factura() {
	}

	public long getCodfactura() {
		return this.codfactura;
	}

	public void setCodfactura(long codfactura) {
		this.codfactura = codfactura;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechafactura() {
		return this.fechafactura;
	}

	public void setFechafactura(Date fechafactura) {
		this.fechafactura = fechafactura;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}