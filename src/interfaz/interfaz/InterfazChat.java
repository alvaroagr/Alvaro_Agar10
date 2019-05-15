package interfaz;

import javax.swing.*;

import conexion.ClienteChat;
import hilos.HiloRecibirMensajesInterfaz;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InterfazChat extends JFrame {
	
	private PanelEnvioMensaje enviar;
	private PanelChat chat;
	private JLabel banner;
	private ClienteChat cliente;
	
	
	public InterfazChat() {
		// TODO Auto-generated constructor stub	
		String nickname = "";
		while(nickname.equals("")|| nickname.equals(" ") || nickname.equals(":") || nickname.equals(": ")){
		nickname = JOptionPane.showInputDialog(this, "Ingrese su nickname para el chat", "Nickname", JOptionPane.OK_CANCEL_OPTION);
		}
		setLayout(new BorderLayout());
		setTitle("Chat");
		setSize(new Dimension(510,480));
		setResizable(false);
		enviar = new PanelEnvioMensaje(this);
		chat = new PanelChat(this);
		banner = new JLabel();
		ImageIcon img = new ImageIcon("./docs/imgs/bannerChat.jpg");
		banner.setIcon(img);
		
		add(banner, BorderLayout.NORTH);
		add(chat, BorderLayout.CENTER);
		add(enviar,BorderLayout.SOUTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		cliente = new ClienteChat(nickname);
		HiloRecibirMensajesInterfaz t = new HiloRecibirMensajesInterfaz(this, cliente);
		t.start();
	}

	
	public void enviarMensaje(String mensaje) {
		cliente.enviarMensaje(mensaje);
	}
	public void recibirMensajes(String mensaje) {
		chat.nuevoMensaje(mensaje);
	}

}
