package conexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocketFactory;

/**
 * Clase que se encarga de solicitar los servicios de login y registro de
 * usuario al servidor
 * 
 * @author cris6
 *
 */
public class SSLCliente {

	/**
	 * Constante con la ubicación del archivo de las claves
	 */
	public static final String TRUSTTORE_LOCATION = "./mykeystore/keystore.jks";

	/**
	 * Constante para representar el servicio de login
	 */
	public static final String INICIAR_SESION = "1";

	/**
	 * Constante para representar el servicio de agregar un nuevo usuario
	 */
	public static final String AGREGAR_USUARIO = "2";

	/**
	 * Canal por medio del cual se recibe la respuesta al servicio solicitado
	 */
	private DataInputStream dIS;

	/**
	 * Canal por medio del cual se solicita un servicio
	 */
	private DataOutputStream dOS;

	/**
	 * Boolean que representa si es posible que el usuario acceda a la plataforma
	 */
	private boolean validateAccess;

	/**
	 * Cadena utilizada como variable auxiliar en el proceso de login
	 */
	private String cadena;

	public static void main(String[] args) {
		SSLCliente s = new SSLCliente();

	}

	/**
	 * Constructor de la clase SSLCliente, se encarga de inicializar los canales de
	 * comunicación con el servidor
	 */
	public SSLCliente() {
		// TODO Auto-generated constructor stub
		System.out.println("SSLClientSocket Started");

		System.setProperty("javax.net.ssl.trustStore", TRUSTTORE_LOCATION);
		SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
		validateAccess = false;
		cadena = "aaa";

		try {
			Socket socket = sf.createSocket("127.0.0.1", 10000);
			dIS = new DataInputStream(socket.getInputStream());
			dOS = new DataOutputStream(socket.getOutputStream());

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Consultar el valor actual de la cadena
	 * 
	 * @return cadena
	 */
	public String getCadena() {
		return cadena;
	}

	/**
	 * Modificar el valor actual de la cadena
	 * 
	 * @param cadena - Nuevo valor de la cadena
	 */
	public void setCadena(String cadena) {
		this.cadena = cadena;
	}

	/**
	 * Consultar el estado de la accesibilidad del cliente
	 * 
	 * @return validateAccess
	 */
	public boolean isValidateAccess() {
		return validateAccess;
	}

	/**
	 * Modificar el estado de la accesibilidad del cliente
	 * 
	 * @param validateAccess
	 */
	public void setValidateAccess(boolean validateAccess) {
		this.validateAccess = validateAccess;
	}

	/**
	 * Realiza la solicitud de conexión para realizar el login con el servidor y
	 * maneja el proceso de envío y recepción de información
	 * 
	 * @param correo - Correo electrónico del cliente
	 * @param contra - Contraseña del cliente
	 */
	public void loginUsuario(String correo, String contra) {
		try {
			dOS.writeUTF(INICIAR_SESION);
			dOS.writeUTF(correo + ";" + contra);
			// System.out.println(validateAccess);
			cadena = dIS.readUTF();
			// System.out.println(validateAccess);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Realiza la solicitud de conexion para realizar el registro con el servidor y
	 * maneja el proceso de envío y recepción de información
	 * 
	 * @param correo - Correo electronico del nuevo cliente
	 * @param contra - Contraseña del nuevo cliente
	 */
	public void registrarUsuario(String correo, String contra) {
		try {
			dOS.writeUTF(AGREGAR_USUARIO);
			dOS.writeUTF(correo + ";" + contra);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
