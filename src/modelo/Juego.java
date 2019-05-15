package modelo;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
/**
 * @author Manuel Quintero
 * @author Christian Lopez
 * @author Alvaro Gomez.
 */
public class Juego implements Serializable {

	/**
	 * Lista de bolas controladas por jugadores (y que se mueven).
	 */
	private ArrayList<Bola> jugadores;

	/**
	 * Directorio que tiene a cada jugador asociado con su número en la lista.
	 */
	private HashMap<String, Integer> directorioJugadores;

	/**
	 * Lista de bolas que funcionan como comida
	 */
	private ArrayList<Bola> bolas;

	/**
	 * Constructor del juego. Inicializa las listas de jugadores y bolas, asi
	 */
	public Juego() {
		jugadores = new ArrayList<Bola>();
		directorioJugadores = new HashMap<String, Integer>();
		bolas = new ArrayList<Bola>();
	}

	/**
	 * Retorna la lista de jugadores
	 * @return jugadores
	 */
	public ArrayList<Bola> getJugadores() {
		return jugadores;
	}

	/**
	 * Modifica la lista de jugadores con otra lista.
	 * @param jugadores Nueva lista de jugadores
	 */
	public void setJugadores(ArrayList<Bola> jugadores) {
		this.jugadores = jugadores;
	}

