package interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class PanelCrearCuenta extends JPanel implements ActionListener {

	public final static String REGISTRATE = "Registrate";
	private JLabel lblCorreoElectronico;
	private JLabel lblNickname;
	private JLabel lblContraseña;
	private JLabel lblConfirmarContraseña;

	private JTextField txtCorreoElectronico;
	private JTextField txtNickname;
	private JTextField txtContraseña;
	private JTextField txtConfirmarContraseña;

	private JButton btnRegistrar;

	private JPanel panel;

	private InterfazPrincipalLogin panelPrincipal;

	public PanelCrearCuenta(InterfazPrincipalLogin panelPrincipal) {

		setLayout(new BorderLayout());
		setSize(500, 700);

		this.panelPrincipal = panelPrincipal;
		this.setBorder(new TitledBorder(new EtchedBorder(),"Crear una cuenta"));
		lblCorreoElectronico = new JLabel("Correo electrónico");
		lblNickname = new JLabel("Nickname");
		lblContraseña = new JLabel("Contraseña");
		lblConfirmarContraseña = new JLabel("Confirmar contraseña");

		txtCorreoElectronico = new JTextField();
		txtNickname = new JTextField();
		txtContraseña = new JTextField();
		txtConfirmarContraseña = new JTextField();

		btnRegistrar = new JButton("Registrate");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setActionCommand(REGISTRATE);

		panel = new JPanel();
		panel.setLayout(new GridLayout(8, 1));
		panel.add(lblCorreoElectronico);
		panel.add(txtCorreoElectronico);

		panel.add(lblNickname);
		panel.add(txtNickname);

		panel.add(lblContraseña);
		panel.add(txtContraseña);

		panel.add(lblConfirmarContraseña);
		panel.add(txtConfirmarContraseña);
		this.add(panel, BorderLayout.CENTER);
		this.add(btnRegistrar, BorderLayout.SOUTH);

		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if (comando.equals(REGISTRATE)) {
			panelPrincipal.registrarUsuario(txtCorreoElectronico.getText(), txtNickname.getText(),
					txtContraseña.getText(), txtConfirmarContraseña.getText());
		}

	}
}
