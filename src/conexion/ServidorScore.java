package conexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import hilos.HiloDespliegueAppWeb;

public class ServidorScore {
	public final static int PORT_BD = 6500;
	public final static int PORT_WEB_SERVICE = 7000;
	public final static String LOCAL_HOST = "localhost";
	
	private boolean webService;
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
	
	public static void main(String[] args) throws InterruptedException, IOException {
		ServidorScore s = new ServidorScore();
	}
	
	public boolean isWebServiceOn() {
		return webService;
	}
	
	public String askForData(String username) {
		String output = "";
		try {
			Socket s = new Socket(LOCAL_HOST, PORT_BD);
			DataInputStream in = new DataInputStream(s.getInputStream());
			DataOutputStream out = new DataOutputStream(s.getOutputStream());
			String message = "ASK;"+username;
			out.writeUTF(message);
			String returnMessage = in.readUTF();
			output = returnMessage;
			s.close();
		} catch (Exception e) {
			System.out.println("Exploto ServidorBD");
		}
		return output;
	}

}
