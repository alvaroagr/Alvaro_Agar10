package hilos;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import conexion.ServidorJuego;
import interfaz.PanelEspera;
import modelo.Bola;

/**
 * Clase encargada de gestionar el tiempo de espera para iniciar el juego
 * 
 * @author cris6
 *
 */
public class HiloTimer extends Thread {

	/**
	 * Jframe sobre el cual se despliego el panelEspera
	 */
	private JFrame x;
	
	/**
	 * Panel de espera que debe ser actualizado mientras se conectan los jugadores
	 */
	private PanelEspera n;

	private ServidorJuego server;
	/**
	 * Contructor del HiloTimer
	 */
	public HiloTimer(ServidorJuego s) {
		// TODO Auto-generated constructor stub
		server = s;
		x = new JFrame();
		n = new PanelEspera();
		x.add(n);
		x.setSize(600, 702);
		x.setResizable(false);
		x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		x.setVisible(true);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
	long i = server.getTime();
	
	while(i > 0 ) {
		try {
			i = i-1000;
			sleep(1000);
			server.setTime(i);
			this.actualizarPanel();
			if(i == 1000) {
				x.dispose();
				if(server.getHilos().size() <= 1) {
					server.setServerConnected(false);
				}
				else {
					
					server.iniciarMovimientos();
				}
			}
			System.out.println(server.getTime());
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   }
		}

	}

	/**
	 * Actualiza el panel de espera segun se van conectando los jugadores
	 */
	private void actualizarPanel() {

		int i = server.getMundo().getJugadores().size();
		int pan = n.getNumConectados();
		ArrayList<Bola> j = server.getMundo().getJugadores();
			for (int j2 = 0; j2< j.size(); j2++) {
				String nick = j.get(j2).getNick();
				n.conectarCliente(nick);
			}
			
		  n.repaint();
		  n.revalidate();
		  x.repaint();
		  x.revalidate();
		
	}

}
