package entidades;

public class Libro {
	private String isbn;
	private String titulo;
	private long idautor;
	private String ejemplarestotales;
	private String ejemplaresprestados;
	private String ejemplaresdisponibles;
	private String autor;

	public Libro() {
		// TODO Auto-generated constructor stub
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public long getIdautor() {
		return idautor;
	}

	public void setIdautor(long idautor) {
		this.idautor = idautor;
	}

	public String getEjemplarestotales() {
		return ejemplarestotales;
	}

	public void setEjemplarestotales(String ejemplarestotales) {
		this.ejemplarestotales = ejemplarestotales;
	}

	public String getEjemplaresprestados() {
		return ejemplaresprestados;
	}

	public void setEjemplaresprestados(String ejemplaresprestados) {
		this.ejemplaresprestados = ejemplaresprestados;
	}

	public String getEjemplaresdisponibles() {
		return ejemplaresdisponibles;
	}

	public void setEjemplaresdisponibles(String ejemplaresdisponibles) {
		this.ejemplaresdisponibles = ejemplaresdisponibles;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

}