	/**
	 * Agrega un nuevo jugador al juego, en una posicion aleatoria y con radio 15.
	 * @param nickname Nombre del jugador
	 */
	public void agregarNuevoJugador(String nickname) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension tamanio = tk.getScreenSize();
		Random n = new Random();
		Bola jugador = new Bola(n.nextInt(tamanio.width), n.nextInt(tamanio.height), 15);
		int rd = n.nextInt(255);
		int ble = n.nextInt(255);
		int grn = n.nextInt(255);
		jugador.setRed(rd);
		jugador.setBlue(ble);
		jugador.setGreen(grn);
		jugador.setNick(nickname);
		int num = jugadores.size();
		jugadores.add(jugador);
		directorioJugadores.put(nickname, num);
	}

	/**
	 * Retorna un jugador con el nombre dado
	 * @param nickname nombre del jugador que se quiere retornar
	 * @return Jugador cuyo nombre coincida con el ingresado.
	 */
	public Bola getJugador(String nickname) {

		int num = directorioJugadores.get(nickname);
		return jugadores.get(num);
	}

	/**
	 * Retorna la lista de bolas para comer en el juego.
	 * @return bolas
	 */
	public ArrayList<Bola> getBolas() {
		return bolas;
	}

	/**
	 * Cambia la lista de bolas para comer por otra lista de bolas.
	 * @param bolas
	 */
	public void setBolas(ArrayList<Bola> bolas) {
		this.bolas = bolas;
	}

	/**
	 * Metodo usado para generar 50 bolas de comida en posiciones aleatorias para el juego.
	 */
	public void generarBola() {
		Random r = new Random();
		for (int i = 0; i < 50; i++) {
			int red = r.nextInt(255);
			int blue = r.nextInt(255);
			int green = r.nextInt(255);
			Bola nueva = new Bola(r.nextInt(1000), r.nextInt(1000), 5);
			nueva.setRed(red);
			nueva.setBlue(blue);
			nueva.setGreen(green);
			bolas.add(nueva);
		}
	}

	/**
	 * Verifica si un jugador puede comer alguna bola. Si ese es el caso, esa bola se elimina.
	 */
	public void update() {
		for (int j = 0; j < jugadores.size(); j++) {
			for (int i = bolas.size(); i > 0; i--) {
				if (jugadores.get(j).eats(bolas.get(i - 1))) {
					bolas.remove(i - 1);
				}
			}
//			for(int i=jugadores.size(); i>0; i--) {
//				if(jugadores.get(j) != jugadores.get(i-1)) {
//					if (jugadores.get(j).eats(jugadores.get(i - 1))) {
//						jugadores.remove(i - 1);
//					}
//				}
//			}
		}
	}

	/**
	 * Retorna la informacion del jugador que coincida con el nombre ingresado
	 * @param nickname nombre de usuario del que se quiere informacion
	 * @return mensaje - string de la bola que representa a dicho usuario en el juego.
	 */
	public String informacionJugadores(String nickname) {
		String mensaje = "";
		int k = directorioJugadores.get(nickname);
		for (int i = 0; i < jugadores.size(); i++) {
			if (i != k)
				mensaje += jugadores.get(i).toString() + "\n";
		}

		return mensaje;

	}

	/**
	 * Retorna un string con la informacion de todos los jugadores al ser invocado
	 * @return mensaje
	 */
	public String informacionJugadoresCompleta() {
		String mensaje = "";
		for (int i = 0; i < jugadores.size(); i++) {
			mensaje += jugadores.get(i).toString() + "\n";

		}
		return mensaje;

	}

	/**
	 * Retorna información del jugador con el nombre ingresado
	 * @param nickname
	 * @return toString de la bola que maneja el usuario ingresado.
	 */
	public String informacionJugador(String nickname) {
		int n = directorioJugadores.get(nickname);
		return jugadores.get(n).toString();
	}

	/**
	 * Retorna la información de todas las bolas de comida en el juego.
	 * @return mensaje
	 */
	public String informacionComida() {
		update();
		String mensaje = "";
		for (int j = 0; j < bolas.size(); j++) {
			mensaje += bolas.get(j).toString() + "\n";
		}
		return mensaje;
	}

	/**
	 * Llamado al inicio para interpretar un texto y crear a todos los jugadores a partir de una linea de texto. 
	 * @param mensaje - Contiene distintas lineas, cada una con informacion sobre un jugador y su bola.
	 */
	public void asignarInformacionJugadoresCompleta(String mensaje) {
		String[] valores = mensaje.split("\n");
		ArrayList<Bola> nuevo = new ArrayList<Bola>();
		HashMap<String, Integer> dir = new HashMap<String, Integer>();
		for (int i = 0; i < valores.length; i++) {
			Bola m = new Bola(valores[i]);
			dir.put(m.getNick(), i);
			nuevo.add(m);
		}
		setJugadores(nuevo);
		directorioJugadores = dir;
	}

	/**
	 * Llamado durante el juego para interpretar un texto y cambiar la informacion de un jugador. 
	 * @param mensaje - Contiene informacion sobre un jugador y su bola.
	 * @param nick - nombre de usuario al que se le va a modificar la informacion.
	 */
	public void asignarInformacionJugadores(String mensaje, String nick) {
		String[] valores = mensaje.split("\n");
		ArrayList<Bola> nuevo = new ArrayList<Bola>();
		HashMap<String, Integer> dir = new HashMap<String, Integer>();
		int k = directorioJugadores.get(nick);
		for (int i = 0; i < valores.length; i++) {
			if (k == i) {
				nuevo.add(i, jugadores.get(k));
				dir.put(nick, i);
			} else {
				Bola m = new Bola(valores[i]);
				dir.put(m.getNick(), i);
				nuevo.add(i, m);
			}
		}
		setJugadores(nuevo);
		directorioJugadores = dir;
	}

	/**
	 * Modifica la informacion de un jugador, usando un string que contiene el nombre del jugador
	 * @param mensaje
	 */
	public void asignarInformacionJugador(String mensaje) {
		String[] valores = mensaje.split(",");
		String nick = valores[0];
		int n = directorioJugadores.get(nick);
		jugadores.get(n).actualizarInformacion(mensaje);
	}

	/**
	 * Modifica la informacion de la comida que hay en el juego a traves de un mensaje de varias lineas, cada con informacion sobre una bola de comida.
	 * @param mensaje - String de varias lineas con información sobre bolas de comida.
	 */
	public void asignarInformacionComida(String mensaje) {
		ArrayList<Bola> nuevo = new ArrayList<Bola>();
		String[] valores = mensaje.split("\n");
		for (int i = 0; i < valores.length; i++) {
			Bola m = new Bola(valores[i]);
			nuevo.add(m);
		}
		setBolas(nuevo);
	}

}
