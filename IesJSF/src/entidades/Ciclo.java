package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the CICLO database table.
 * 
 */
@Entity
@NamedQuery(name="Ciclo.findAll", query="SELECT c FROM Ciclo c")
public class Ciclo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long idciclo;

	private String grado;

	private String nombre;

	//bi-directional many-to-one association to Ofertaeducativa
	@OneToMany(mappedBy="ciclo")
	private List<Ofertaeducativa> ofertaeducativas;

	public Ciclo() {
	}

	public long getIdciclo() {
		return this.idciclo;
	}

	public void setIdciclo(long idciclo) {
		this.idciclo = idciclo;
	}

	public String getGrado() {
		return this.grado;
	}

	public void setGrado(String grado) {
		this.grado = grado;
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
		ofertaeducativa.setCiclo(this);

		return ofertaeducativa;
	}

	public Ofertaeducativa removeOfertaeducativa(Ofertaeducativa ofertaeducativa) {
		getOfertaeducativas().remove(ofertaeducativa);
		ofertaeducativa.setCiclo(null);

		return ofertaeducativa;
	}

}