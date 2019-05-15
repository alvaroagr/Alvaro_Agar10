package hilos;

import java.io.IOException;

import interfaz.InterfazCanvasCliente;

/**
 * Clase encargada de refrescar constantemente el canvas del juego
 * 
 * @author cris6
 *
 */
public class HiloRefrescoJuego extends Thread {

	/**
	 * Canvas que debe ser refrescado constantemente
	 */
	private InterfazCanvasCliente icc;

	/**
	 * Constructor del HiloRefresco
	 * 
	 * @param i - Canvas a refrescar
	 */
	public HiloRefrescoJuego(InterfazCanvasCliente i) {
		icc = i;
	}

	/**
	 * Consultar el canvas a refrescar
	 * 
	 * @return icc
	 */
	public InterfazCanvasCliente getIcc() {
		return icc;
	}

	/**
	 * Modificar el canvas a refrescar
	 * 
	 * @param icc - Nuevo canvas a refrescar
	 */
	public void setIcc(InterfazCanvasCliente icc) {
		this.icc = icc;
	}

	public void run() {
		while (true) {
			try {
				icc.refrescarInterfazJuego();
				sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
