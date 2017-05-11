package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the OFERTAEDUCATIVA database table.
 * 
 */
@Entity
@NamedQuery(name="Ofertaeducativa.findAll", query="SELECT o FROM Ofertaeducativa o")
public class Ofertaeducativa implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OfertaeducativaPK id;

	private BigDecimal plazas;

	//bi-directional many-to-one association to Ciclo
	@ManyToOne
	@JoinColumn(name="IDCICLO",insertable=false, updatable=false)
	private Ciclo ciclo;

	//bi-directional many-to-one association to y
	@ManyToOne
	@JoinColumn(name="IDIES",insertable=false, updatable=false)
	private IES IES;

	//bi-directional many-to-one association to Turno
	@ManyToOne
	@JoinColumn(name="TURNO",insertable=false, updatable=false)
	private Turno turno;

	public Ofertaeducativa() {
	}

	public OfertaeducativaPK getId() {
		return this.id;
	}

	public void setId(OfertaeducativaPK id) {
		this.id = id;
	}

	public BigDecimal getPlazas() {
		return this.plazas;
	}

	public void setPlazas(BigDecimal plazas) {
		this.plazas = plazas;
	}

	public Ciclo getCiclo() {
		return this.ciclo;
	}

	public void setCiclo(Ciclo ciclo) {
		this.ciclo = ciclo;
	}

	public IES getIES() {
		return this.IES;
	}

	public void setIES(IES IES) {
		this.IES = IES;
	}

	public Turno getTurno() {
		return this.turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

}