package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the COMPANIA database table.
 * 
 */
@Entity
@NamedQuery(name="Compania.findAll", query="SELECT c FROM Compania c")
public class Compania implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String cif;

	private String nombre;

	//bi-directional many-to-one association to Consulta
	@OneToMany(mappedBy="compania")
	private List<Consulta> consultas;

	//bi-directional many-to-one association to Tarifa
	@OneToMany(mappedBy="compania",fetch=FetchType.EAGER)
	private List<Tarifa> tarifas;

	public Compania() {
	}

	public String getCif() {
		return this.cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Consulta> getConsultas() {
		return this.consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}

	public Consulta addConsulta(Consulta consulta) {
		getConsultas().add(consulta);
		consulta.setCompania(this);

		return consulta;
	}

	public Consulta removeConsulta(Consulta consulta) {
		getConsultas().remove(consulta);
		consulta.setCompania(null);

		return consulta;
	}

	public List<Tarifa> getTarifas() {
		return this.tarifas;
	}

	public void setTarifas(List<Tarifa> tarifas) {
		this.tarifas = tarifas;
	}

	public Tarifa addTarifa(Tarifa tarifa) {
		getTarifas().add(tarifa);
		tarifa.setCompania(this);

		return tarifa;
	}

	public Tarifa removeTarifa(Tarifa tarifa) {
		getTarifas().remove(tarifa);
		tarifa.setCompania(null);

		return tarifa;
	}

}