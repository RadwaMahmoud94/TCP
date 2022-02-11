import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;
public class session extends Thread {
	/*public static String rev(String s){
		return new StringBuilder(s).reverse().toString();
		}*/
	Socket clientSocket;
	public session(Socket clientSocket) {
		this.clientSocket=clientSocket;
		
	}
	@Override
	public void run() {
		
		Scanner scanner= new Scanner(System.in);
	
		try {
			DataOutputStream Output = new DataOutputStream(clientSocket.getOutputStream());
		
		DataInputStream Input=new DataInputStream(clientSocket.getInputStream());
		 Output.writeUTF("CONNECTED");
		
			
			
	
				
				 while (true) {
					 Output.writeUTF(" Please enter the list of numbers to be arranged or 'close' to close the connection. ");
					 String request=Input.readUTF();
					 System.out.println("Client: "+request);
					if(request.equalsIgnoreCase("close")){
						    clientSocket.close();
							System.out.println("Connection is closed");
							break;				
							     
					} else if (request.contains(",")){
						
						String current = "Please choose:\r\n"
								+ "1. Ascending order.\r\n"
								+ "2. Descending order.";
						Output.writeUTF(current);	

						
						String clientReply=Input.readUTF();
						System.out.println("Client: "+clientReply);
						String[] splitted = request.split(",");
						int[] DesArr=new int [splitted.length];
						int[] numb = new int[splitted.length];
						for(int i=0;i<splitted.length;i++) {
							numb[i] = Integer.parseInt(splitted[i]);
							
						}
						Arrays.sort(numb);
					
						String sortedarray = String.join(",", Arrays.toString(numb));
						
					if(clientReply.equals("1")) {
						
						Output.writeUTF(sortedarray);
						}
						else if (clientReply.equals("2")) {
							int j =splitted.length ;
	                        for (int i = 0; i < splitted.length; i++) {
	                        	DesArr[j - 1] = numb[i];
	                               j = j - 1;
	                         }
	                        sortedarray = String.join(",", Arrays.toString(DesArr));	
							Output.writeUTF(sortedarray);
							
						}
						
						else {Output.writeUTF("wrong choice");}
			
				 }
					else {
					 Output.writeUTF("wrong choice");
				 }
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}


