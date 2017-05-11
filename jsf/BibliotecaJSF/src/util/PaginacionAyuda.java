package util;

public abstract class PaginacionAyuda {

	private int pagina;//indica el numero de página
	private int numRegPag;//indica el nº de registros a mostrar por pagina
	
	public PaginacionAyuda(int tampagina,int pag) {
		this.pagina=pag;
		this.numRegPag=tampagina;
	}

	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}

	public int getNumRegPag() {
		return numRegPag;
	}

	public void setNumRegPag(int numRegPag) {
		this.numRegPag = numRegPag;
	}

	public abstract long getItemsCount(); 
	
	
	public boolean isHayPaginaSiguiente(){
		if((((pagina+1)*numRegPag)+1)<=getItemsCount()){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isHayPaginaAnterior(){
		if(pagina>0)
			return true;
		else
			return false;
	}
	
	public void getPaginaSig(){
		if(isHayPaginaSiguiente())
			pagina++;
	}
	
	public void getPaginaAnt(){
		if(isHayPaginaAnterior())
			pagina--;
	}
	
	public int getPrimerElemento(){
		return (pagina*numRegPag)+1;
	}
	
	public int getUltimoElemento(){
		int ultimo=(pagina*numRegPag)+numRegPag;
		if(ultimo>getItemsCount()){
			ultimo=(int) getItemsCount();
		}
		if(ultimo<1){
			ultimo=1;
		}
		return ultimo;
	}
}
