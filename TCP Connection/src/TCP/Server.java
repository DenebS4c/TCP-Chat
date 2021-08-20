package TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Server  {
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");   
	LocalDateTime now = LocalDateTime.now(); 
	Scanner scanner = new Scanner(System.in);
	
	public Server() throws IOException {
		
		
		
		ServerSocket server = new ServerSocket(4444);
		
		System.out.println("Esperando Conexiones En El Puerto 4444 !");
		
		Socket socket = server.accept();
		
		System.out.println("Cliente Conectado Desde -> "+socket.getInetAddress()+":"+socket.getPort()+" !");
		
		BufferedReader in_socket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out_socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
		
		while (true) {
			System.out.print("> ");
			String Cosas = scanner.nextLine();
			
			if (Cosas == "stop") {
				System.out.println("Server Parado !");
				socket.close();
			}
			
			out_socket.println(Cosas);
			String message = in_socket.readLine();
			 
			System.out.println("[ "+dtf.format(now)+" ]"+" Cliente Dijo > "+ message);
			
			
		}
		
		
		
		
	}
	
	public static void main(String[] args) {
		try {
			new Server();
		} catch (Exception e){
			e.printStackTrace();
		}

	}

}
