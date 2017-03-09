package entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the OFERTAEDUCATIVA database table.
 * 
 */
@Embeddable
public class OfertaeducativaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column
	private long idies;

	@Column
	private long idciclo;

	@Column
	private long turno;

	public OfertaeducativaPK() {
	}
	public long getIdies() {
		return this.idies;
	}
	public void setIdies(long idies) {
		this.idies = idies;
	}
	public long getIdciclo() {
		return this.idciclo;
	}
	public void setIdciclo(long idciclo) {
		this.idciclo = idciclo;
	}
	public long getTurno() {
		return this.turno;
	}
	public void setTurno(long turno) {
		this.turno = turno;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OfertaeducativaPK)) {
			return false;
		}
		OfertaeducativaPK castOther = (OfertaeducativaPK)other;
		return 
			(this.idies == castOther.idies)
			&& (this.idciclo == castOther.idciclo)
			&& (this.turno == castOther.turno);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.idies ^ (this.idies >>> 32)));
		hash = hash * prime + ((int) (this.idciclo ^ (this.idciclo >>> 32)));
		hash = hash * prime + ((int) (this.turno ^ (this.turno >>> 32)));
		
		return hash;
	}
}