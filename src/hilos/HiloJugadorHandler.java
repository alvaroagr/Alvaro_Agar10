package hilos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import conexion.ServidorJuego;

public class HiloJugadorHandler extends Thread {

	private ServidorJuego server;
	private Socket sock;
	
    public HiloJugadorHandler(ServidorJuego s, Socket sk) {
		// TODO Auto-generated constructor stub
	   server = s;
	   sock =sk;
    }
    
    @Override
    public void run() {
    try {
    	DataInputStream in;
		while(server.isServerConnected()) {
			in = new DataInputStream(sock.getInputStream());
			String mensajeObtenidoCliente = in.readUTF();
			server.getMundo().asignarInformacionJugador(mensajeObtenidoCliente);
			server.getMundo().update();
			envioMensajes(mensajeObtenidoCliente);
			
		}
     
      }
      catch (Exception e) {
    	  e.printStackTrace();
      }
    }
    
    private void envioMensajes(String mensaje) {
		for (int i = 0; i < server.getSockets().size(); i++) {
			Socket actual  = server.getSockets().get(i);
			DataOutputStream out;
			if(!actual.equals(sock)) {
				try {
					out = new DataOutputStream(actual.getOutputStream());
					out.writeUTF(mensaje);
					out.writeUTF(server.getMundo().informacionComida());
					out.flush();
					Thread.sleep(10);
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}
    }
}
