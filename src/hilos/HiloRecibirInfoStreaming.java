package hilos;

import java.io.IOException;
import java.net.DatagramPacket;

import conexion.ClienteStreaming;

public class HiloRecibirInfoStreaming extends Thread{
	
	
	private ClienteStreaming stream;
	
	public HiloRecibirInfoStreaming(ClienteStreaming s) {
		// TODO Auto-generated constructor stub
		
		stream = s;
	}
	
	@Override
	public void run() {
		while (stream.isEscucharPaquetes()) {
			try {
				byte[] buf = new byte[1000];
				DatagramPacket recibido = new DatagramPacket(buf,buf.length);
				stream.getSock().receive(recibido);
				String info = new String(recibido.getData(),0, recibido.getLength());
				stream.Streaming(info);
				sleep(10);
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
