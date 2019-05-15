package conexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

import hilos.HiloAudioCliente;
import hilos.HiloEscucharServerJuego;
import hilos.HiloMandarCoordenadas;
import modelo.Juego;
/**
 * @author Manuel Quintero
 * @author Christian Lopez
 * @author Alvaro Gomez.
 */
public class ClienteJugador {
		
	/**
	 * Numero del puerto del servidor a conectarse.
	 */
	private static final int SERVERPORT = 8000;
	
	private static final int SERVERMUSIC = 8099;
	
	private static final String HOST = "127.0.0.1";
	
	public final static String DIRECCIONGRUPO = "224.0.0.1";
	/**
	 * Socket del jugador
	 */
	private Socket send;
	
	private MulticastSocket mSock;
	
	private InetAddress dirGrupo;
	/**
	 * Flujo de datos de salida
	 */
	private DataOutputStream dos;
	/**
	 * Flujo de datos de entrada
	 */
	private DataInputStream dis;
	/**
	 * Nickname del jugador
	 */
	private String nickname;
	/**
	 * Password del jugador
	 */
	private String password;
	/**
	 * Juego del jugadorActual
	 */
	private Juego juegoActual;
	
	private boolean conectado;
	
	private HiloMandarCoordenadas hiloSend;
	
	private HiloEscucharServerJuego hiloRecieve;
	
	private HiloAudioCliente hiloAudio;
	
	/**
	 * Constructor del jugador
	 * @param nickname nickname del jugador
	 * @param password  password del jugador
	 * @throws IOException Excepcion creada por el socket
	 */
	public ClienteJugador(String nickname, String password) throws IOException {
		this.nickname = nickname;
		this.password = password;
		send = new Socket(HOST,SERVERPORT);
		mSock = new MulticastSocket(SERVERMUSIC);
		dirGrupo = InetAddress.getByName(DIRECCIONGRUPO);
		mSock.joinGroup(dirGrupo);
		dos = new DataOutputStream(send.getOutputStream());
		dis= new DataInputStream(send.getInputStream());
		conectado = true;
		juegoActual = new Juego();
		hiloSend = new HiloMandarCoordenadas(this);
		hiloRecieve = new HiloEscucharServerJuego(this);
		hiloAudio = new HiloAudioCliente(this);
	}
	/**
	 * Método que retorna el nickname del jugador
	 * @return nickname del jugador
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * Método que asigna un nuevo valor al nickname del jugador.
	 * @param nickname nuevo nickname a asignar.
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * Método que retorna el valor de la password del jugador
	 * @return password del jugador.
	 */
	
	public String getPassword() {
		return password;
	}
	/**
	 * Método que asigna un nuevo valor a la password del jugador
	 * @param password valor nuevo a asignar.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Método que inicial el juego del jugador.
	 * @throws IOException Excepcion generada por los sockets
	 * @throws InterruptedException Excepcion generada por los hilos
	 */
	public void iniciarJuego() throws IOException, InterruptedException{
		dos.writeUTF(nickname);
		String jugadores = dis.readUTF();
		String comida = dis.readUTF();
		juegoActual.asignarInformacionJugadoresCompleta(jugadores);
		juegoActual.asignarInformacionComida(comida);
		hiloSend.start();
		hiloRecieve.start();	
		hiloAudio.start();
	} 
	
	/**
	 * Método que se encarga de asignar los nuevos valores dentro del juego del jugador
	 * cuando este se mueve y de enviar la nueva información al servidor para que se actualice
	 * @param mx valor x del mouse
	 * @param my valor y del mouse
	 * @throws IOException Excepcion generada por los sockets
	 */
	public void  movimientoJugador (int mx, int my){
			juegoActual.getJugador(nickname).movimientoPelota(mx, my);
	        hiloSend.setMovement(true);
	}
	public DataOutputStream getDos() {
		return dos;
	}
	public void setDos(DataOutputStream dos) {
		this.dos = dos;
	}
	public DataInputStream getDis() {
		return dis;
	}
	public void setDis(DataInputStream dis) {
		this.dis = dis;
	}
	/**
	 * Método que se encarga de retornar el juego del jugador.
	 * @return Juego del jugador
	 */
	
	public Juego getJuegoActual() {
		return juegoActual;
	}
	/**
	 * Método que se encarga de asignar un nuevo juego al jugador actual.
	 * @param mundo nuevo juego a asignar.
	 */
	public void setJuegoActual(Juego mundo) {
		this.juegoActual = mundo;
	}
	
	public Socket getSend() {
		return send;
	}
	public void setSend(Socket send) {
		this.send = send;
	}
	public boolean isConectado() {
		return conectado;
	}
	public void setConectado(boolean conectado) {
		this.conectado = conectado;
	}
	public HiloMandarCoordenadas getHiloSend() {
		return hiloSend;
	}
	public void setHiloSend(HiloMandarCoordenadas hiloSend) {
		this.hiloSend = hiloSend;
	}
	public HiloEscucharServerJuego getHiloRecieve() {
		return hiloRecieve;
	}
	public void setHiloRecieve(HiloEscucharServerJuego hiloRecieve) {
		this.hiloRecieve = hiloRecieve;
	}
	public MulticastSocket getmSock() {
		return mSock;
	}
	public void setmSock(MulticastSocket mSock) {
		this.mSock = mSock;
	}
	
	
	
	
	
	
	
	
	

}
