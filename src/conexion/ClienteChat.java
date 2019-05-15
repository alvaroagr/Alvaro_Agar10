package conexion;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import hilos.HiloEnviarMensaje;
import hilos.HiloEscucharChat;

public class ClienteChat {
	
	public static final String HOST = "localhost";
	
	public static final int SERVERPORT = 8050;
	
	private String nickname;
	
	private Socket socket;
	
	private boolean clientConnected;
	
	private boolean enviarMensaje;
	
	private ArrayList<String> mensajesRecibidos;
	
	private ArrayList<String> mensajesEnviar;
	
	private HiloEscucharChat hiloEscucharChat;
	
	private HiloEnviarMensaje hiloEnviarMensaje;
	
	public ClienteChat(String nickname) {
		// TODO Auto-generated constructor stub
	 try {
	   socket = new Socket(HOST,SERVERPORT);
	   clientConnected = true;
	   mensajesRecibidos = new ArrayList<String>();
	   mensajesEnviar = new ArrayList<String>();
	   this.nickname = nickname;
	   conectadoAlChat();
	   bienvenido();
	   hiloEscucharChat = new HiloEscucharChat(this);
	   hiloEscucharChat.start();
	   hiloEnviarMensaje = new HiloEnviarMensaje(this);
	   hiloEnviarMensaje.start();
	   
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public boolean isClientConnected() {
		return clientConnected;
	}

	public void setClientConnected(boolean clientConnected) {
		this.clientConnected = clientConnected;
	}

	public HiloEscucharChat getHiloEscucharChat() {
		return hiloEscucharChat;
	}

	public void setHiloEscucharChat(HiloEscucharChat hiloEscucharChat) {
		this.hiloEscucharChat = hiloEscucharChat;
	}

	public HiloEnviarMensaje getHiloEnviarMensaje() {
		return hiloEnviarMensaje;
	}

	public void setHiloEnviarMensaje(HiloEnviarMensaje hiloEnviarMensaje) {
		this.hiloEnviarMensaje = hiloEnviarMensaje;
	}

	public ArrayList<String> getMensajesRecibidos() {
		return mensajesRecibidos;
	}

	public void setMensajesRecibidos(ArrayList<String> mensajesRecibidos) {
		this.mensajesRecibidos = mensajesRecibidos;
	}
	
	public boolean isEnviarMensaje() {
		return enviarMensaje;
	}

	public void setEnviarMensaje(boolean enviarMensaje) {
		this.enviarMensaje = enviarMensaje;
	}

	public ArrayList<String> getMensajesEnviar() {
		return mensajesEnviar;
	}

	public void setMensajesEnviar(ArrayList<String> mensajesEnviar) {
		this.mensajesEnviar = mensajesEnviar;
	}

	public void recibirMensaje(String mensaje) {
		mensajesRecibidos.add(mensaje);
	}
	public void enviarMensaje(String mensaje) {
		mensajesEnviar.add(mensaje);
		enviarMensaje = true;
	}
	public void borrarMensajesRecibidos() {
		mensajesRecibidos = new ArrayList<String>();
	}
	public void borrarMensajesEnviados() {
	 	mensajesEnviar = new ArrayList<String>();
	}
	
	public void bienvenido() {
		String mensaje = nickname + ": " + "<-- Bienvenido";
		try {
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(mensaje);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void conectadoAlChat() {
		String mensaje = nickname + ": " + "<-- Se ha conectado al chat";
		try {
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(mensaje);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	
}
