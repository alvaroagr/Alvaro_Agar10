package conexion;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import hilos.HiloAudioStreaming;
import hilos.HiloRecibirInfoStreaming;
import modelo.Juego;

public class ClienteStreaming {

	
	public final static int SERVERSTREAM = 8100;
	public final static int SERVERMUSIC = 8099;
	
	public final String DIRECCIONGRUPO = "239.1.2.2";
	public final static String DIRECCIONAUDIO= "224.0.0.1";
	
	
	private Juego mundo;
	
	private DatagramPacket recieve;
	
	private MulticastSocket sock;
	
	private MulticastSocket sAudio;
	
	private InetAddress dirGrupo;
	
	private InetAddress dirGrupo2;
	
	private HiloAudioStreaming audio;
	
	private boolean escucharPaquetes;
	
	

	
	private HiloRecibirInfoStreaming h;
	public ClienteStreaming() {
		// TODO Auto-generated constructor stub
		mundo = new Juego();
	     try {
			sock = new MulticastSocket(SERVERSTREAM);
			dirGrupo = InetAddress.getByName(DIRECCIONGRUPO);
			sock.joinGroup(dirGrupo);
			sAudio = new MulticastSocket(SERVERMUSIC);
			dirGrupo = InetAddress.getByName(DIRECCIONAUDIO);
			sAudio.joinGroup(dirGrupo);
			audio = new HiloAudioStreaming(this);
			escucharPaquetes = true;
			escuchar();
		    audio.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public Juego getMundo() {
		return mundo;
	}
	public void setMundo(Juego mundo) {
		this.mundo = mundo;
	}
	public DatagramPacket getRecieve() {
		return recieve;
	}
	public void setRecieve(DatagramPacket recieve) {
		this.recieve = recieve;
	}
	public MulticastSocket getSock() {
		return sock;
	}
	public void setSock(MulticastSocket sock) {
		this.sock = sock;
	}
	public InetAddress getDirGrupo() {
		return dirGrupo;
	}
	public void setDirGrupo(InetAddress dirGrupo) {
		this.dirGrupo = dirGrupo;
	}
	public boolean isEscucharPaquetes() {
		return escucharPaquetes;
	}
	public void setEscucharPaquetes(boolean escucharPaquetes) {
		this.escucharPaquetes = escucharPaquetes;
	}
	
	public void actualizarInfoMundo(String mensaje) {
		mundo.asignarInformacionJugadoresCompleta(mensaje);
	}
	public void actualizarInfoComida(String mensaje) {
		mundo.asignarInformacionComida(mensaje);
	}
	public void Streaming (String mensaje) {
		String[] values = mensaje.split("---");
		boolean change = false;
		String jugadores = "";
		String comida = "";
		String temp = "";
		for (int i = 0; i < values.length; i++) {
			temp += values[i];
		}
		values = temp.split("|");
		
		for (int i = 0; i < values.length; i++) {
			if(values[i].equals("|")) {
			    change = true;
			}
			else {
				if(!change) jugadores += values[i];
				else comida+= values[i];
			}
		}
		actualizarInfoMundo(jugadores);
		actualizarInfoComida(comida);
	}
	
	public void escuchar() {
		try {
			byte[] buf = new byte[1000];
			DatagramPacket recibido = new DatagramPacket(buf,buf.length);
			sock.receive(recibido);
			String info = new String(recibido.getData(),0, recibido.getLength());
			Streaming(info);			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public MulticastSocket getSockAudio() {
		
		return sAudio;
	}
	
}
