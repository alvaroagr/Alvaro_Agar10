package interfaz;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class PanelLogin extends JPanel implements ActionListener {

	public final static String INICIAR_SESION = "Iniciar sesion";

	private JLabel lblAgarIo;
	private JLabel lblCorreoElectronico;
	private JLabel lblContraseña;

	private JTextField txtCorreoElectronico;
	private JPasswordField txtContraseña;

	private JButton btnIniciarSesion;

	private InterfazPrincipalLogin panelPrincipal;

	public PanelLogin(InterfazPrincipalLogin panelPrincipal) {

		setLayout(new GridLayout(1, 4));
		setSize(1650, 150);

		this.panelPrincipal = panelPrincipal;
		this.setBorder(new TitledBorder(new EtchedBorder(),"¡INGRESA AL JUEGO!"));

		lblAgarIo = new JLabel("LOGIN");
		lblAgarIo.setAlignmentX(CENTER_ALIGNMENT);
		lblAgarIo.setAlignmentY(CENTER_ALIGNMENT);
		lblAgarIo.setSize(new Dimension(150,150));
		lblCorreoElectronico = new JLabel("Correo electrónico:");
		lblContraseña = new JLabel("Contraseña:");

		txtCorreoElectronico = new JTextField();
		txtContraseña = new JPasswordField();

		btnIniciarSesion = new JButton("Iniciar sesión");
		btnIniciarSesion.addActionListener(this);
		btnIniciarSesion.setActionCommand(INICIAR_SESION);

		JPanel panelAux1 = new JPanel();
		panelAux1.setLayout(new GridLayout(2, 1));
		panelAux1.add(lblCorreoElectronico);
		panelAux1.add(txtCorreoElectronico);

		JPanel panelAux2 = new JPanel();
		panelAux2.setLayout(new GridLayout(2, 1));
		panelAux2.add(lblContraseña);
		panelAux2.add(txtContraseña);

		JPanel panelAux3 = new JPanel();
		panelAux3.setLayout(new GridLayout(2, 1));
		panelAux3.add(new JLabel());
		panelAux3.add(btnIniciarSesion);

		this.add(lblAgarIo);
		this.add(panelAux1);
		this.add(panelAux2);
		this.add(panelAux3);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if (comando.equals(INICIAR_SESION)) {
			try {
				panelPrincipal.loginUsuario(txtCorreoElectronico.getText(), txtContraseña.getText());
			} catch (ClassNotFoundException | IOException | AWTException | InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

}
