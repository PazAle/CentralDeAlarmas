package ar.unlam.edu.pbii;

import java.util.Set;
import java.util.TreeSet;

public class Administrador extends Usuario {

	public Administrador(Integer dNI, String nombre) {
		super(dNI, nombre);
		
	}

	public Boolean agregarAlarma(Central central, Alarma alarma) throws AlarmaDuplicadaException {
		Boolean seRegistro = central.registrarAlarma(alarma);
		return seRegistro;
	}

	public Boolean agregarUsuario(Central central, Usuario usuario) throws UsuarioRegistradoException {
		Boolean seRegistro = central.registrarUsuario(usuario);
		return seRegistro;
	}

	public void agregarUsuarioaUnaAlarma(Integer dniUsuario, Integer idAlarma, Integer codConfig, Central central)
			throws UsuarioNoRegistradoException, AlarmaNoRegistradaException, CodigoAlarmaIncorrectoException {
		Usuario usuarioBuscado = central.buscarUsuarioPorDni(dniUsuario);
		Alarma alarmaBuscada = central.buscarAlarmaPorId(idAlarma);
		alarmaBuscada.agregarUsuario(usuarioBuscado, codConfig);
	}
	
	public Boolean agregarSensorAUnaAlarma(Integer idAlarma, Integer codConfiguracion, Sensor sensor, Central central)
			throws AlarmaNoRegistradaException, CodigoAlarmaIncorrectoException, SensorDuplicadoException {
		Boolean seAgrego = false;
		Alarma alarma = central.buscarAlarmaPorId(idAlarma);
		Boolean codigoCorrecto = verificarCodigoConfiguracion(alarma, codConfiguracion);
		if(codigoCorrecto) {
			alarma.agregarSensor(sensor);
		}
		return seAgrego;
	}

	private Boolean verificarCodigoConfiguracion(Alarma alarma, Integer codConfiguracion) throws CodigoAlarmaIncorrectoException {
		Boolean codigoCorrecto = false;
		if(alarma.getCodConfig().equals(codConfiguracion)) {
			codigoCorrecto = true;
		} else {
			throw new CodigoAlarmaIncorrectoException("El código es incorrecto");
		}
		return codigoCorrecto;
	}

	public Set<Accion> obtenerUnaListaDeAccionesOrdenada(Alarma alarma, TipoDeAccion accion) {
		Set<Accion> acciones = new TreeSet<>();
		
		for(Accion accionBuscada: alarma.getAccionesRealizadas()) {
			if(accionBuscada.getAccion().equals(accion)) {
				acciones.add(accionBuscada);
			}
		}
		return acciones;
	}

}
