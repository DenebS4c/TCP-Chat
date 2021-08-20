package TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Client {
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");   
	LocalDateTime now = LocalDateTime.now();
	Scanner scanner = new Scanner(System.in);
	
	
	public Client() throws IOException {
		
		Socket socket = new Socket("localhost", 4444);
		System.out.println("Conectado Correctamente Al Servidor !");
		
		BufferedReader in_socket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out_socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
		
		while (true) {
			
			String message = in_socket.readLine();
			System.out.println("[ "+dtf.format(now)+" ]"+" Server Dijo > "+ message);
			System.out.print("> ");
			String Cosas = scanner.nextLine();
			if (Cosas == "stop") {
				System.out.println("Client Parado !");
				socket.close();
			}
			out_socket.println(Cosas);
			
		}
		
	}
	
	public static void main(String[] args) {
		
		try {
			new Client();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
