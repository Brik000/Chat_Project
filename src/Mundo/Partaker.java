package Mundo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Partaker {
	
	private ObjectInputStream sInput;		
	private ObjectOutputStream sOutput;		
	private Socket socket;					
	public static final int PORT_MESSAGE = 5650;
	public static final String host = "localhost";
	
	public static final String DIRECCION_MULTICAST = "229.5.6.7";
	
	private ArrayList<String> messages;
	private String userName;
	
	public Partaker(String userName) {
		this.userName= userName;
		messages= new ArrayList<String>();
	}
	public Partaker() {
		this.userName= "Anonymous";
		messages= new ArrayList<String>();
	}

	public void conect(){
		try
		{
			sInput  = new ObjectInputStream(socket.getInputStream());
			sOutput = new ObjectOutputStream(socket.getOutputStream());
		}
		catch (IOException eIO) {
			display("Exception creating new Input/output Streams: " + eIO);
		}
	}
	public void display(String message) {
		System.out.println(message);
	}
	public static void main(String[] args) {
		// default values if not entered
		String userName = "Anonymous";
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter the username: ");
		userName = scan.nextLine();

		Partaker client = new Partaker(userName);
		
		
		
		System.out.println("\nHello.! Welcome to the chatroom.");
		System.out.println("Instructions:");
		System.out.println("1. Simply type the message to send broadcast to all active clients");
		System.out.println("2. Type '@username<space>yourmessage' without quotes to send message to desired client");
		System.out.println("3. Type 'WHOISIN' without quotes to see list of active clients");
		System.out.println("4. Type 'LOGOUT' without quotes to logoff from server");
		
		// infinite loop to get the input from the user
		while(true) {
			System.out.print("> ");
			String msg = scan.nextLine();
			if(msg.equalsIgnoreCase("LOGOUT")) {
				client.sendMessage(new ChatMessage(ChatMessage.LOGOUT, ""));
				break;
			}
			else if(msg.equalsIgnoreCase("WHOISIN")) {
				client.sendMessage(new ChatMessage(ChatMessage.WHOISIN, ""));				
			}
			else {
				client.sendMessage(new ChatMessage(ChatMessage.MESSAGE, msg));
			}
		}
		scan.close();
		client.disconnect();	
	}
	void sendMessage(ChatMessage msg) {
		try {
			sOutput.writeObject(msg);
		}
		catch(IOException e) {
			display("Exception writing to server: " + e);
		}
	}

	private void disconnect() {
		try { 
			if(sInput != null) sInput.close();
		}
		catch(Exception e) {}
		try {
			if(sOutput != null) sOutput.close();
		}
		catch(Exception e) {}
        try{
			if(socket != null) socket.close();
		}
		catch(Exception e) {}
			
	}

}
