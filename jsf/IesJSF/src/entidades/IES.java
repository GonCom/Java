package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the IES database table.
 * 
 */
@Entity
@Table(name="IES")
public class IES implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long idies;

	private String nombre;

	//bi-directional many-to-one association to Ofertaeducativa
	@OneToMany(mappedBy="IES")
	private List<Ofertaeducativa> ofertaeducativas;

	public IES() {
	}

	public long getIdies() {
		return this.idies;
	}

	public void setIdies(long idies) {
		this.idies = idies;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Ofertaeducativa> getOfertaeducativas() {
		return this.ofertaeducativas;
	}

	public void setOfertaeducativas(List<Ofertaeducativa> ofertaeducativas) {
		this.ofertaeducativas = ofertaeducativas;
	}

	public Ofertaeducativa addOfertaeducativa(Ofertaeducativa ofertaeducativa) {
		getOfertaeducativas().add(ofertaeducativa);
		ofertaeducativa.setIES(this);

		return ofertaeducativa;
	}

	public Ofertaeducativa removeOfertaeducativa(Ofertaeducativa ofertaeducativa) {
		getOfertaeducativas().remove(ofertaeducativa);
		ofertaeducativa.setIES(null);

		return ofertaeducativa;
	}

}