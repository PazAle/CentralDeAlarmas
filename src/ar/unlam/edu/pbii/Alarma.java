package ar.unlam.edu.pbii;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Alarma {

	private Integer id;
	private Integer codActivacion;
	private Integer codConfig;
	private String nombre;
	private Boolean activada;
	private Set<Usuario> usuarios;
	private Set<Accion> accionesRealizadas;
	private Set<Sensor> sensores;
	private Integer cantAcciones;
	
	public Alarma(Integer id, Integer codActivacion, Integer codConfig, String nombre) {
		this.id = id;
		this.codActivacion = codActivacion;
		this.codConfig = codConfig;
		this.nombre = nombre;
		this.activada = false;
		this.usuarios = new HashSet<>();
		this.accionesRealizadas = new TreeSet<>();
		this.sensores = new HashSet<>();
		this.cantAcciones = 0;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodActivacion() {
		return codActivacion;
	}

	public void setCodActivacion(Integer codActivacion) {
		this.codActivacion = codActivacion;
	}

	public Integer getCodConfig() {
		return codConfig;
	}

	public void setCodConfig(Integer codConfig) {
		this.codConfig = codConfig;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Set<Accion> getAccionesRealizadas() {
		return accionesRealizadas;
	}

	public void setAccionesRealizadas(Set<Accion> accionesRealizadas) {
		this.accionesRealizadas = accionesRealizadas;
	}

	public Set<Sensor> getSensores() {
		return sensores;
	}

	public void setSensores(Set<Sensor> sensores) {
		this.sensores = sensores;
	}
		
	public Boolean getActivada() {
		return activada;
	}

	public void setActivada(Boolean activada) {
		this.activada = activada;
	}
	
	public Integer getCantAcciones() {
		return cantAcciones;
	}

	public void setCantAcciones(Integer cantAcciones) {
		this.cantAcciones = cantAcciones;
	}

	public void agregarSensor(Sensor sensor) throws SensorDuplicadoException {
		Boolean sensorAgregado = existeSensor(sensor);
		if(!sensorAgregado) {
			this.sensores.add(sensor);
		}
	}

	private Boolean existeSensor(Sensor sensor) throws SensorDuplicadoException {
		Boolean existe = false;
		if(this.sensores.contains(sensor)) {
			throw new SensorDuplicadoException("El sensor que intenta agregar, ya se encuentra en la alarma");
		}
		return existe;
	}
	
	public Boolean existeUsuario(Usuario usuario) throws UsuarioNoAutorizadoException {
		Boolean existe = true;
		if(!this.getUsuarios().contains(usuario)) {
			throw new UsuarioNoAutorizadoException("El usuario NO está autorizado para activar/desactivar la alarma");
		}
		return existe;
	}

	public void agregarUsuario(Usuario usuarioBuscado, Integer codConfig) throws CodigoAlarmaIncorrectoException {
		if(this.getCodConfig().equals(codConfig)) {
			this.usuarios.add(usuarioBuscado);
		} else {
			throw new CodigoAlarmaIncorrectoException("El código de la alarma es incorrecto");
		}
		
	}

	public Boolean activar(Alarma alarmaBuscada, Integer codActivacion) throws SensorDesactivadoException {
		if(alarmaBuscada.getActivada().equals(true)) {
			alarmaBuscada.setActivada(false);
		} else {
			if(alarmaBuscada.getCodActivacion().equals(codActivacion)) {
				for(Sensor sensor: sensores) {
					if(sensor.getEstado().equals(false)) {
						throw new SensorDesactivadoException("La alarma que intenta activar cuenta con sensores desactivados");
					} else {
						this.setActivada(true);
					}
				}
			}
		}
		
		return this.getActivada();
	}

	public Boolean activarSensor(Integer idSensor, Integer codConfig) throws CodigoSensorInvalidoException {
		Sensor sensorBuscado = null;
		for(Sensor sensor: sensores) {
			if(sensor.getId().equals(idSensor) && sensor.getEstado().equals(false)) {
				sensorBuscado = sensor;
				sensorBuscado.setEstado(true);
			} 
		}
		
		if(sensorBuscado == null) {
			throw new CodigoSensorInvalidoException("No existe ningun sensor con el código proporcionado");
		}
		return sensorBuscado.getEstado();
	}

	public void registrarAccion(TipoDeAccion accion) {
		Integer cant = this.getAccionesRealizadas().size();
		cant++;
		this.getAccionesRealizadas().add(new Accion(cant, accion));
	}

}
