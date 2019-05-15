package conexion;

import java.io.IOException;
import java.net.ServerSocket;

import hilos.HiloDespliegueAppWeb;

public class ServidorScore {
	private boolean webService;
	public final static int PORT_WEB_SERVICE = 7000;
	private HiloDespliegueAppWeb hilo;
	private ServerSocket serverSocket;
	
	public ServidorScore() throws InterruptedException, IOException{
		webService = true;
		try {
			serverSocket = new ServerSocket(PORT_WEB_SERVICE);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		hilo = new HiloDespliegueAppWeb(this);
		hilo.start();
	}
	
	public ServerSocket getServerSocket() {
		return serverSocket;
	}
	
	public static void main(String[] args) {
		
	}
	
	public boolean isWebServiceOn() {
		return webService;
	}
	
	public String askForData(String string) {
		return null;
	}

}
