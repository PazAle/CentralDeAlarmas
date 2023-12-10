package ar.unlam.edu.pbii;

public class Activador extends Usuario implements Activable{

	public Activador(Integer dNI, String nombre) {
		super(dNI, nombre);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean activarDesactivarAlarma(Integer idAlarma, Integer codActivacion, Usuario usuario, Central central)
			throws AlarmaNoRegistradaException, UsuarioNoAutorizadoException, SensorDesactivadoException {
		Boolean seActivo = false;
		Alarma alarmaBuscada = central.buscarAlarmaPorId(idAlarma);
		Boolean usuarioAutorizado = alarmaBuscada.existeUsuario(usuario);
		if(usuarioAutorizado) {
			seActivo = alarmaBuscada.activar(alarmaBuscada, codActivacion);
		}
		
		if(!seActivo) {
			alarmaBuscada.registrarAccion(TipoDeAccion.DESACTIVACION);
		} else {
			alarmaBuscada.registrarAccion(TipoDeAccion.ACTIVACION);
		}
		
		return seActivo;
	}

}
