import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class HiloEnvioMulticast extends Thread{
	
	private Server server;
	
	public HiloEnvioMulticast(Server server) {
		
		this.server = server;
	}

	
	public void run() {
		
		try {
			

			DataInputStream in;
			DataOutputStream out;
			
			while(server.isServerConected()) {
				System.out.println("");
				if(server.isSendMulticast() && server.getMensajes().size() >0) {
					
					System.out.println("entro al enviar en hiloenviomulticast");
					for (int i = 0; i < server.getSockets().size(); i++) {
						Socket actual  = server.getSockets().get(i);
//						actual = new Socket(actual.getInetAddress(), server.PORT_SEND);
						out = new DataOutputStream(actual.getOutputStream());
						for (int j = 0; j < server.getMensajes().size() ; j++) {
							String mensaje = server.getMensajes().get(j);
							out.writeUTF(mensaje);
						}
					}
					
					server.eraseMessages();
//					server.setSendMulticast(false);
			
				}
		}
	}
	catch (Exception e) {
			e.printStackTrace();
		}
		
				
				
		}
		
		
	}