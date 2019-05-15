package hilos;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import conexion.ServidorChat;

public class HiloAgregarClienteChat extends Thread{

	private ServidorChat server;
	
	public HiloAgregarClienteChat(ServidorChat s) {
		// TODO Auto-generated constructor stub
	   server  = s;
	}
	
	@Override
	public void run() {
			
			try {
				
				DataInputStream in;
				
				while (server.isChatServerOnline()) {
					Socket recieved = server.getServerSocket().accept();
					System.out.println(":: Se conecto un cliente ::");
					in = new DataInputStream (recieved.getInputStream());
					String mensaje = in.readUTF();
					server.asignarHandler(recieved, mensaje);
					server.newMessage(mensaje);
					Thread.sleep(5);
					server.setSendMultiCast(true);
				}
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	}
}
