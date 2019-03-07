import java.io.DataInputStream;
import java.net.Socket;

public class HiloClientHandler  extends Thread{

	private Socket socket;
	
	private Server server;

	public HiloClientHandler(Socket socket, Server server) {
	
		this.socket = socket;
		this.server = server;
	}

	public void run() {
		
		try {
			
			DataInputStream in;
			
			while(server.isServerConected()) {
				in = new DataInputStream(socket.getInputStream());
				String mensajeObtenidoCliente = in.readUTF();
//				System.out.println("nuevo mensaje del cliente");
				server.nuevoMensaje(mensajeObtenidoCliente);
				Thread.sleep(1);
			
			
			
			
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	

	
	
	
	
}