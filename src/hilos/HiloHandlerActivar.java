package hilos;

public class HiloHandlerActivar extends Thread{
	
	private HiloJugadorHandler h;
	
	public HiloHandlerActivar(HiloJugadorHandler x) {
		// TODO Auto-generated constructor stub
		
		h = x;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Thread act = new Thread(h);
		act.start();
	}

}
