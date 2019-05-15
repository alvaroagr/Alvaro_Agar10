package interfaz;

import javax.swing.*;

import conexion.ClienteJugador;
import conexion.ClienteStreaming;
import hilos.HiloRefrescoJuego;
import hilos.HiloRefrescoStreaming;
import modelo.Bola;
import modelo.Juego;

import java.awt.*;
import java.io.IOException;
import java.net.UnknownHostException;

public class InterfazCanvasCliente extends JFrame{
	
	private PanelCanvasCliente panelCanvas;
	private Juego mundo;
	private ClienteJugador jugador;
	private ClienteStreaming viewer;
	private int mouseX;
	private int mouseY;
	private HiloRefrescoJuego t;
	private HiloRefrescoStreaming s;
	
	
	
	public InterfazCanvasCliente() throws AWTException, UnknownHostException, IOException, ClassNotFoundException {
		// TODO Auto-generated constructor stub
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension tamanio = tk.getScreenSize();
		this.setPreferredSize(tamanio);
	}
	
	public void iniciarJuego() throws AWTException {
		setMundo(jugador.getJuegoActual());
		panelCanvas = new PanelCanvasCliente(this, jugador.getNickname());
		this.setLayout(new BorderLayout());
		this.add(panelCanvas, BorderLayout.CENTER);	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
	}
	public void iniciarJugador(ClienteJugador n) throws IOException, ClassNotFoundException, InterruptedException, AWTException {
		jugador = n;
		jugador.iniciarJuego();
		iniciarJuego();
		setTitle(jugador.getNickname());
		t = new HiloRefrescoJuego(this);
		t.start();
	}
	public void iniciarStreaming() throws AWTException {
		viewer = new ClienteStreaming();
		setMundo(viewer.getMundo());
		panelCanvas = new PanelCanvasCliente(this, "viewer");
		this.setLayout(new BorderLayout());
		this.add(panelCanvas, BorderLayout.CENTER);	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		
		s = new HiloRefrescoStreaming(this, viewer);
		s.start();
		
	}
	
	public void refrescarInterfazJuego() throws IOException {
		Point p = MouseInfo.getPointerInfo().getLocation();
		SwingUtilities.convertPointFromScreen(p, panelCanvas);
		mouseX =(int) p.getX();
		mouseY = (int) p.getY();
		movimientoJugador();
		refrescarInterfaz();
	}
	public void refrescarInterfaz() {
		panelCanvas.repaint();
		this.repaint();
		this.revalidate();
	}
	
	
	
	public Juego getMundo() {
		return mundo;
	}
	public void setMundo(Juego mundo) {
		this.mundo = mundo; 
	}

	public void movimientoJugador() throws IOException {
		// TODO Auto-generated method stub
			jugador.movimientoJugador(mouseX, mouseY);
			setMundo(jugador.getJuegoActual());
	        panelCanvas.asignarNuevosValores();
	    	panelCanvas.repaint();
			this.repaint();
			this.revalidate();
	}
	public void asignarNuevosValoresCanvas() {
		panelCanvas.asignarNuevosValores();
	}

	public ClienteJugador getJugador() {
		return jugador;
	}

	public void setJugador(ClienteJugador jugador) {
		this.jugador = jugador;
	}

	public int getMouseX() {
		return mouseX;
	}

	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

	public void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}

}
