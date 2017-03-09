package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TURNO database table.
 * 
 */
@Entity
@NamedQuery(name="Turno.findAll", query="SELECT t FROM Turno t")
public class Turno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long idturno;

	private String descripcion;

	//bi-directional many-to-one association to Ofertaeducativa
	@OneToMany(mappedBy="turno")
	private List<Ofertaeducativa> ofertaeducativas;

	public Turno() {
	}

	public long getIdturno() {
		return this.idturno;
	}

	public void setIdturno(long idturno) {
		this.idturno = idturno;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Ofertaeducativa> getOfertaeducativas() {
		return this.ofertaeducativas;
	}

	public void setOfertaeducativas(List<Ofertaeducativa> ofertaeducativas) {
		this.ofertaeducativas = ofertaeducativas;
	}

	public Ofertaeducativa addOfertaeducativa(Ofertaeducativa ofertaeducativa) {
		getOfertaeducativas().add(ofertaeducativa);
		ofertaeducativa.setTurno(this);

		return ofertaeducativa;
	}

	public Ofertaeducativa removeOfertaeducativa(Ofertaeducativa ofertaeducativa) {
		getOfertaeducativas().remove(ofertaeducativa);
		ofertaeducativa.setTurno(null);

		return ofertaeducativa;
	}

}