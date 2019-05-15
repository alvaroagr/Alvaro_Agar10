package hilos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import conexion.ServidorJuego;

/**
 * Clase encargada de agregar un jugador al juego
 * @author cris6
 *
 */
public class HiloAgregarJugador extends Thread {

	/**
	 * Solicitud asociada a la solicitud a responder
	 */
	private Socket sJugador;
	
	/**
	 * Canal de llegada de datos
	 */
	private DataInputStream in;

	private ServidorJuego server;

	/**
	 * Constructor de la clase HiloAgregarJugador
	 * @param sJugador - Solicitud a responder
	 * @param pIn
	 * @throws IOException
	 */
	
	public HiloAgregarJugador(Socket sJugador, DataInputStream pIn, ServidorJuego ser) throws IOException {
		this.sJugador = sJugador;
		this.in = pIn;
		server = ser;
		
	}

	/**
	 * Consultar la solicitud a responder
	 * @return sJugador
	 */
	public Socket getsJugador() {
		return sJugador;
	}
	
	/**
	 * Modifica la solicitud a responder
	 * @param sJugador - nueva solicitud
	 */
	public void setsJugador(Socket sJugador) {
		this.sJugador = sJugador;
	}	
	/**
	 * Consultar el canal de entrada de datos
	 * @return in
	 */
	public DataInputStream getIn() {
		return in;
	}

	/**
	 * Modificar el canal de entrada de datos
	 * @param in - nuevo canal de entrada
	 */
	public void setIn(DataInputStream in) {
		this.in = in;
	}

	public void run() {
		String mensaje;
		try {
			server.getSockets().add(sJugador);
			mensaje = (String) in.readUTF();
			System.out.println(mensaje);
			server.getMundo().agregarNuevoJugador(mensaje);
			HiloJugadorHandler t = new HiloJugadorHandler(server, sJugador);
			server.getJa().add(t);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
