package hilos;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import conexion.ClienteChat;

public class HiloEnviarMensaje extends Thread {

	private ClienteChat cliente;
	public HiloEnviarMensaje(ClienteChat cliente) {
		// TODO Auto-generated constructor stub
		this.cliente = cliente;
	}
	
	@Override
	public void run() {
		try {
			DataOutputStream out;
			Socket sock;
			
			while (cliente.isClientConnected()) {
				sock = cliente.getSocket();
				out = new DataOutputStream(sock.getOutputStream());
				if(cliente.isEnviarMensaje()) {
					enviarMensaje(out);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void enviarMensaje(DataOutputStream out) {
		ArrayList<String> mensajes = cliente.getMensajesEnviar();
		for(int i = 0; i < mensajes.size(); i++) {
			String mensaje =cliente.getNickname() + ": " +  mensajes.get(i);
			try {
				out.writeUTF(mensaje);
				System.out.println("Cliente: Se envio el mensaje");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		cliente.setEnviarMensaje(false);
		cliente.borrarMensajesEnviados();
		
	}
}
