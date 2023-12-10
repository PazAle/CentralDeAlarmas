package ar.unlam.edu.pbii;

public class Accion implements Comparable<Accion>{

	private Integer id;
	private TipoDeAccion accion;
	
	public Accion(Integer id, TipoDeAccion accion) {
		super();
		this.id = id;
		this.accion = accion;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipoDeAccion getAccion() {
		return accion;
	}

	public void setAccion(TipoDeAccion accion) {
		this.accion = accion;
	}

	
	
	@Override
	public String toString() {
		return "Accion [id=" + id + ", accion=" + accion + "]";
	}


	@Override
	public int compareTo(Accion o) {
		
		return this.getId().compareTo(o.getId());
	}
	
}
