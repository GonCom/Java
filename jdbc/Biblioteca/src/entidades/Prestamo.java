package entidades;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Prestamo {
	private int idejemplar;
	private String nombre;
	private int idsocio;
	private String titulo;
	private Date fechaprestamo;
	private Date fechalimitedevolucion;
	private int diasdemora;

	public Prestamo() {

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getIdsocio() {
		return idsocio;
	}

	public void setIdsocio(int idsocio) {
		this.idsocio = idsocio;
	}

	public int getIdejemplar() {
		return idejemplar;
	}

	public void setIdejemplar(int idejemplar) {
		this.idejemplar = idejemplar;
	}

	public Date getFechaprestamo() {
		return fechaprestamo;
	}

	public void setFechaprestamo(Date fechaprestamo) {
		this.fechaprestamo = fechaprestamo;
	}

	public Date getFechalimitedevolucion() {
		return fechalimitedevolucion;
	}

	public void setFechalimitedevolucion(Date fechalimitedevolucion) {
		this.fechalimitedevolucion = fechalimitedevolucion;
	}

	public int getDiasdemora() {
		return diasdemora;
	}

	public void setDiasdemora(int diasdemora) {
		this.diasdemora = diasdemora;
	}

}
