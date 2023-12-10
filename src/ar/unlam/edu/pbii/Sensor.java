package ar.unlam.edu.pbii;

public class Sensor {

	private Integer id;
	private Boolean estado;
	
	public Sensor(Integer id) {
		this.id = id;
		this.estado = false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	
}
