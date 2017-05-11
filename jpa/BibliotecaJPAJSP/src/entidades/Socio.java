package entidades;

import java.io.Serializable;

import javax.persistence.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;


/**
 * The persistent class for the SOCIO database table.
 * 
 */
@Entity
@NamedQuery(name="Socio.findAll", query="SELECT s FROM Socio s")
public class Socio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SOCIO_IDSOCIO_GENERATOR", sequenceName="S_SOCIO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SOCIO_IDSOCIO_GENERATOR")
	private long idsocio;

	private String direccion;

	private String nombre;

	//bi-directional many-to-one association to Prestamo
	@OneToMany(mappedBy="socio")
	private List<Prestamo> prestamos;

	public Socio() {
	}
	

	public long getIdsocio() {
		return this.idsocio;
	}

	public void setIdsocio(long idsocio) {
		this.idsocio = idsocio;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Prestamo> getPrestamos() {
		return this.prestamos;
	}

	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}

	
	public static String encriptar(String passwd) {
		try {
			final char[] Hexadecimales = { '0', '1', '2', '3', '4', '5', '6',
					'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

			MessageDigest msgdgt = MessageDigest.getInstance("MD5");
			byte[] bytes = msgdgt.digest(passwd.getBytes());
			StringBuilder strCryptMD5 = new StringBuilder(2 * bytes.length);
			for (int i = 0; i < bytes.length; i++) {
				int low = (int) (bytes[i] & 0x0f);
				int high = (int) ((bytes[i] & 0xf0) >> 4);
				strCryptMD5.append(Hexadecimales[high]);
				strCryptMD5.append(Hexadecimales[low]);
			}
			return strCryptMD5.toString();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}//encriptar

	public Prestamo addPrestamo(Prestamo prestamo) {
		getPrestamos().add(prestamo);
		prestamo.setSocio(this);

		return prestamo;
	}

	public Prestamo removePrestamo(Prestamo prestamo) {
		getPrestamos().remove(prestamo);
		prestamo.setSocio(null);

		return prestamo;
	}

}//Socio