package interfaz;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class PanelEspera extends JPanel{

	private JLabel banner;
	private JPanel panelConectados;
	private int numConectados;
	private ArrayList<String> nicknames;
	public PanelEspera() {
		numConectados = 0;
		nicknames = new ArrayList<String>();
		setLayout(new BorderLayout());
		setSize(new Dimension(600,700));
		banner = new JLabel();
		ImageIcon bn = new ImageIcon("./docs/imgs/bannerEspera.jpg");
		banner.setIcon(bn);
		this.add(banner, BorderLayout.NORTH);
		panelConectados = new JPanel();	
		panelConectados.setLayout(new GridLayout(5,1));
		panelConectados.setBackground(Color.DARK_GRAY);
		this.add(panelConectados, BorderLayout.CENTER);
		
		
	}
	
	public void conectarCliente (String nickname) {
		if(!containsNick(nickname)) {
		numConectados++;
		auxConectarCliente(nickname);
		nicknames.add(nickname);
		}
			
	}
	public void auxConectarCliente(String nk) {
		JPanel panelaux = new JPanel();
		panelaux.setLayout(new GridLayout(1,3));
		panelaux.setBackground(Color.DARK_GRAY);
		JLabel nickname = new JLabel ("Nickname: '" + nk + "'");
		nickname.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		nickname.setAlignmentY(JLabel.CENTER_ALIGNMENT);
		nickname.setForeground(Color.WHITE);
		nickname.setBackground(Color.DARK_GRAY);
		JLabel pic = new JLabel();
		ImageIcon img = new ImageIcon("./docs/imgs/"+numConectados+".jpg");
		pic.setIcon(img);
		pic.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		pic.setAlignmentY(JLabel.CENTER_ALIGNMENT);
		JLabel estado = new JLabel("ESTADO: ACTIVO");
		estado.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		estado.setAlignmentY(JLabel.CENTER_ALIGNMENT);
		estado.setSize(100, 100);
		estado.setForeground(Color.WHITE);
		estado.setBackground(Color.DARK_GRAY);
		
		panelaux.add(pic);
		panelaux.add(nickname);
		panelaux.add(estado);
		panelConectados.add(panelaux);
		refrescarConectados();
		
	}
	public boolean containsNick(String nickname) {
		boolean r = false;
		for (int i = 0; i < nicknames.size(); i++) {
			if(nicknames.get(i).equals(nickname)) r = true;
		}
		return r;
	}
	public void refrescarConectados() {
		panelConectados.repaint();
	}
	public int getNumConectados() {
		return numConectados;
	}
}
