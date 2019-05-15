package hilos;

import conexion.ClienteJugador;

public class HiloEscucharServerJuego extends Thread{
	
	private ClienteJugador player;
	
	public HiloEscucharServerJuego(ClienteJugador j) {
		// TODO Auto-generated constructor stub
		player = j;
	}

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(player.isConectado()) {
		try {
			String jugadores = player.getDis().readUTF();
			String comida = player.getDis().readUTF();
			player.getJuegoActual().asignarInformacionJugador(jugadores);
			player.getJuegoActual().asignarInformacionComida(comida);

		}
		catch(Exception e) {
			e.printStackTrace();

		}
				
	}
	}
}
