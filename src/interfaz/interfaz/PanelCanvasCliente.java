package interfaz;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Robot;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import modelo.Bola;

public class PanelCanvasCliente extends JPanel implements MouseMotionListener, MouseListener {

	private InterfazCanvasCliente p;
	private ArrayList<Bola>jugadores;
	private Bola miJugador;
	private String miNickname;
	private ArrayList<Bola> comida;
	private Random r;

	public PanelCanvasCliente(InterfazCanvasCliente principal, String nickname) throws AWTException {
		p = principal;
		jugadores = principal.getMundo().getJugadores();
//		miJugador = principal.getMundo().getJugador(nickname);
		miNickname = nickname;
		comida = principal.getMundo().getBolas();
		setBorder(new TitledBorder(new EtchedBorder(), "EN LÍNEA"));
		setLayout(new FlowLayout());
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension tamanio = tk.getScreenSize();
		setPreferredSize(new Dimension(tamanio));
		addMouseMotionListener(this);
		r = new Random();

		
		
	}

	public void paintComponent(Graphics g) {
	 for (int i = 0; i < jugadores.size(); i++) {
		 Bola jugador = jugadores.get(i);
		 g.setColor(new Color(jugador.getRed(), jugador.getBlue(), jugador.getGreen()));
		 g.fillOval(jugador.getX() - jugador.getR(), jugador.getY() - jugador.getR(), jugador.getR() * 2,
				 jugador.getR() * 2);
		 g.setColor(Color.black);
		 g.drawString(jugador.getNick(), jugador.getX()-jugador.getR()+jugador.getR()/2, jugador.getY());
	 }
		for (int i = 0; i < comida.size(); i++) {
			g.setColor(new Color(comida.get(i).getRed(), comida.get(i).getBlue(), comida.get(i).getGreen()));
			int m = comida.get(i).getX();
			int n = comida.get(i).getY();
			int l = comida.get(i).getR();
			g.fillOval(m - l, n - l, 2 * l, 2 * l);
		}

	}

	public String getNickname() {
		return miNickname;
	}
	
	
	public void refrescar() {
		// TODO Auto-generated method stub
		this.repaint();
		this.revalidate();
	}
	
	public void asignarNuevosValores() {
		jugadores = p.getMundo().getJugadores();
		comida = p.getMundo().getBolas();
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
//		int mx = e.getX();
//		int my = e.getY();
//        p.setMouseX(mx);
//        p.setMouseY(my);
//        try {
//        	System.out.println(miNickname);
//			p.movimientoJugador();
//			
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
        
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
