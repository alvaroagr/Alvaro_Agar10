package interfaz;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.*;

import conexion.ClienteJugador;
import conexion.ClienteStreaming;
import conexion.SSLCliente;
import conexion.SSLServidor;
import jdk.nashorn.internal.scripts.JO;

public class InterfazPrincipalLogin extends JFrame {

	private PanelLogin panelLogin;
	private PanelCrearCuenta panelCrearCuenta;
	private PanelStreaming panelStreaming;
	private SSLCliente sslCliente;
	public static final String TRUSTTORE_LOCATION = "./mykeystore/keystore.jks";
	private InterfazCanvasCliente icc;
	private InterfazChat ichat;
	

	public InterfazPrincipalLogin() {
		sslCliente = new SSLCliente();
		setLayout(new BorderLayout());
		setSize(831, 321);
		setTitle("Login");
		setResizable(false);
		System.setProperty("javax.net.ssl.trustStore", TRUSTTORE_LOCATION);
		panelLogin = new PanelLogin(this);
		panelCrearCuenta = new PanelCrearCuenta(this);
		panelStreaming = new PanelStreaming(this);
		
		
		JPanel aux = new JPanel();
		aux.setSize(450,321);
		aux.setLayout(new BorderLayout());
		aux.add(panelStreaming, BorderLayout.CENTER);
		this.add(aux, BorderLayout.WEST);

		this.add(panelLogin, BorderLayout.NORTH);
		this.add(panelCrearCuenta, BorderLayout.CENTER);
		

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		InterfazPrincipalLogin ipl = new InterfazPrincipalLogin();
		ipl.setVisible(true);
	}

	public void loginUsuario(String correoElectronico, String contraseña) throws IOException, ClassNotFoundException, AWTException, InterruptedException {
		sslCliente.loginUsuario(correoElectronico, contraseña);
		System.out.println(sslCliente.isValidateAccess() + "");
		if (sslCliente.getCadena().equals("true")) {
			JOptionPane.showMessageDialog(null, "Aceptar para iniciar conexion");
			ClienteJugador c = new ClienteJugador(correoElectronico, contraseña);
			icc = new InterfazCanvasCliente();
			try {				
				icc.setVisible(true);
				this.setVisible(false);
				icc.iniciarJugador(c);
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(this, "Ocurrio un error, no existian la cantidad suficiente de jugadores", "ERROR", JOptionPane.ERROR_MESSAGE);
				icc.dispose();
				this.dispose();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Datos ingresados no válidos");
			this.dispose();
		}
		

	}

	public void registrarUsuario(String correo, String nickname, String contraseña, String contraseñaR) {
		if(contraseña.compareTo(contraseñaR) == 0) {
		sslCliente.registrarUsuario(correo, contraseña);
        JOptionPane.showMessageDialog(null, "Se completo el registro del nuevo usuario\nReinicie el programa para implementar los cambios", "REGISTO EXITOSO", JOptionPane.INFORMATION_MESSAGE);
		this.dispose();
		}
		else {
			JOptionPane.showMessageDialog(this, "Las contraseñas no son iguales", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void iniciarStreaming() {
		try {
			icc = new InterfazCanvasCliente();
			ichat = new InterfazChat();
			icc.setVisible(true);
			ichat.setVisible(true);
			this.setVisible(false);
			icc.iniciarStreaming();
			
		} catch (ClassNotFoundException | AWTException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
