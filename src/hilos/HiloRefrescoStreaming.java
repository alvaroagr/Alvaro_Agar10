package hilos;

import java.io.IOException;

import conexion.ClienteStreaming;
import interfaz.InterfazCanvasCliente;

public class HiloRefrescoStreaming extends Thread {
	
	/**
	 * Canvas que debe ser refrescado constantemente
	 */
	private InterfazCanvasCliente icc;

	private ClienteStreaming c;
	/**
	 * Constructor del HiloRefresco
	 * 
	 * @param i - Canvas a refrescar
	 */
	public HiloRefrescoStreaming(InterfazCanvasCliente i, ClienteStreaming c) {
		icc = i;
		this.c = c;
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
				c.escuchar();
				icc.setMundo(c.getMundo());
				icc.asignarNuevosValoresCanvas();
				icc.refrescarInterfaz();
				sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}


