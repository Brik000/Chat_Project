import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	
	
	private  HiloAtentoAuser hiloEscuchaUser;
	
	private HiloEnvioMulticast hiloEnvio;
	
//	public static final int PORT_SEND = 8500;
	
	public static final int PORT_RECEIVE = 8000;
	
	/**
	 * El servidor dispone de un serversocket, para permitir la conexion a los clientes
	 */
	private static  ServerSocket serverSocketReceived;
	
//	private static  ServerSocket serverSocketSend;
	/**
	 * El servidor dispone de un socket para atender a cada cliente por individual
	 */
	private  ArrayList<Socket> sockets;
	
	private  ArrayList<String> usuarios; 
	
	private boolean isServerConected;
	
	private boolean sendMulticast;
	
	private ArrayList<String> mensajes;
	
	private ArrayList<HiloClientHandler> manejadorActivos;
	
	
	public Server() {

		try {
			
			System.out.println("::SERVIDOR CHAT ICESI :ON ::");
			
			isServerConected = true;
			
			manejadorActivos = new ArrayList<>();
			
			serverSocketReceived = new ServerSocket(PORT_RECEIVE);
			
//			serverSocketSend = new ServerSocket(PORT_SEND);
			
			sockets = new ArrayList<>();
			
			usuarios = new ArrayList<>();
			
			mensajes = new ArrayList<>();
			
			hiloEnvio = new HiloEnvioMulticast(this);
			hiloEnvio.start();
			
			hiloEscuchaUser = new HiloAtentoAuser(this);
			hiloEscuchaUser.start();
			
			
			sendMulticast = false;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}


	public HiloAtentoAuser getHiloEscuchaUser() {
		return hiloEscuchaUser;
	}


	public void setHiloEscuchaUser(HiloAtentoAuser hiloEscuchaUser) {
		this.hiloEscuchaUser = hiloEscuchaUser;
	}


	public HiloEnvioMulticast getHiloEnvio() {
		return hiloEnvio;
	}


	public void setHiloEnvio(HiloEnvioMulticast hiloEnvio) {
		this.hiloEnvio = hiloEnvio;
	}


	public static ServerSocket getServerSocketReceived() {
		return serverSocketReceived;
	}


	public static void setServerSocketReceived(ServerSocket serverSocketReceived) {
		Server.serverSocketReceived = serverSocketReceived;
	}


//	public static ServerSocket getServerSocketSend() {
//		return serverSocketSend;
//	}
//
//
//	public static void setServerSocketSend(ServerSocket serverSocketSend) {
//		Server.serverSocketSend = serverSocketSend;
//	}


	public  ArrayList<Socket> getSockets() {
		return sockets;
	}


	public  void setSockets(ArrayList<Socket> sockets) {
		this.sockets = sockets;
	}


	public  ArrayList<String> getUsuarios() {
		return usuarios;
	}


	public  void setUsuarios(ArrayList<String> usuarios) {
		this.usuarios = usuarios;
	}


	public boolean isServerConected() {
		return isServerConected;
	}


	public void setServerConected(boolean isServerConected) {
		this.isServerConected = isServerConected;
	}


	public boolean verificarSiEstaRegistrado(Socket socketReceived, String mensaje) throws IOException {
		String[] components = mensaje.split(";");
//		System.out.println("salio del in.readutf en hiloatentouser");
		String obtaninNickFromSocket = components[0];
		boolean bandera = false;
		if(usuarios.contains(obtaninNickFromSocket)) {
			bandera = true;
		}
		else {
			sockets.add(socketReceived);
			System.out.println(sockets.size());
			usuarios.add(components[0]);
			HiloClientHandler hilo = new HiloClientHandler(socketReceived, this);
			hilo.start();
		}
//		System.out.println("salio del verificar si esta registrado en hiloatentouser");
		return bandera;
	}


	


	public boolean isSendMulticast() {
		return sendMulticast;
	}


	public void setSendMulticast(boolean sendMulticast) {
		this.sendMulticast = sendMulticast;
	}


	public ArrayList<String> getMensajes() {
		return mensajes;
	}


	public void setMensajes(ArrayList<String> mensajes) {
		this.mensajes = mensajes;
	}


	public void eraseMessages() {
		
		mensajes = new ArrayList<>();
		
	}


	public void nuevoMensaje(String mensajeObtenidoCliente) {
		
		mensajes.add(mensajeObtenidoCliente);
		
	}


	
	
	
	
	
	
	
	

}