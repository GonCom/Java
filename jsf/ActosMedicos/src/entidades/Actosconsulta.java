package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ACTOSCONSULTA database table.
 * 
 */
@Entity
@NamedQuery(name="Actosconsulta.findAll", query="SELECT a FROM Actosconsulta a")
public class Actosconsulta implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ActosconsultaPK id;

	private String facturadoa;

	private BigDecimal precio;

	//bi-directional many-to-one association to Actomedico
	@ManyToOne
	@JoinColumn(name="ACTOMEDICO")
	private Actomedico actomedico;

	//bi-directional many-to-one association to Consulta
	@ManyToOne
	@JoinColumn(name="CONSULTA")
	private Consulta consulta;

	public Actosconsulta() {
	}

	public ActosconsultaPK getId() {
		return this.id;
	}

	public void setId(ActosconsultaPK id) {
		this.id = id;
	}

	public String getFacturadoa() {
		return this.facturadoa;
	}

	public void setFacturadoa(String facturadoa) {
		this.facturadoa = facturadoa;
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

	public Consulta getConsulta() {
		return this.consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

}