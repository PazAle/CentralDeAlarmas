package ar.unlam.edu.pbii;

public class Configurador extends Usuario implements Configurable{

	public Configurador(Integer dNI, String nombre) {
		super(dNI, nombre);
		
	}

	@Override
	public void agregarUsuarioAUnaAlarma(Integer dniUsuario, Integer idAlarma, Integer codConfig, Central central)
			throws UsuarioNoRegistradoException, AlarmaNoRegistradaException, CodigoAlarmaIncorrectoException {
		Usuario usuarioBuscado = central.buscarUsuarioPorDni(dniUsuario);
		Alarma alarmaBuscada = central.buscarAlarmaPorId(idAlarma);
		alarmaBuscada.agregarUsuario(usuarioBuscado, codConfig);
		alarmaBuscada.registrarAccion(TipoDeAccion.CONFIGURACION);
		
	}

	@Override
	public Boolean agregarSensorAUnaAlarma(Integer idAlarma, Integer codConfig, Sensor sensor,
			Central central) throws CodigoAlarmaIncorrectoException, AlarmaNoRegistradaException, UsuarioNoRegistradoException, SensorDuplicadoException {
		Boolean seAgrego = false;
		Alarma alarma = central.buscarAlarmaPorId(idAlarma);
		Boolean codigoCorrecto = verificarCodigoConfiguracion(alarma, codConfig);
		if(codigoCorrecto) {
			alarma.agregarSensor(sensor);
			alarma.registrarAccion(TipoDeAccion.CONFIGURACION);
		}
		return seAgrego;
	}

	private Boolean verificarCodigoConfiguracion(Alarma alarma, Integer codConfig) throws CodigoAlarmaIncorrectoException {
		Boolean codigoCorrecto = false;
		if(alarma.getCodConfig().equals(codConfig)) {
			codigoCorrecto = true;
		} else {
			throw new CodigoAlarmaIncorrectoException("El código es incorrecto");
		}
		return codigoCorrecto;
	}

	@Override
	public Boolean activarSensor(Integer idSensor, Integer idAlarma, Integer codConfig, Central central) throws AlarmaNoRegistradaException, CodigoSensorInvalidoException {
		Alarma alarmaBuscada = central.buscarAlarmaPorId(idAlarma);
		Boolean seActivo = alarmaBuscada.activarSensor(idSensor, codConfig);
		alarmaBuscada.registrarAccion(TipoDeAccion.CONFIGURACION);
		return seActivo;
	}


}
