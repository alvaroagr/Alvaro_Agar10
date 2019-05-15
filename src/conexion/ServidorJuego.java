 package conexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;

import javax.print.attribute.standard.Severity;

import hilos.HiloAgregarJugador;
import hilos.HiloAudioServer;
import hilos.HiloEnviarInfoStreaming;
import hilos.HiloHandlerActivar;
import hilos.HiloJugadorHandler;
import hilos.HiloTimer;
import hilos.HiloTimerEnLinea;
import modelo.Juego;

/**
 * @author Manuel Quintero
 * @author Christian Lopez
 * @author Alvaro Gomez.
 */
public class ServidorJuego {


	/**
	 * Puerto donde se establecerá el servidor.
	 */
	public  final int SERVERPORT = 8000;
	
	public final int SERVERSTREAM = 8100;
	
	public final int SERVERAUDIO = 8099;
	
	public final String DIRECCIONGRUPO = "239.1.2.2";
	
	public final String DIRECCIONAUDIO = "224.0.0.1";
	
	/**
	 * ServerSocker que funciona de servidor
	 */
	private  ServerSocket ss;
	
	private DatagramSocket dSock;
	
	private HiloEnviarInfoStreaming stream;
	
	private HiloAudioServer audio;
	
	
	/**
	 * Arreglo que contiene los hilos de los jugadores activos.
	 */
	
	private  ArrayList<HiloJugadorHandler> ja;
	
	private  ArrayList<Socket> sockets;
	
	private  ArrayList<String> mensajes;
	
	
	/**
	 * Juego del servidor.
	 */
	private  Juego mundo;
	
	
	/**
	 * Atributo que tendrá el valor del socket que acepte el servidor.
	 */
	private  Socket s;
	
	/**
	 * Long que representa el tiempo en milisegundos del tiempo que queda para
	 * agregar jugadores.
	 */
	private  long time;
	
	/**
	 * Long que representa el tiempo en milisegundo del tiempo que queda
	 * para estar en línea.
	 */
	private  long timeJuego;
	
	private  boolean serverConnected;
	
	private  boolean sendMultiCast;
	/**
	 * Main del Servidor
	 * @param args
	 * @throws IOException Excepcion obtenida por culpa de los sockets
	 * @throws InterruptedExceptionExcepcion obtenida por culpa de los hilos
	 */
	public static void main(String[] args) throws IOException, InterruptedException{
		ServidorJuego server = new ServidorJuego();
		server.conectado();
		
	}
	
	public ServidorJuego() throws IOException {
		// TODO Auto-generated constructor stub
	    ss =  new ServerSocket(SERVERPORT);
	    System.out.println("::Servidor escuchando a los posibles clientes::");
	    serverConnected = true;
	    mundo = new Juego();
	    mundo.generarBola();
	    time = 120000;
	    timeJuego = 300000;
	    ja = new ArrayList<>();
	    sockets = new ArrayList<Socket> ();
	    mensajes = new ArrayList<String>();
	    sendMultiCast = false;
	    dSock = new DatagramSocket();
	   
	}
	public void conectado() throws IOException {
	    int i = 0;
	    HiloTimer k= new HiloTimer(this);
	    k.start();
			while(time > 0 && ja.size()<4) {
			s = ss.accept();
			DataOutputStream oos = new DataOutputStream(s.getOutputStream());
			DataInputStream ois = new DataInputStream(s.getInputStream());
			HiloAgregarJugador t =  new HiloAgregarJugador(s,ois, this);
			t.start();		
			System.out.println("El cliente se ha conectado!");	
	    	}
	}
	
	/**
	 * Método que retorna el juego del servidor.
	 * @return El juego del servidor.
	 */
	
	public  Juego getMundo() {
		return mundo;
	}
	
	/**
	 * Método que retorna el ArrayList que contiene los hilos de los jugadores activos.
	 * @return ArrayList que contiene los hilos.
	 */
	public  ArrayList<HiloJugadorHandler> getHilos() {
		return ja;
	}
	/**
	 * Método que retorna un valor long que representa el tiempo en milisegundos que se tiene
	 * para poder adicionar más jugadores
	 * @return long
	 */
	public  long getTime() {
		return time;
	}
	
	/**
	 * Método que reasigna el valor long que representa el tiempo en milisegundos que se tiene
	 * para poder adicionar más jugadores
	 * @param m nuevo valor de la variable time, tipo long.
	 */
	public  void setTime(long m) {
		time = m;
	}
	/**
	 * Método que retorna un valor long que representa el tiempo en milisegundos que se tiene
	 * para poder jugar en línea con los de más jugadores
	 * @return long
	 */
	public  long getTimeJuego() {
		return timeJuego;
	}
	
	/**
	 * Método que reasigna el valor long que representa el tiempo en milisegundos que se tiene
	 * para poder jugar en línea con los de más jugadores
	 * @param m nuevo valor de la variable time, tipo long.
	 */	
	
	public  void setTimeJuego(long m) {
		timeJuego = m;
	}
	public  ServerSocket getSs() {
		return ss;
	}

	public  void setSs(ServerSocket ss) {
		this.ss = ss;
	}

	public  ArrayList<HiloJugadorHandler> getJa() {
		return ja;
	}

	public  void setJa(ArrayList<HiloJugadorHandler> ja) {
		this.ja = ja;
	}

	public ArrayList<Socket> getSockets() {
		return sockets;
	}

	public void setSockets(ArrayList<Socket> sockets) {
		this.sockets = sockets;
	}

	public ArrayList<String> getMensajes() {
		return mensajes;
	}

	public void setMensajes(ArrayList<String> mensajes) {
		this.mensajes = mensajes;
	}

	public  Socket getS() {
		return s;
	}

	public  void setS(Socket s) {
		this.s = s;
	}

	public  void setMundo(Juego mundo) {
		this.mundo = mundo;
	}

	public  boolean isServerConnected() {
		return serverConnected;
	}

	public  void setServerConnected(boolean pServerConnected) {
		serverConnected = pServerConnected;
	}

	public  boolean isSendMultiCast() {
		return sendMultiCast;
	}

	public  void setSendMultiCast(boolean pSendMultiCast) {
		sendMultiCast = pSendMultiCast;
	} 
	
	public void eraseMessages() {
		mensajes = new ArrayList<String>();
	}
	
	public void nuevoMensaje(String coordenadas) {
		mensajes.add(coordenadas);
	}
	
	

	public DatagramSocket getdSock() {
		return dSock;
	}

	public void setdSock(DatagramSocket dSock) {
		this.dSock = dSock;
	}

	public HiloEnviarInfoStreaming getStream() {
		return stream;
	}

	public void setStream(HiloEnviarInfoStreaming stream) {
		this.stream = stream;
	}

	public void iniciarMovimientos() {
		// TODO Auto-generated method stub
		System.out.println("Entro");
		for(int i = 0; i < sockets.size(); i++) {
			Socket soc = sockets.get(i);
		       try {
		    	DataOutputStream out = new DataOutputStream(soc.getOutputStream());
				out.writeUTF(getMundo().informacionJugadoresCompleta());
				out.writeUTF(getMundo().informacionComida());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int i = 0; i < ja.size(); i++) {		
			HiloHandlerActivar act = new HiloHandlerActivar(ja.get(i));
			act.start();
		}
		iniciarAudio();
		iniciarStreaming();
	}
	
	public void iniciarStreaming() {
		stream = new HiloEnviarInfoStreaming(this);
		stream.start();
	}
	
	public void iniciarAudio() {
		audio = new HiloAudioServer(DIRECCIONAUDIO, SERVERAUDIO);
		audio.start();
	}


}
