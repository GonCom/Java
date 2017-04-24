package entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the TARIFA database table.
 * 
 */
@Embeddable
public class TarifaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column
	private String compania;

	@Column
	private long actomedico;

	public TarifaPK() {
	}
	public String getCompania() {
		return this.compania;
	}
	public void setCompania(String compania) {
		this.compania = compania;
	}
	public long getActomedico() {
		return this.actomedico;
	}
	public void setActomedico(long actomedico) {
		this.actomedico = actomedico;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TarifaPK)) {
			return false;
		}
		TarifaPK castOther = (TarifaPK)other;
		return 
			this.compania.equals(castOther.compania)
			&& (this.actomedico == castOther.actomedico);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.compania.hashCode();
		hash = hash * prime + ((int) (this.actomedico ^ (this.actomedico >>> 32)));
		
		return hash;
	}
}