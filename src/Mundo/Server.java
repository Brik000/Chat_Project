package Mundo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	public final static int PUERTO=1024;
	
	ServerSocket servidor;
	
	Socket servicio;
	
	
	boolean status;
	
	ArrayList<HiloCliente> usuarios;
	
	
	private BufferedReader entrada;
	private PrintWriter salida;
	
	//comandos admin
	
	public Server() {
		
		 usuarios=new ArrayList<HiloCliente>();
		 
	}
	
	public void coneccionNueva() {
		
		status=true;
		
		servicio=null;
		
		
		try {
			servidor=new ServerSocket(PUERTO);
			 System.out.println("SERVIDOR A LA ESPERA DE CONECCION POR EL PUERTO "+PUERTO);
			 
			 while (status) {
				servicio=servidor.accept();
				
				entrada=new BufferedReader(new InputStreamReader(servicio.getInputStream()));
				salida=new PrintWriter(new BufferedWriter(new OutputStreamWriter(servicio.getOutputStream())),true);
				
				
				HiloCliente nc=new HiloCliente(servicio, entrada, salida);
				
				nc.start();
				
				usuarios.add(nc);
				
				System.out.println(usuarios.size());
				
			 }
			
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<HiloCliente> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<HiloCliente> usuarios) {
		this.usuarios = usuarios;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server os=new Server();
		os.coneccionNueva();

	}

}
