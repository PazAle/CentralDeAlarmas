package ar.unlam.edu.pbii;

public interface Activable {

	Boolean activarDesactivarAlarma(Integer idAlarma, Integer codActivacion, Usuario usuario, Central central)
			throws AlarmaNoRegistradaException, UsuarioNoAutorizadoException, SensorDesactivadoException;
}
