package ar.unlam.edu.pbii;

public interface Configurable {

	void agregarUsuarioAUnaAlarma(Integer dniUsuario, Integer idAlarma, Integer codConfig, Central central) 
			throws UsuarioNoRegistradoException, AlarmaNoRegistradaException, CodigoAlarmaIncorrectoException;
	Boolean agregarSensorAUnaAlarma(Integer idAlarma, Integer codConfiguracion, Sensor sensor, Central central) 
			throws CodigoAlarmaIncorrectoException, AlarmaNoRegistradaException, UsuarioNoRegistradoException, SensorDuplicadoException;
	Boolean activarSensor(Integer idSensor, Integer idAlarma, Integer codActivacion, Central central) 
			throws AlarmaNoRegistradaException, CodigoSensorInvalidoException;
}
