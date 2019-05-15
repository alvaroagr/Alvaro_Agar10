package hilos;

import conexion.ClienteChat;
import interfaz.InterfazChat;

public class HiloRecibirMensajesInterfaz extends Thread {

	
	private ClienteChat cliente;
	private InterfazChat ic;
	
	public HiloRecibirMensajesInterfaz(InterfazChat i, ClienteChat c) {
		// TODO Auto-generated constructor stub
		cliente = c;
		ic = i;
	}
	
	@Override
	public void run() {
		
		try {
			while(cliente.isClientConnected()) {
				if(cliente.getMensajesRecibidos().size() > 0) {
					for (int i = 0; i < cliente.getMensajesRecibidos().size(); i++) {
						String mensaje = cliente.getMensajesRecibidos().get(i);
						System.out.println("Interfaz - " + mensaje);
						ic.recibirMensajes(mensaje);
					}
					
					cliente.borrarMensajesRecibidos();
					ic.repaint();
				}
				Thread.sleep(5);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
