package hilos;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;

import conexion.ServidorScore;

public class HiloClientHandler extends Thread {
	private Socket socket;
	private ServidorScore server;

	public HiloClientHandler(Socket socket, ServidorScore server) {

		this.socket = socket;
		this.server = server;
	}

	public void run() {

		// le quite el while true
		handleRequest(this.socket);

	}

	private void handleRequest(Socket socket2) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String headerLine = in.readLine();
			// A tokenizer is a process that splits text into a series of tokens
			StringTokenizer tokenizer = new StringTokenizer(headerLine);
			// The nextToken method will return the next available token
			String httpMethod = tokenizer.nextToken();
			// The next code sequence handles the GET method. A message is displayed on the
			// server side to indicate that a GET method is being processed
			if (httpMethod.equals("GET")) {
				System.out.println("Get method processed");
				String httpQueryString = tokenizer.nextToken();
				System.out.println(httpQueryString);
				if (httpQueryString.equals("/")) {
					StringBuilder responseBuffer = new StringBuilder();
					String str = "";
					BufferedReader buf = new BufferedReader(new FileReader("web/agarweb.html"));
					while ((str = buf.readLine()) != null) {
						responseBuffer.append(str);
					}
					sendResponse(socket, 200, responseBuffer.toString());
					buf.close();
				}
				// permite obtener el dato ingresado en el submit
//				if (httpQueryString.contains("/?ced=")) {
//					System.out.println("Get method processed");
//					String[] response = httpQueryString.split("=");
//					String mensajeObtenido = server.askForData(response[1]);
//					String[] lista = mensajeObtenido.split("\n");
//					StringBuilder responseBuffer = new StringBuilder();
//					responseBuffer.append("<html>").append("<head>").append("<style>").append("body{").append(
//							"	background-image: url(\"http://www.mascotahogar.com/Imagenes/wallpaper-de-un-caballo-blanco.jpg\");")
//							.append("}").append("</style>").append("<title>Informacion Cedula Correspondiente</title>")
//							.append("</head>").append("<body>").append("<h1>Listado de Carreras Realizadas</h1>")
//							.append("<table>").append("<tr>").append("<td><strong>CEDULA</strong></td>")
//							.append("<td><strong>CABALLO</strong></td>").append("<td><strong>MONTO $</strong></td>")
//							.append("<td><strong>GANO?</strong></td>");
//					agregarlista(lista, responseBuffer, response[1].trim());
//					responseBuffer.append("<body>").append("<table>").append("<body>").append("</html>");
//
//					sendResponse(socket, 200, responseBuffer.toString());
//
//				}
				if(httpQueryString.contains("/?usr=")) {
					System.out.println("Get method processed");
					String[] data = httpQueryString.split("&");
					String[] usr = data[0].split("=");
					String[] pss = data[1].split("=");
					//CONDICIONAL FOR PASSWORD HERE
					if(server.auntenticar(usr[1], pss[1]).equals("true")) {
						String returnMessage = server.askForData(usr[1]);
						String[] lista = returnMessage.split("\n");
						StringBuilder responseBuffer = new StringBuilder();
						responseBuffer
						.append("<html>")
						.append("<head>")
						.append("<style>")
						.append("body{")
						.append("	background-image: url(\"http://agar.io/img/1200x630.png\");")
						.append("	background-size: cover;")
						.append("	background-repeat: no-repeat;")
						.append("}")
						.append("table,td{")
						.append("	background-color: white;")
						.append("	width: 100px;")
						.append("	border: 1px solid black;")
						.append("	table-layout: fixed;")
						.append("}")
						.append("h1{")
						.append("	background-color: white;")
						.append("	border: 1px solid black;")
						.append("}")
						.append("tr{")
						.append("	border: 2px solid black;")
						.append("}")
						.append("</style>")
						.append("<title>Resultados Usuario Correspondiente</title>")
						.append("</head>")
						.append("<body>")
						.append("<h1>Resultados</h1>")
						.append("<table>")
						.append("<tr>")
						.append("<td><strong>FECHA</strong></td>")
						.append("<td><strong>USUARIO</strong></td>")
						.append("<td><strong>PUNTAJE</strong></td>")
						.append("<td><strong>GANO?</strong></td>");
						agregarlista(lista, responseBuffer, usr[1].trim());
						responseBuffer.append("<body>")
						.append("<table>")
						.append("<body>")
						.append("</html>");
						sendResponse(socket, 200, responseBuffer.toString());
					} else if (server.auntenticar(usr[1], pss[1]).equals("false")){
						StringBuilder responseBuffer = new StringBuilder();
						responseBuffer
						.append("<html>")
						.append("<head>")
						.append("<style>")
						.append("body{")
						.append("	background-image: url(\"http://agar.io/img/1200x630.png\");")
						.append("	background-size: cover;")
						.append("	background-repeat: no-repeat;")
						.append("}")
						.append("</style>")
						.append("<title>ERROR</title>")
						.append("</head>")
						.append("<body>")
						.append("<h1>Contraseña erronea.</h1>")
						.append("<body>")
						.append("</html>");
						sendResponse(socket, 200, responseBuffer.toString());
					} else {
						StringBuilder responseBuffer = new StringBuilder();
						responseBuffer
						.append("<html>")
						.append("<head>")
						.append("<style>")
						.append("body{")
						.append("	background-image: url(\"http://agar.io/img/1200x630.png\");")
						.append("	background-size: cover;")
						.append("	background-repeat: no-repeat;")
						.append("}")
						.append("</style>")
						.append("<title>ERROR</title>")
						.append("</head>")
						.append("<body>")
						.append("<h1>Este usuario no existe.</h1>")
						.append("<body>")
						.append("</html>");
						sendResponse(socket, 200, responseBuffer.toString());
					}
					
					
					
//					String returnMessage = server.askForData(usr[1]);
//					String[] lista = returnMessage.split("\n");
//					StringBuilder responseBuffer = new StringBuilder();
//					responseBuffer
//					.append("<html>")
//					.append("<head>")
//					.append("<style>")
//					.append("body{")
//					.append("	background-image: url(\"http://agar.io/img/1200x630.png\");")
//					.append("	background-size: cover;")
//					.append("	background-repeat: no-repeat;")
//					.append("}")
//					.append("table,td{")
//					.append("	background-color: white;")
//					.append("	width: 100px;")
//					.append("	border: 1px solid black;")
//					.append("	table-layout: fixed;")
//					.append("}")
//					.append("tr{")
//					.append("	border: 2px solid black;")
//					.append("}")
//					.append("</style>")
//					.append("<title>Resultados Usuario Correspondiente</title>")
//					.append("</head>")
//					.append("<body>")
//					.append("<h1>Resultados</h1>")
//					.append("<table>")
//					.append("<tr>")
//					.append("<td><strong>FECHA</strong></td>")
//					.append("<td><strong>USUARIO</strong></td>")
//					.append("<td><strong>PUNTAJE</strong></td>")
//					.append("<td><strong>GANO?</strong></td>");
//					agregarlista(lista, responseBuffer, usr[1].trim());
//					responseBuffer.append("<body>")
//					.append("<table>")
//					.append("<body>")
//					.append("</html>");
//					sendResponse(socket, 200, responseBuffer.toString());
					
					
					
					
				}
			} else {
				System.out.println("The HTTP method is not recognized");
				sendResponse(socket, 405, "Method Not Allowed");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void agregarlista(String[] lista, StringBuilder responseBuffer, String usr) {
		for (int i = 0; i < lista.length; i++) {
			System.out.println("CHEQUEO LISTA :" + lista[i]);
			if(lista[i].contains(usr)) {
				String temp[] = lista[i].split(";");
				responseBuffer.append("<tr>");
				responseBuffer.append("<td>"+temp[0]+"</td>");
				responseBuffer.append("<td>"+temp[1]+"</td>");
				responseBuffer.append("<td>"+temp[2]+"</td>");
				responseBuffer.append("<td>"+temp[3]+"</td>");
				responseBuffer.append("<tr>");
				responseBuffer.append("<td> </td>");
				responseBuffer.append("<td>"+temp[4]+"</td>");
				responseBuffer.append("<td>"+temp[5]+"</td>");
				responseBuffer.append("<td>"+temp[6]+"</td>");
				responseBuffer.append("<tr>");
				if(temp.length>7) {
					responseBuffer.append("<td> </td>");
					responseBuffer.append("<td>"+temp[7]+"</td>");
					responseBuffer.append("<td>"+temp[8]+"</td>");
					responseBuffer.append("<td>"+temp[9]+"</td>");
					responseBuffer.append("<tr>");
				}
				if(temp.length>10) {
					responseBuffer.append("<td> </td>");
					responseBuffer.append("<td>"+temp[10]+"</td>");
					responseBuffer.append("<td>"+temp[11]+"</td>");
					responseBuffer.append("<td>"+temp[12]+"</td>");
					responseBuffer.append("<tr>");
				}
				if(temp.length>13) {
					responseBuffer.append("<td> </td>");
					responseBuffer.append("<td>"+temp[13]+"</td>");
					responseBuffer.append("<td>"+temp[14]+"</td>");
					responseBuffer.append("<td>"+temp[15]+"</td>");
					responseBuffer.append("<tr>");
				}
				responseBuffer.append("<br>");
				responseBuffer.append("<table>");
				
			}
		}

	}

	public void sendResponse(Socket socket, int statusCode, String responseString) {
		String statusLine;
		String serverHeader = "Server: WebServer\r\n";
		String contentTypeHeader = "Content-Type: text/html\r\n";

		try {
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			if (statusCode == 200) {
				statusLine = "HTTP/1.0 200 OK" + "\r\n";
				String contentLengthHeader = "Content-Length: " + responseString.length() + "\r\n";
				out.writeBytes(statusLine);
				out.writeBytes(serverHeader);
				out.writeBytes(contentTypeHeader);
				out.writeBytes(contentLengthHeader);
				out.writeBytes("\r\n");
				out.writeBytes(responseString);
			} else if (statusCode == 405) {
				statusLine = "HTTP/1.0 405 Method Not Allowed" + "\r\n";
				out.writeBytes(statusLine);
				out.writeBytes("\r\n");
			} else {
				statusLine = "HTTP/1.0 404 Not Found" + "\r\n";
				out.writeBytes(statusLine);
				out.writeBytes("\r\n");
			}
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
