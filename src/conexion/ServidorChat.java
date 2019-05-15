package conexion;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import hilos.HiloAgregarClienteChat;
import hilos.HiloHandlerChat;
import hilos.HiloMultiCast;

public class ServidorChat {
	
	public final static int PORTSERVER = 8050;
	
	private ServerSocket serverSocket;
	private HiloMultiCast hiloMulti;
	private HiloAgregarClienteChat hiloAgregar;
	private ArrayList<Socket> sockets;
	private ArrayList<String> mensajes; 
	private ArrayList<HiloHandlerChat> manejadorActivos;
	private ArrayList<String> nicknames;
	private boolean chatServerOnline;
	private boolean sendMultiCast;
	
	
	
	public static void main(String[] args) {
		ServidorChat s = new ServidorChat();
	}
    public ServidorChat() {
    	try {
			serverSocket = new ServerSocket(PORTSERVER);
			System.out.println(":: Servidor Chat ACTIVO ::");
			chatServerOnline = true;
			manejadorActivos = new ArrayList<HiloHandlerChat>();
			sockets = new ArrayList<Socket>();
			mensajes = new ArrayList<String>();
			nicknames = new ArrayList<String>();
			
			hiloAgregar = new HiloAgregarClienteChat(this);
			hiloAgregar.start();
			
			  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }


	public ServerSocket getServerSocket() {
		return serverSocket;
	}


	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}


	public HiloMultiCast getHiloMulti() {
		return hiloMulti;
	}


	public void setHiloMulti(HiloMultiCast hiloMulti) {
		this.hiloMulti = hiloMulti;
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


	public void setMensajes(ArrayList<String> mensaje) {
		this.mensajes = mensaje;
	}


	public ArrayList<HiloHandlerChat> getManejadorActivos() {
		return manejadorActivos;
	}


	public void setManejadorActivos(ArrayList<HiloHandlerChat> manejadorActivos) {
		this.manejadorActivos = manejadorActivos;
	}


	public boolean isChatServerOnline() {
		return chatServerOnline;
	}


	public void setChatServerOnline(boolean chatServerOnline) {
		this.chatServerOnline = chatServerOnline;
	}


	public boolean isSendMultiCast() {
		return sendMultiCast;
	}


	public void setSendMultiCast(boolean sendMultiCast) {
		this.sendMultiCast = sendMultiCast;
	}
	
	public void eraseMessage() {
		mensajes = new ArrayList<String>();
	}
	public void newMessage(String mensaje) {
		mensajes.add(mensaje);
	}
	public boolean asignarHandler(Socket sock, String mensaje) {
		String[] values = mensaje.split(";");
		String nick = values[0];
		boolean flag = false;
		
		if(nicknames.contains(nick)) {
			flag = true;
		}
		else {
			sockets.add(sock);
			nicknames.add(nick);
			HiloHandlerChat hilo = new HiloHandlerChat(sock, this);
			hilo.start();
		}
		
		return flag;
	}
    
}
