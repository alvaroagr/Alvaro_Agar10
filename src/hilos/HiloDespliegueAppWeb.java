package hilos;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import conexion.ServidorScore;

public class HiloDespliegueAppWeb extends Thread {
	
	public ServidorScore server;

	public HiloDespliegueAppWeb(ServidorScore server) {

		this.server = server;
	}

	public void run() {
		
		while (server.isWebServiceOn()) {
			System.out.println(":::Web Server Started:::");
			ServerSocket serverSocket = server.getServerSocket();
			try {
				Socket cliente = serverSocket.accept();
				HiloClientHandler hilo = new HiloClientHandler(cliente, server);
				hilo.start();

			} catch (IOException e) {
				System.out.println("Exploto HiloDespliegueAppWeb");
			}

		}

	}
	
}
