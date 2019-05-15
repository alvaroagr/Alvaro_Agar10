package hilos;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import conexion.ServidorJuego;

public class HiloEnviarInfoStreaming extends Thread {
	
	ServidorJuego server;
	
	public HiloEnviarInfoStreaming(ServidorJuego s) {
		// TODO Auto-generated constructor stub
		server = s;
	}
	
	@Override
	public void run() {
		while (server.isServerConnected()) {
			try {
				DatagramSocket dSock = server.getdSock();
				DatagramPacket dPacket = null;
				InetAddress dirGrupo = InetAddress.getByName(server.DIRECCIONGRUPO);
				String mensaje = server.getMundo().informacionJugadoresCompleta() + "---|" + server.getMundo().informacionComida();
				byte[] buf = mensaje.getBytes();
				dPacket = new DatagramPacket(buf, buf.length,dirGrupo,server.SERVERSTREAM);
				dSock.send(dPacket);	
				sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
	}
}
