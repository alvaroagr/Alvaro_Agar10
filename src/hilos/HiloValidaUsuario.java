package hilos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import conexion.SSLServidor;

/**
 * Clase encargada de validar los datos en el ingreso de un usuario a la
 * plataforma
 * 
 * @author cris6
 *
 */
public class HiloValidaUsuario extends Thread {

	/**
	 * Solicitud asociada a la solicitud a responder
	 */
	private Socket socket;

	/**
	 * Canal de entrada de datos
	 */
	private DataInputStream dIS;

	/**
	 * Canal de salida de datos
	 */
	private DataOutputStream dOS;

	/**
	 * Contructor del HiloValidaUsuario
	 * 
	 * @param socket
	 * @param dIS
	 * @param dOS
	 */
	public HiloValidaUsuario(Socket socket, DataInputStream dIS, DataOutputStream dOS) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
		this.dIS = dIS;
		this.dOS = dOS;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			String[] data = dIS.readUTF().split(";");
			String correoElectronico = data[0];
			String contraseña = data[1];
			if (SSLServidor.getDataJugadores().containsKey(correoElectronico)) {
				if (SSLServidor.getDataJugadores().get(correoElectronico).equals(contraseña)) {
					dOS.writeUTF("true");
				} else {
					dOS.writeUTF("false");
				}

			} else {
				dOS.writeUTF("false");

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
