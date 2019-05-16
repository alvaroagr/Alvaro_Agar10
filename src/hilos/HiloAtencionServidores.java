package hilos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import conexion.ServidorBD;

public class HiloAtencionServidores extends Thread {
	
private ServidorBD server;
	
	public HiloAtencionServidores(ServidorBD server) {
		
		this.server = server;
	}
	
	public void run() {
		
		while(server.isAlive()) {
			try {
				Socket socket;
				socket = server.getSocketServer().accept();
				System.out.println("CHEQUEO : Se ha establecido una conexion con el Servidor de BD");
				DataInputStream in = new DataInputStream(socket.getInputStream());
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				String mensaje = in.readUTF();
				String[] componentes = mensaje.split(";");
				if (componentes[0].compareToIgnoreCase("GUARDAR")==0) {
					server.guardarEnBaseDeDatos(componentes[1]);
					out.writeUTF("GUARDADO");
				}
				else {
					server.retornarInfoBD(out);
				}
				
				socket.close();
			} catch (IOException e) {
				System.out.println("Exception in Thread HiloAtencionServidores");

			}
			
		}
		
	}

}
