package hilos;

import java.io.IOException;

import javax.swing.JOptionPane;

import conexion.ServidorJuego;

/**
 * Clase encargada de gestionar el tiempo de espera para iniciar el juego
 * 
 * @author cris6
 *
 */
public class HiloTimerEnLinea extends Thread {
	private ServidorJuego server;
	public HiloTimerEnLinea(ServidorJuego s) {
		// TODO Auto-generated constructor stub
		server = s;
	}
	
	@Override
	public void run() {
		long i = server.getTimeJuego();
		
		while(i > 0 ) {
			try {
				i = i - 1000;
				sleep(1000);
				server.setTimeJuego(i);
				if(i == 1000) {
									
				  server.setServerConnected(false);
					
				}
				System.out.println(server.getTimeJuego());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//
			}
		}
	}
}
