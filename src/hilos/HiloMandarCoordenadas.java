package hilos;

import java.io.IOException;

import conexion.ClienteJugador;

public class HiloMandarCoordenadas extends Thread{
	
	private ClienteJugador player;
	private boolean movement;
	public HiloMandarCoordenadas(ClienteJugador j) {
		// TODO Auto-generated constructor stub
		player = j;
		movement = false;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(player.isConectado()) {
			 movimientoJugador();
			 try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
				
	}
		
	
	public void  movimientoJugador() {
		try {
			String coordenadas = player.getJuegoActual().informacionJugador(player.getNickname());
			player.getDos().writeUTF(coordenadas);
			movement = false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ClienteJugador getPlayer() {
		return player;
	}

	public void setPlayer(ClienteJugador player) {
		this.player = player;
	}

	public boolean isMovement() {
		return movement;
	}

	public void setMovement(boolean movement) {
		this.movement = movement;
	}
}
