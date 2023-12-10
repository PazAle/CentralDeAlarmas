package ar.unlam.edu.pbii;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Test {

	@org.junit.Test
	public void queSePuedaRegistrarUnaAlarmaEnLaCentral() {
		
		//Preparación
		
		final Integer DNI = 33456324, ID = 1, codActivacion = 13579, codConfig = 77899, idSensor1 = 1;
		String nombreAdmin = "Armando Barreda", nombreAlarma = "ZBT";
		
		Central central = new Central("Central de alarmas");
		Usuario administrador = new Administrador(DNI, nombreAdmin);
		Alarma alarmaDeAuto = new Alarma(ID, codActivacion, codConfig, nombreAlarma);
		Sensor sensorUno = new Sensor(idSensor1);
		
		//Ejecución
		Boolean seRegistro = false;
		try {
			alarmaDeAuto.agregarSensor(sensorUno);
			seRegistro = ((Administrador)administrador).agregarAlarma(central, alarmaDeAuto);
		} catch (AlarmaDuplicadaException | SensorDuplicadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Verificación
		assertTrue(seRegistro);
	}
	
	@org.junit.Test
	public void queSePuedaAgregarUnUsuarioConfiguradorAUnaAlarma() {
		
		//Preparación
		
		final Integer DNI = 33456324, DNIConfig= 34135234, ID = 1, codActivacion = 13579, codConfig = 77899, idSensor1 = 1;
		String nombreAdmin = "Armando Barreda", nombreConfig = "Jorge", nombreAlarma = "ZBT";
		
		Central central = new Central("Central de alarmas");
		Usuario administrador = new Administrador(DNI, nombreAdmin);
		Usuario configurador = new Configurador(DNIConfig, nombreAdmin);
		Alarma alarmaDeAuto = new Alarma(ID, codActivacion, codConfig, nombreAlarma);
		Sensor sensorUno = new Sensor(idSensor1);
		
		//Ejecución
		
		Boolean seRegistro = false, seRegistroUsuario;
		try {
			alarmaDeAuto.agregarSensor(sensorUno);
			seRegistro = ((Administrador)administrador).agregarAlarma(central, alarmaDeAuto);
			seRegistroUsuario = ((Administrador)administrador).agregarUsuario(central, configurador);
		} catch (AlarmaDuplicadaException | UsuarioRegistradoException | SensorDuplicadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Verificación
		assertTrue(seRegistro);
	}
	
	@org.junit.Test (expected = CodigoAlarmaIncorrectoException.class) 
	public void alAgregarUnUsuarioAUnaAlarmaConCodigoDeConfiguracionDeAlarmaInvalidoSeLanceCodigoAlarmaIncorrectoException()
			throws CodigoAlarmaIncorrectoException{
		
		//Preparación
		
		final Integer DNI = 33456324, DNIConfig= 34135234, ID = 1, codActivacion = 13579, codConfig = 77899, idSensor1 = 1;
		String nombreAdmin = "Armando Barreda", nombreConfig = "Jorge", nombreAlarma = "ZBT";
		
		Central central = new Central("Central de alarmas");
		Usuario administrador = new Administrador(DNI, nombreAdmin);
		Usuario configurador = new Configurador(DNIConfig, nombreAdmin);
		Alarma alarmaDeAuto = new Alarma(ID, codActivacion, codConfig, nombreAlarma);
		Sensor sensorUno = new Sensor(idSensor1);
		
		//Ejecución
		
		Boolean seRegistro = false, seRegistroUsuario;
		try {
			alarmaDeAuto.agregarSensor(sensorUno);
			seRegistro = ((Administrador)administrador).agregarAlarma(central, alarmaDeAuto);
			seRegistroUsuario = ((Administrador)administrador).agregarUsuario(central, configurador);
			((Administrador)administrador).agregarUsuarioaUnaAlarma(DNIConfig, ID, 777, central);
		} catch (AlarmaDuplicadaException | UsuarioRegistradoException | UsuarioNoRegistradoException | AlarmaNoRegistradaException
				| CodigoAlarmaIncorrectoException | SensorDuplicadoException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			throw new CodigoAlarmaIncorrectoException();
		}
		//Verificacion
		Integer valorEsperado = 0;
		Integer valorObtenido = alarmaDeAuto.getUsuarios().size();
		
		assertEquals(valorEsperado, valorObtenido);
	}
	
	@org.junit.Test (expected = SensorDuplicadoException.class) 
	public void alAgregarUnSensorDuplicadoEnUnaAlarmaSeLanceUnaSensorDuplicadoException()
			throws SensorDuplicadoException{
		
		//Preparación
		
		final Integer DNI = 33456324, DNIConfig= 34135234, ID = 1, codActivacion = 13579, codConfig = 77899, idSensor1 = 1;
		String nombreAdmin = "Armando Barreda", nombreConfig = "Jorge", nombreAlarma = "ZBT";
		
		Central central = new Central("Central de alarmas");
		Usuario administrador = new Administrador(DNI, nombreAdmin);
		Usuario configurador = new Configurador(DNIConfig, nombreAdmin);
		Alarma alarmaDeAuto = new Alarma(ID, codActivacion, codConfig, nombreAlarma);
		Sensor sensorUno = new Sensor(idSensor1);
		
		//Ejecución
		
		Boolean seRegistro = false, seRegistroUsuario;
		try {
			alarmaDeAuto.agregarSensor(sensorUno);
			alarmaDeAuto.agregarSensor(sensorUno);
			seRegistro = ((Administrador)administrador).agregarAlarma(central, alarmaDeAuto);
			seRegistroUsuario = ((Administrador)administrador).agregarUsuario(central, configurador);
			((Administrador)administrador).agregarUsuarioaUnaAlarma(DNIConfig, ID, 777, central);
		} catch (AlarmaDuplicadaException | UsuarioRegistradoException | UsuarioNoRegistradoException | AlarmaNoRegistradaException
				| CodigoAlarmaIncorrectoException | SensorDuplicadoException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			throw new SensorDuplicadoException();
		}
		//Verificacion
		Integer valorEsperado = 1;
		Integer valorObtenido = alarmaDeAuto.getSensores().size();
		
		assertEquals(valorEsperado, valorObtenido);
	}
	
	@org.junit.Test (expected = SensorDesactivadoException.class) 
	public void queNoSePuedaActivarUnaAlarmaSiHayAlMenosUnSensorDesactivado() throws SensorDesactivadoException{
		
		//Preparación
		
		final Integer DNI = 33456324, DNIConfig= 34135234, ID = 1, codActivacion = 13579, codConfig = 77899, idSensor1 = 1,
				idSensor2 = 2, DNIActivador = 33682349;
		String nombreAdmin = "Armando Barreda", nombreConfig = "Jorge", nombreActivador = "Armando Esteban Quito", nombreAlarma = "ZBT";
		
		Central central = new Central("Central de alarmas");
		Usuario administrador = new Administrador(DNI, nombreAdmin);
		Usuario configurador = new Configurador(DNIConfig, nombreAdmin);
		Usuario activador = new Activador(DNIActivador, nombreActivador);
		Alarma alarmaDeAuto = new Alarma(ID, codActivacion, codConfig, nombreAlarma);
		Sensor sensorUno = new Sensor(idSensor1);
		Sensor sensorDos = new Sensor(idSensor2);
		
		//Ejecución
		
		Boolean seRegistro = false, seRegistroUsuarioUno, seRegistroUsuarioDos, seRegistroSensorUno, seRegistroSensorDos, seActivoAlarma;
		try {
			seRegistro = ((Administrador)administrador).agregarAlarma(central, alarmaDeAuto);
			seRegistroSensorUno = ((Administrador)administrador).agregarSensorAUnaAlarma(ID, codConfig, sensorUno, central);
			seRegistroSensorDos = ((Administrador)administrador).agregarSensorAUnaAlarma(ID, codConfig, sensorDos, central);
			seRegistroUsuarioUno = ((Administrador)administrador).agregarUsuario(central, configurador);
			seRegistroUsuarioDos = ((Administrador)administrador).agregarUsuario(central, activador);
			((Administrador)administrador).agregarUsuarioaUnaAlarma(DNIConfig, ID, 77899, central);
			((Configurador)configurador).agregarUsuarioAUnaAlarma(DNIActivador, ID, 77899, central);
			seActivoAlarma = ((Activador)activador).activarDesactivarAlarma(ID, codActivacion, activador, central);
		} catch (AlarmaDuplicadaException | UsuarioRegistradoException | UsuarioNoRegistradoException | AlarmaNoRegistradaException
				| CodigoAlarmaIncorrectoException | SensorDuplicadoException | UsuarioNoAutorizadoException | SensorDesactivadoException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			throw new SensorDesactivadoException();
		}
		//Verificacion
		
	}
	
	@org.junit.Test 
	public void queSePuedaActivarUnaAlarma(){
		
		//Preparación
		
		final Integer DNI = 33456324, DNIConfig= 34135234, ID = 1, codActivacion = 13579, codConfig = 77899, idSensor1 = 1,
				idSensor2 = 2, DNIActivador = 33682349;
		String nombreAdmin = "Armando Barreda", nombreConfig = "Jorge", nombreActivador = "Armando Esteban Quito", nombreAlarma = "ZBT";
		
		Central central = new Central("Central de alarmas");
		Usuario administrador = new Administrador(DNI, nombreAdmin);
		Usuario configurador = new Configurador(DNIConfig, nombreAdmin);
		Usuario activador = new Activador(DNIActivador, nombreActivador);
		Alarma alarmaDeAuto = new Alarma(ID, codActivacion, codConfig, nombreAlarma);
		Sensor sensorUno = new Sensor(idSensor1);
		Sensor sensorDos = new Sensor(idSensor2);
		
		//Ejecución
		
		Boolean seRegistro = false, seRegistroUsuarioUno, seRegistroUsuarioDos, seRegistroSensorUno, seRegistroSensorDos, seActivoAlarma = false;
		try {
			seRegistro = ((Administrador)administrador).agregarAlarma(central, alarmaDeAuto);
			seRegistroSensorUno = ((Administrador)administrador).agregarSensorAUnaAlarma(ID, codConfig, sensorUno, central);
			seRegistroSensorDos = ((Administrador)administrador).agregarSensorAUnaAlarma(ID, codConfig, sensorDos, central);
			seRegistroUsuarioUno = ((Administrador)administrador).agregarUsuario(central, configurador);
			seRegistroUsuarioDos = ((Administrador)administrador).agregarUsuario(central, activador);
			((Administrador)administrador).agregarUsuarioaUnaAlarma(DNIConfig, ID, 77899, central);
			((Configurador)configurador).agregarUsuarioAUnaAlarma(DNIActivador, ID, 77899, central);
			((Configurador)configurador).activarSensor(1, ID, codConfig, central);
			((Configurador)configurador).activarSensor(2, ID, codConfig, central);
			seActivoAlarma = ((Activador)activador).activarDesactivarAlarma(ID, codActivacion, activador, central);
		} catch (AlarmaDuplicadaException | UsuarioRegistradoException | UsuarioNoRegistradoException | AlarmaNoRegistradaException
				| CodigoAlarmaIncorrectoException | SensorDuplicadoException | UsuarioNoAutorizadoException | SensorDesactivadoException
				| CodigoSensorInvalidoException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());

		}
		//Verificacion
		assertTrue(seActivoAlarma);
	}
	
	@org.junit.Test 
	public void queParaUnaAlarmaDeterminadaSePuedaObtenerUnaColeccionOrdenadaDeAcccionesDeTipoConfiguracionOdenadasPorIdDeAccion(){
		
		//Preparación
		
		final Integer DNI = 33456324, DNIConfig= 34135234, ID = 1, codActivacion = 13579, codConfig = 77899, idSensor1 = 1,
				idSensor2 = 2, DNIActivador = 33682349;
		String nombreAdmin = "Armando Barreda", nombreConfig = "Jorge", nombreActivador = "Armando Esteban Quito", nombreAlarma = "ZBT";
		
		Central central = new Central("Central de alarmas");
		Usuario administrador = new Administrador(DNI, nombreAdmin);
		Usuario configurador = new Configurador(DNIConfig, nombreAdmin);
		Usuario activador = new Activador(DNIActivador, nombreActivador);
		Alarma alarmaDeAuto = new Alarma(ID, codActivacion, codConfig, nombreAlarma);
		Sensor sensorUno = new Sensor(idSensor1);
		Sensor sensorDos = new Sensor(idSensor2);
		
		//Ejecución
		
		Boolean seRegistro = false, seRegistroUsuarioUno, seRegistroUsuarioDos, seRegistroSensorUno, seRegistroSensorDos, seActivoAlarma = false;
		try {
			seRegistro = ((Administrador)administrador).agregarAlarma(central, alarmaDeAuto);
			seRegistroSensorUno = ((Administrador)administrador).agregarSensorAUnaAlarma(ID, codConfig, sensorUno, central);
			seRegistroSensorDos = ((Administrador)administrador).agregarSensorAUnaAlarma(ID, codConfig, sensorDos, central);
			seRegistroUsuarioUno = ((Administrador)administrador).agregarUsuario(central, configurador);
			seRegistroUsuarioDos = ((Administrador)administrador).agregarUsuario(central, activador);
			((Administrador)administrador).agregarUsuarioaUnaAlarma(DNIConfig, ID, 77899, central);
			((Configurador)configurador).agregarUsuarioAUnaAlarma(DNIActivador, ID, 77899, central);
			((Configurador)configurador).activarSensor(1, ID, codConfig, central);
			((Configurador)configurador).activarSensor(2, ID, codConfig, central);
			seActivoAlarma = ((Activador)activador).activarDesactivarAlarma(ID, codActivacion, activador, central);
		} catch (AlarmaDuplicadaException | UsuarioRegistradoException | UsuarioNoRegistradoException | AlarmaNoRegistradaException
				| CodigoAlarmaIncorrectoException | SensorDuplicadoException | UsuarioNoAutorizadoException | SensorDesactivadoException
				| CodigoSensorInvalidoException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());

		}
		Set<Accion> accionesConfiguracion = ((Administrador)administrador).obtenerUnaListaDeAccionesOrdenada(alarmaDeAuto, TipoDeAccion.CONFIGURACION);
		List<Accion> listaOrdenada = new ArrayList<>(accionesConfiguracion);
		System.out.println(accionesConfiguracion);
		System.out.println(alarmaDeAuto.getAccionesRealizadas());
		Integer uno = 1;
		Integer dos = 2;
		Integer tres = 3;
		
		//Verificacion
		assertEquals(listaOrdenada.get(0).getId(), uno);
		assertEquals(listaOrdenada.get(1).getId(), dos);
		assertEquals(listaOrdenada.get(2).getId(), tres);
	}
	
	@org.junit.Test 
	public void queSePuedaDesactivarUnaAlarma(){
		
		//Preparación
		
		final Integer DNI = 33456324, DNIConfig= 34135234, ID = 1, codActivacion = 13579, codConfig = 77899, idSensor1 = 1,
				idSensor2 = 2, DNIActivador = 33682349;
		String nombreAdmin = "Armando Barreda", nombreConfig = "Jorge", nombreActivador = "Armando Esteban Quito", nombreAlarma = "ZBT";
		
		Central central = new Central("Central de alarmas");
		Usuario administrador = new Administrador(DNI, nombreAdmin);
		Usuario configurador = new Configurador(DNIConfig, nombreAdmin);
		Usuario activador = new Activador(DNIActivador, nombreActivador);
		Alarma alarmaDeAuto = new Alarma(ID, codActivacion, codConfig, nombreAlarma);
		Sensor sensorUno = new Sensor(idSensor1);
		Sensor sensorDos = new Sensor(idSensor2);
		
		//Ejecución
		
		Boolean seRegistro = false, seRegistroUsuarioUno, seRegistroUsuarioDos, seRegistroSensorUno, seRegistroSensorDos, seActivoAlarma = false;
		try {
			seRegistro = ((Administrador)administrador).agregarAlarma(central, alarmaDeAuto);
			seRegistroSensorUno = ((Administrador)administrador).agregarSensorAUnaAlarma(ID, codConfig, sensorUno, central);
			seRegistroSensorDos = ((Administrador)administrador).agregarSensorAUnaAlarma(ID, codConfig, sensorDos, central);
			seRegistroUsuarioUno = ((Administrador)administrador).agregarUsuario(central, configurador);
			seRegistroUsuarioDos = ((Administrador)administrador).agregarUsuario(central, activador);
			((Administrador)administrador).agregarUsuarioaUnaAlarma(DNIConfig, ID, 77899, central);
			((Configurador)configurador).agregarUsuarioAUnaAlarma(DNIActivador, ID, 77899, central);
			((Configurador)configurador).activarSensor(1, ID, codConfig, central);
			((Configurador)configurador).activarSensor(2, ID, codConfig, central);
			seActivoAlarma = ((Activador)activador).activarDesactivarAlarma(ID, codActivacion, activador, central);
			seActivoAlarma = ((Activador)activador).activarDesactivarAlarma(ID, codActivacion, activador, central);
		} catch (AlarmaDuplicadaException | UsuarioRegistradoException | UsuarioNoRegistradoException | AlarmaNoRegistradaException
				| CodigoAlarmaIncorrectoException | SensorDuplicadoException | UsuarioNoAutorizadoException | SensorDesactivadoException
				| CodigoSensorInvalidoException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());

		}
		
		//Verificacion
		assertFalse(seActivoAlarma);
	}

}
