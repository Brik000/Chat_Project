
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HiloAtentoAuser extends Thread {
	
	private Server server;
	
	public HiloAtentoAuser(Server server) {
		
		this.server = server;
	}

	
	public void run() {
		
		try {
			
			
			DataInputStream in;
			
			while(server.isServerConected()) {
//				System.out.println("entro al hilo atento al user");
				Socket socketReceived = server.getServerSocketReceived().accept();
//				System.out.println("El cliente se ha conectado!");
				in = new DataInputStream(socketReceived.getInputStream());
				String mensajeObtenidoCliente = in.readUTF();
				server.verificarSiEstaRegistrado(socketReceived, mensajeObtenidoCliente);
				server.nuevoMensaje(mensajeObtenidoCliente);
				Thread.sleep(1);
				server.setSendMulticast(true);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
	}

}
