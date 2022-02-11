import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;


public class Server {

	
	public static void main(String[] args) {
			try {
				ServerSocket serversocket= new ServerSocket(2000);
				System.out.println("server is booted up and ready to accept client");
				while(true) {
					Socket clientsocket = serversocket.accept();
					session clientsession = new session(clientsocket);
					clientsession.start();
				}
			 
				}
			 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
			

}