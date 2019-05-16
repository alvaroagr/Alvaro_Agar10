package conexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import hilos.HiloDespliegueAppWeb;

public class ServidorScore {
	public final static int PORT_BD = 6500;
	public final static int PORT_WEB_SERVICE = 7000;
	public final static int PORT_SSL_SERVICE = 10000;
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
	
	public String auntenticar(String usr, String pass) {
//		String output = "";
		String msg = usr+";"+pass;
		String usrs = "";
		try {
			usrs = retornarInfoUSR();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(usrs.contains(usr)) {
			if(usrs.contains(msg)) {
				System.out.println("Verifique contraseña yay");
				return "true";
			} else {
				System.out.println("Verifique contraseña nay");
				return "false";
			}
		} else {
			System.out.println("john doe");
				return "not";
		}
//		try {
//			Socket s = new Socket(LOCAL_HOST, PORT_SSL_SERVICE);
//			DataInputStream in = new DataInputStream(s.getInputStream());
//			DataOutputStream out = new DataOutputStream(s.getOutputStream());
//			out.writeUTF(msg);
//			String returnMessage = in.readUTF();
//			output = returnMessage;
//			s.close();
//			System.out.println(returnMessage);
//		} catch (Exception e) {
//			System.out.println("Exploto ServidorBD");
//		}SSL DIDN'T WORK
		
		
		
		
//		return output;
	}
	
	//ESTE METODO ES MUY PROBABLE QUE SEA ALTAMENTE INSEGURO, PERO SSL NO FUNCIONA EN MI PC, POR LO QUE RECURRO A ESTO
	//A FUTURO PODRIA ARREGLARSE, PERO NI IDEA
	public String retornarInfoUSR() throws IOException {
		String mensajeDevolver = "";
		try {
			File texto = new File("data/users.txt");
			Scanner s = new Scanner(texto);
			while (s.hasNextLine()) {
				mensajeDevolver += s.nextLine()+ "\n";
			}
//			out.writeUTF(mensajeDevolver);
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error en guardado de TXT BD");
		}
		return mensajeDevolver;
		
		
	}

}
