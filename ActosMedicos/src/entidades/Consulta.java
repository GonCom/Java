package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the CONSULTA database table.
 * 
 */
@Entity
@NamedQuery(name="Consulta.findAll", query="SELECT c FROM Consulta c")
public class Consulta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long numero;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	//bi-directional many-to-one association to Actosconsulta
	@OneToMany(mappedBy="consulta")
	private List<Actosconsulta> actosconsultas;

	//bi-directional many-to-one association to Compania
	@ManyToOne
	@JoinColumn(name="COMPANIACARGO")
	private Compania compania;

	//bi-directional many-to-one association to Paciente
	@ManyToOne
	@JoinColumn(name="PACIENTE")
	private Paciente paciente;

	public Consulta() {
	}

	public long getNumero() {
		return this.numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<Actosconsulta> getActosconsultas() {
		return this.actosconsultas;
	}

	public void setActosconsultas(List<Actosconsulta> actosconsultas) {
		this.actosconsultas = actosconsultas;
	}

	public Actosconsulta addActosconsulta(Actosconsulta actosconsulta) {
		getActosconsultas().add(actosconsulta);
		actosconsulta.setConsulta(this);

		return actosconsulta;
	}

	public Actosconsulta removeActosconsulta(Actosconsulta actosconsulta) {
		getActosconsultas().remove(actosconsulta);
		actosconsulta.setConsulta(null);

		return actosconsulta;
	}

	public Compania getCompania() {
		return this.compania;
	}

	public void setCompania(Compania compania) {
		this.compania = compania;
	}

	public Paciente getPaciente() {
		return this.paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

}