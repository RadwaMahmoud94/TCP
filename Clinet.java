import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Clinet {

	public static void main(String[] args) {
		try {
			InetAddress ip= InetAddress.getByName("localhost");
			Socket ClinetSocket=new Socket(ip,2000);
			Scanner scanner= new Scanner(System.in);
			DataOutputStream Output=new DataOutputStream(ClinetSocket.getOutputStream());
			DataInputStream Input=new DataInputStream(ClinetSocket.getInputStream());
			String conn =Input.readUTF();
			System.out.println("server:"+conn);
			while (true){
				String ask=Input.readUTF();
				System.out.println("server:"+ask);
				String request =scanner.nextLine();
				Output.writeUTF(request);
				if(request.equalsIgnoreCase("close")){
					ClinetSocket.close();
					System.out.println("Connection is closed");
					break;				
				} 
				else {
					String reply=Input.readUTF();
					System.out.println("sever"+reply);
					
					String clientReply = scanner.nextLine();
					Output.writeUTF(clientReply);
					
					String sortedarray=Input.readUTF();
					System.out.println("server: " + sortedarray);
					
				}
			}		
		} 
		catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
