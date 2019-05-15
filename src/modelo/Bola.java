package modelo;

import java.io.Serializable;
/**
 * @author Manuel Quintero
 * @author Christian Lopez
 * @author Alvaro Gomez.
 */
public class Bola implements Serializable {

	/**
	 * Número máximo de pixeles que se mueve la bola en un movimiento.
	 */
	private final static int MAXVEL = 10;
	/**
	 * Posición en el eje x de la bola. Va desde 0 hasta el ancho de la ventana de
	 * juego.
	 */
	private int x;
	/**
	 * Posición en el eje y de la bola. Va desde 0 hasta el ancho de la ventana de
	 * juego.
	 */
	private int y;
	/**
	 * Radio de la bola.
	 */
	private int r;

	/**
	 * Valor de rojo que se usa para definir el color de la Bola. Esta entre 0 y
	 * 255.
	 */
	private int red;
	/**
	 * Valor de azul que se usa para definir el color de la Bola. Esta entre 0 y
	 * 255.
	 */
	private int blue;
	/**
	 * Valor de verde que se usa para definir el color de la Bola. Esta entre 0 y
	 * 255.
	 */
	private int green;

	/**
	 * Nombre del jugador que utiliza la bola. Si no tiene nombre, esta bola es
	 * comida.
	 */
	private String nick;

	/**
	 * Retorna el valor de rojo del color de la bola.
	 * 
	 * @return numero entre 0 y 255.
	 */
	public int getRed() {
		return red;
	}

	/**
	 * Cambia el valor de rojo del color de la bola.
	 * @param red
	 */
	public void setRed(int red) {
		this.red = red;
	}

	/**
	 * Retorna el valor de azul del color de la bola.
	 * 
	 * @return numero entre 0 y 255.
	 */
	public int getBlue() {
		return blue;
	}

	/**
	 * Cambia el valor de azul del color de la bola.
	 * @param blue
	 */
	public void setBlue(int blue) {
		this.blue = blue;
	}

	/**
	 * Retorna el valor de verde del color de la bola.
	 * 
	 * @return numero entre 0 y 255.
	 */
	public int getGreen() {
		return green;
	}

	/**
	 * Cambia el valor de verde del color de la bola.
	 * @param green
	 */
	public void setGreen(int green) {
		this.green = green;
	}

	/**
	 * Constructor para la clase bola.
	 * 
	 * @param x posicion inicial en el eje x.
	 * @param y posicion inicial en el eje y.
	 * @param r radio de la bola.
	 */
	public Bola(int x, int y, int r) {
		this.x = x;
		this.y = y;
		this.r = r;
		nick = "";
	}

	/**
	 * Constructor que utiliza el metodo actualizarInformacion para recibir un
	 * mensaje .
	 * 
	 * @param mensaje
	 */
	public Bola(String mensaje) {
		actualizarInformacion(mensaje);
	}

	/**
	 * Retorna la posicion en el eje x de la bola.
	 * @return x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Cambia la posicion en x
	 * @param x nueva posicion en el eje x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Retorna la posicion en el eje y de la bola.
	 * @return y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Cambia la posicion en y
	 * @param y nueva posicion en el eje y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Retorna el radio de la bola
	 * @return r
	 */
	public int getR() {
		return r;
	}

	/**
	 * Cambia el radio de la bola
	 * @param r nuevo radio de la bola
	 */
	public void setR(int r) {
		this.r = r;
	}

	/**
	 * Retorna el nombre del usuario comandando la bola
	 * @return nick
	 */
	public String getNick() {
		return nick;
	}

	/**
	 * Cambia el nombre de usuario que maneja la bola
	 * @param nick nueva cadena de usuario.
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}

	/**
	 * Cambia la posicion de la bola de acuerdo a la posicion del Mouse en la
	 * interfaz.
	 * 
	 * @param mx Posicion en el eje x donde esta el mouse.
	 * @param my Posicion en el eje y donde esta el mouse.
	 */
	public void movimientoPelota(int mx, int my) {
//		int r1 = this.getR();
//	    int x1 = this.getX() + (r/2);
//	    int y1 = this.getY() + (r/2);
//	    int sumx = 0;
//	    int sumy = 0;
//	    sumx = mx - x1;
//	    sumy = my - y1;
//	    x = x1+sumx +10;
//	    y = y1+sumy +10;   
		double nx = mx - x;
		double ny = my - y;
		double ang = Math.atan2(ny, nx);
		int npx = (int) (Math.cos(ang) * MAXVEL);
		int npy = (int) (Math.sin(ang) * MAXVEL);
		x += npx;
		y += npy;
	}

	/**
	 * Recibe un mensaje y lo usa para actualizar toda la información de la bola.
	 * 
	 * @param mensaje Para que funcione debe incluir nombre, coordenada x,
	 *                coordenada y, radio y valores RGB.
	 */
	public void actualizarInformacion(String mensaje) {
		
		String value[] = mensaje.split(",");
		if (value.length == 1 || value.length < 7) {
			nick = "";
			x = 10;
			y = 10;
			r = 5;
			red = 10;
			blue = 10;
			green = 10;
		} 
		else {
			nick = value[0];
			x = Integer.parseInt(value[1]);
			y = Integer.parseInt(value[2]);
			r = Integer.parseInt(value[3]);
			red = Integer.parseInt(value[4]);
			blue = Integer.parseInt(value[5]);
			green = Integer.parseInt(value[6]);
		}
	}

	/**
	 * Calcula el area de la bola a traves de la ecuacion pi*r^2
	 * 
	 * @return area de la bola
	 */
	public double getA() {
		return Math.PI * r * r;
	}

	/**
	 * Metodo para determinar si esta bola puede comer otra bola en el campo de
	 * juego.<br>
	 * Primero determina cual es la distancia que hay entre el centro de esta bola y
	 * de la bola que quiere saber si puede comer.<br>
	 * Si dicha distancia es menor a la suma de los radios de ambas bolas (es decir,
	 * estas se intersecan) y el area de esta bola es mayor al area de la que quiere
	 * saber si puede comer, entonces:<br>
	 * Cambia el radio de esta bola al sacar la raiz cuadrada de: la suma de las
	 * areas de ambos circulos dividida por pi. A esto se le suma 2 unidades, para
	 * asegurarse que el radio aumente.<br>
	 * De lo contrario, no se hace nada.
	 * 
	 * @param b Bola que se quiere saber si se puede comer.
	 * @return true si esta bola puede comerse a b, false si esta bola no puede
	 *         comerse a b
	 */
	public boolean eats(Bola b) {

		int dx = Math.abs(x - b.getX());
		int dy = Math.abs(y - b.getY());
		int d = (int) Math.sqrt(dx * dx + dy * dy);
		if (d < r + b.getR() && getA() > b.getA()) {
			double temp = getA() + b.getA();
			r = (int) (Math.sqrt(temp / Math.PI) + 2.5);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Metodo para representar la bola en un String.
	 * 
	 * @return mensaje Incluye el nombre, posicion x, posicion y, radio y valores
	 *         RGB.
	 */
	public String toString() {
		String mensaje = nick + "," + x + "," + y + "," + r + "," + red + "," + +blue + "," + green;
		return mensaje;
	}
}
