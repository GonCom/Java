package entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the EMPRESA database table.
 * 
 */
@Entity
@NamedQuery(name="Empresa.findAll", query="SELECT e FROM Empresa e")
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long codempresa;

	private String cif;

	private String nombreempresa;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="CODCLIENTE")
	private Cliente cliente;

	public Empresa() {
	}

	public long getCodempresa() {
		return this.codempresa;
	}

	public void setCodempresa(long codempresa) {
		this.codempresa = codempresa;
	}

	public String getCif() {
		return this.cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getNombreempresa() {
		return this.nombreempresa;
	}

	public void setNombreempresa(String nombreempresa) {
		this.nombreempresa = nombreempresa;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}