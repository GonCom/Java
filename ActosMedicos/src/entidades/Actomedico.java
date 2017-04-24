package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the ACTOMEDICO database table.
 * 
 */
@Entity
@NamedQuery(name="Actomedico.findAll", query="SELECT a FROM Actomedico a")
public class Actomedico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long codigo;

	private String descripcion;

	private BigDecimal preciomedico;

	//bi-directional many-to-one association to Actosconsulta
	@OneToMany(mappedBy="actomedico")
	private List<Actosconsulta> actosconsultas;

	//bi-directional many-to-one association to Tarifa
	@OneToMany(mappedBy="actomedico")
	private List<Tarifa> tarifas;

	public Actomedico() {
	}

	public long getCodigo() {
		return this.codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getPreciomedico() {
		return this.preciomedico;
	}

	public void setPreciomedico(BigDecimal preciomedico) {
		this.preciomedico = preciomedico;
	}

	public List<Actosconsulta> getActosconsultas() {
		return this.actosconsultas;
	}

	public void setActosconsultas(List<Actosconsulta> actosconsultas) {
		this.actosconsultas = actosconsultas;
	}

	public Actosconsulta addActosconsulta(Actosconsulta actosconsulta) {
		getActosconsultas().add(actosconsulta);
		actosconsulta.setActomedico(this);

		return actosconsulta;
	}

	public Actosconsulta removeActosconsulta(Actosconsulta actosconsulta) {
		getActosconsultas().remove(actosconsulta);
		actosconsulta.setActomedico(null);

		return actosconsulta;
	}

	public List<Tarifa> getTarifas() {
		return this.tarifas;
	}

	public void setTarifas(List<Tarifa> tarifas) {
		this.tarifas = tarifas;
	}

	public Tarifa addTarifa(Tarifa tarifa) {
		getTarifas().add(tarifa);
		tarifa.setActomedico(this);

		return tarifa;
	}

	public Tarifa removeTarifa(Tarifa tarifa) {
		getTarifas().remove(tarifa);
		tarifa.setActomedico(null);

		return tarifa;
	}

}