import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class HiloEnvioMensajes extends Thread {
	
	public Client client;
	
	
	public HiloEnvioMensajes(Client cliente) {
		
		this.client = cliente;
		
	}
	
	
	public void run() {
		
		try {
			
			DataOutputStream out;
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			Scanner scan = new Scanner(System.in);
			String mensaje = "";
			Socket socket;
			
			while(client.isClientConected()) {
//				System.out.println("inicio hilo de envio de mensajes");
				socket = client.getSocketSend();
				out = new DataOutputStream(socket.getOutputStream());
				mensaje = client.getNickName() + ";";
				mensaje += scan.nextLine();
				out.writeUTF(mensaje);
					
		}
	}
		catch(Exception e) {
			
		}
		
			
			
			
		
		
	}



}