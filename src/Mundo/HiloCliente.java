
package Mundo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class HiloCliente extends Thread{
	
	
	private Socket servicio;
	private BufferedReader entrada;;
	private PrintWriter salida;
	private String id,nick;
	private boolean admin;
	
	public HiloCliente(Socket servicio, BufferedReader entrada, PrintWriter salida) {
		
		this.servicio = servicio;
		this.entrada = entrada;
		this.salida = salida;
		admin=false;
		id="Cliente"+(int) (Math.random() * 99) + 1; ;
		
	}
	// aqui es lo que hace el cliente
	public void run() {
		System.out.println("El cliente con id "+this.getid()+ " se conecto" );
		boolean control=true;
	 
	 try {
		 //Se crea el servidor local
	 
	  // se recive la cadena del cliente
	  String str = entrada.readLine();
	 
	  //se encripta la cadena
	  		  
	  //se envia la cadena encriptada
	  
		System.out.println(this.getid()+":"+str);
		
		if(str.contains("/change-")){
			this.setid(str.substring(8));
			
		}
	  
	  //Bucle que repite el proceso anterior, hasta que se reciva una cadena vacia
	  while (control) {
		  	salida.flush();
	        str = entrada.readLine();
	        if(str.contains("/change-")){
				this.setid(str.substring(8));
				
			}
	        
	        
	        
			System.out.println(this.getid()+":"+str);
		
	   
	   
	 }}catch (IOException e) {
		// TODO Auto-generated catch block
	      System.out.println("IOException: " + e.getMessage());
	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	public Socket getServicio() {
		return servicio;
	}
	public void setServicio(Socket servicio) {
		this.servicio = servicio;
	}
	public BufferedReader getEntrada() {
		return entrada;
	}
	public void setEntrada(BufferedReader entrada) {
		this.entrada = entrada;
	}
	public PrintWriter getSalida() {
		return salida;
	}
	public void setSalida(PrintWriter salida) {
		this.salida = salida;
	}
	public String getid() {
		return id;
	}
	public void setid(String id) {
		this.id = id;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	
	
	
	
	
	
	
}
