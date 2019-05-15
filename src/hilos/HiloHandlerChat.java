package hilos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import conexion.ServidorChat;

public class HiloHandlerChat extends Thread{

	private ServidorChat server;
	private Socket sock;
	public HiloHandlerChat(Socket s, ServidorChat ser) {
		// TODO Auto-generated constructor stub
	    sock = s;
	    server= ser;
	}
	
	@Override
	public void run() {
	 try {
		DataInputStream in;
		
		while(server.isChatServerOnline()) {
			in = new DataInputStream(sock.getInputStream());
			String mensajeCliente = in.readUTF();
			server.newMessage(mensajeCliente);
			Thread.sleep(5);
			multiCast();
		}
	 }
	 catch(Exception e) {
		 
	 }
	}
	
	public void multiCast() {
		try {
			DataOutputStream out;
			if(server.isSendMultiCast() && server.getMensajes().size()>0) {
				System.out.println("MultiCast If");
				for (int i = 0; i < server.getSockets().size(); i++) {
					Socket actual = server.getSockets().get(i);
					out = new DataOutputStream(actual.getOutputStream());
					for (int j = 0; j < server.getMensajes().size(); j++) {
					   String mensaje = server.getMensajes().get(j);
					   out.writeUTF(mensaje);
					}
				}
				server.eraseMessage();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
