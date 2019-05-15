package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;

public class PanelEnvioMensaje extends JPanel implements ActionListener {
	
	public final static String ENVIAR = "Enviar";
	public final static String LIMPIAR = "Limpiar";
	
	
	private JTextField txtMensaje;
	private JButton btnEnviarMensaje;
	private JButton btnLimpiar;
	private InterfazChat p;
	
	
	public PanelEnvioMensaje(InterfazChat principal) {
		// TODO Auto-generated constructor stub
		p = principal;
	   setBorder(new TitledBorder(new EtchedBorder(),"Tu mensaje:"));
	   setSize(510, 80);
	   setLayout(new GridLayout(1,2));
	   
	   btnEnviarMensaje = new JButton("Enviar");
	   btnEnviarMensaje.setSize(new Dimension(50,60));
	   btnEnviarMensaje.setAlignmentX(CENTER_ALIGNMENT);
	   btnEnviarMensaje.setAlignmentY(CENTER_ALIGNMENT);
	   btnEnviarMensaje.addActionListener(this);
	   btnEnviarMensaje.setActionCommand(ENVIAR);
	   
	   btnLimpiar = new JButton("Limpiar");
	   btnLimpiar.setSize(new Dimension (50,60));
	   btnLimpiar.setAlignmentX(CENTER_ALIGNMENT);
	   btnLimpiar.setAlignmentY(CENTER_ALIGNMENT);
	   btnLimpiar.addActionListener(this);
	   btnLimpiar.setActionCommand(LIMPIAR);
	   
	   
	   txtMensaje = new JTextField();
	   txtMensaje.setSize(410, 60);
	   txtMensaje.setAlignmentX(CENTER_ALIGNMENT);
	   txtMensaje.setAlignmentY(CENTER_ALIGNMENT);
	   
	   JPanel aux = new JPanel();
	   aux.setSize(new Dimension(100,80));
	   aux.setBorder(new TitledBorder(new EtchedBorder(),""));
	   aux.setLayout(new GridLayout(1,2));
	   aux.add(btnEnviarMensaje);
	   aux.add(btnLimpiar);
	   
	   
	   add(txtMensaje);
	   add(aux);
		
	}
	
	
	public void limpiarText() {
		txtMensaje.setText("");
	}
	public void enviarMensaje() {
		p.enviarMensaje(txtMensaje.getText());	
		limpiarText();
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		// TODO Auto-generated method stub
		System.out.println(a.getActionCommand());
		if(a.getActionCommand().equals(ENVIAR)) {
			enviarMensaje();
		}
		else if(a.getActionCommand().equals(LIMPIAR)) {
			limpiarText();
		}
		
	}


    
	
	
	
}
