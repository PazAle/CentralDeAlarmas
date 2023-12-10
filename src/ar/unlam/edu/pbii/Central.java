package ar.unlam.edu.pbii;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Central {

	private String nombre;
	private List<Alarma> alarmasRegistradas;
	private Set<Usuario> usuariosRegistrados;
	
	public Central(String nombre) {
		this.nombre = nombre;
		this.alarmasRegistradas = new LinkedList<>();
		this.usuariosRegistrados = new HashSet<>();
	}

	public Boolean registrarAlarma(Alarma alarma) throws AlarmaDuplicadaException {
		Boolean seRegistro = false;
		if(alarmasRegistradas.size() > 0) {
			for(Alarma alarmaBuscada: alarmasRegistradas) {
				if(alarmaBuscada.getId().equals(alarma.getId())){
					throw new AlarmaDuplicadaException("La alarma que intenta ingresar, ya se encuentra registrada");
				} else {
					alarmasRegistradas.add(alarma);
					seRegistro = true;
				}
			}
		}else {
			alarmasRegistradas.add(alarma);
			seRegistro = true;
		}
		return seRegistro;
	}

	public Boolean registrarUsuario(Usuario usuario) throws UsuarioRegistradoException {
		Boolean usuarioBuscado = existeUsuario(usuario);
		Boolean sePudoRegistrar = false;
		if(usuarioBuscado) {
			throw new UsuarioRegistradoException("El usuario que intenta ingresar, ya se encuentra registrado");
		} else {
			this.usuariosRegistrados.add(usuario);
			sePudoRegistrar = true;
		}
		return sePudoRegistrar;
	}

	private Boolean existeUsuario(Usuario usuario) {
		Boolean buscado = false;
		for(Usuario usuarioBuscado: usuariosRegistrados) {
			if(usuarioBuscado.equals(usuario)) {
				buscado = true;
			}
		}
		return buscado;
	}

	public Usuario buscarUsuarioPorDni(Integer dniUsuario) throws UsuarioNoRegistradoException {
		Usuario buscado = null;
		for(Usuario usuario: usuariosRegistrados) {
			if(usuario.getDni().equals(dniUsuario)) {
				buscado = usuario;
			} 
		}
		
		if(buscado == null) {
			throw new UsuarioNoRegistradoException("El usuario no se encuentra registrado");
		}
		
		return buscado;
	}

	public Alarma buscarAlarmaPorId(Integer idAlarma) throws AlarmaNoRegistradaException {
		Alarma buscada = null;
		for(Alarma alarma: alarmasRegistradas) {
			if(alarma.getId().equals(idAlarma)) {
				buscada = alarma;
			} else {
				throw new AlarmaNoRegistradaException("La alarma que está buscando, no se encuentra registrada en el sistema");
			}
		}
		return buscada;
	}

}
