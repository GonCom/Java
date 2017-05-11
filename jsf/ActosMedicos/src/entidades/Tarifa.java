package entidades;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the TARIFA database table.
 * 
 */
@Entity
@NamedQuery(name="Tarifa.findAll", query="SELECT t FROM Tarifa t")
public class Tarifa implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TarifaPK id;

	private BigDecimal precio;

	//bi-directional many-to-one association to Actomedico
	@ManyToOne
	@JoinColumn(name="ACTOMEDICO",insertable=false, updatable=false)
	private Actomedico actomedico;

	//bi-directional many-to-one association to Compania
	@ManyToOne
	@JoinColumn(name="COMPANIA" ,insertable=false, updatable=false)
	private Compania compania;

	public Tarifa() {
	}

	public TarifaPK getId() {
		return this.id;
	}

	public void setId(TarifaPK id) {
		this.id = id;
	}

	public BigDecimal getPrecio() {
		return this.precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public Actomedico getActomedico() {
		return this.actomedico;
	}

	public void setActomedico(Actomedico actomedico) {
		this.actomedico = actomedico;
	}

	public Compania getCompania() {
		return this.compania;
	}

	public void setCompania(Compania compania) {
		this.compania = compania;
	}

}