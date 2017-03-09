package backing;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import entidades.Ciclo;
import entidades.Ofertaeducativa;
import entidades.OfertaeducativaPK;
import entidades.Turno;

public class BackingOferta {

	@EJB
	private ejb.OfertaBean ejbOferta;
	private List<Ofertaeducativa> listadoOfertas;
	private Ofertaeducativa oferta;
	private Ofertaeducativa ofertaEliminar;
	private OfertaeducativaPK nuevaOferta;
	private long idCiclo;
	private long idTurno;
	private BigDecimal plazas;
	private long idies;
	private List<Ciclo> listadoCiclos;
	private List<Turno> turnos;

	
	public BackingOferta() {
		// TODO Auto-generated constructor stub
	}

	public ejb.OfertaBean getEjbOferta() {
		return ejbOferta;
	}

	public void setEjbOferta(ejb.OfertaBean ejbOferta) {
		this.ejbOferta = ejbOferta;
	}

	public List<Ofertaeducativa> getListadoOfertas() {
		return listadoOfertas;
	}

	public void setListadoOfertas(List<Ofertaeducativa> listadoOfertas) {
		this.listadoOfertas = listadoOfertas;
	}

	public Ofertaeducativa getOferta() {
		return oferta;
	}

	public void setOferta(Ofertaeducativa oferta) {
		this.oferta = oferta;
	}
	
	public Ofertaeducativa getOfertaEliminar() {
		return ofertaEliminar;
	}

	public void setOfertaEliminar(Ofertaeducativa ofertaEliminar) {
		this.ofertaEliminar = ofertaEliminar;
	}
	
	public long getIdies() {
		return idies;
	}

	public void setIdies(long idies) {
		this.idies = idies;
	}
			
	public List<Ciclo> getListadoCiclos() {
		return listadoCiclos;
	}

	public void setListadoCiclos(List<Ciclo> listadoCiclos) {
		this.listadoCiclos = listadoCiclos;
	}

	public List<Turno> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}
	
	public OfertaeducativaPK getNuevaOferta() {
		return nuevaOferta;
	}

	public void setNuevaOferta(OfertaeducativaPK nuevaOferta) {
		this.nuevaOferta = nuevaOferta;
	}
	
	public long getIdCiclo() {
		return idCiclo;
	}

	public void setIdCiclo(long idCiclo) {
		this.idCiclo = idCiclo;
	}

	public long getIdTurno() {
		return idTurno;
	}

	public void setIdTurno(long idTurno) {
		this.idTurno = idTurno;
	}

	public BigDecimal getPlazas() {
		return plazas;
	}

	public void setPlazas(BigDecimal plazas) {
		this.plazas = plazas;
	}

	/************************************************************************************************************/
	public String mostrarOfertasEducativas(){
		listadoOfertas=getEjbOferta().mostrarOfertasEduc(idies);
		
		return "listarIES.xhtml";
	}
	
	public String editarOfertaEduc(){
		FacesContext fc=FacesContext.getCurrentInstance();
		Map<String,String> ruta=fc.getExternalContext().getRequestParameterMap();
		String url=ruta.get("pagina");
		fc.getCurrentInstance().getExternalContext().getSessionMap().put("pagina", url);
		return "editarOfertaEduc.xhtml";
	}

	public String modificarPlazas(){
		System.out.println("Entrando en modificarPlazas...");
		getEjbOferta().modificarPlazasOferta(oferta);
		FacesContext context=FacesContext.getCurrentInstance();
		ResourceBundle archivomensajes=ResourceBundle.getBundle("resources.application",context.getViewRoot().getLocale());
		context.addMessage(null, new FacesMessage(archivomensajes.getString("generico.modificadoCorrectamente")));
		return "listarIES.xhtml";
	}
	
	public String eliminarOfertaEduc(){
		System.out.println("Entrando en eliminarOfertaEduc...");
		
		getEjbOferta().eliminarOferta(ofertaEliminar);
		mostrarOfertasEducativas();
		FacesContext context=FacesContext.getCurrentInstance();
		ResourceBundle archivomensajes=ResourceBundle.getBundle("resources.application",context.getViewRoot().getLocale());
		context.addMessage(null, new FacesMessage(archivomensajes.getString("generico.eliminadoCorrectamente")));
		return "listarIES.xhtml";
	}
	
	public int getTotalItemsOferta(){
		if(listadoOfertas!=null){
			return listadoOfertas.size();
		}else{
			return 0;
		}
	}
	public String volverAtras(){
		String url=(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("pagina");
		return url;
	}
	
	public String añadirOferta(){
		FacesContext fc=FacesContext.getCurrentInstance();
		Map<String,String> ruta=fc.getExternalContext().getRequestParameterMap();
		String url=ruta.get("pagina");
		fc.getCurrentInstance().getExternalContext().getSessionMap().put("pagina2", url);
		listadoCiclos=getEjbOferta().listarCiclos(idies);
		turnos=getEjbOferta().listarTurnos();
		this.nuevaOferta=new OfertaeducativaPK();
		nuevaOferta.setIdies(idies);
		return "nuevaOferta.xhtml";
	}
	
	public String darAltaOferta(){
		System.out.println("Entrando en darAltaOferta...");
		System.out.println("IDIES: " + nuevaOferta.getIdies() + "IDCiclo: " + nuevaOferta.getIdciclo() +
				"Turno: " + nuevaOferta.getTurno() + "Plazas: " + plazas);
		FacesContext context=FacesContext.getCurrentInstance();
		ResourceBundle archivomensajes=ResourceBundle.getBundle("resources.application",context.getViewRoot().getLocale());
		context.addMessage(null, new FacesMessage(archivomensajes.getString("generico.añadirOferta")));
		getEjbOferta().añadirNuevaOferta(nuevaOferta,plazas);
		mostrarOfertasEducativas();
		return "listarIES.xhtml";
	}
}
