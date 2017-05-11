package entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the GRUPOS database table.
 * 
 */
@Entity
@Table(name="GRUPOS")
@NamedQuery(name="Grupo.findAll", query="SELECT g FROM Grupo g")
public class Grupo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GrupoPK id;

	public Grupo() {
	}

	public GrupoPK getId() {
		return this.id;
	}

	public void setId(GrupoPK id) {
		this.id = id;
	}

}