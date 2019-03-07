import java.util.Scanner;

public class PanelCliente {
	
	private static  Client cliente;
	
	
	public static void main(String[] args) {
		
		try {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("::Bienvenido al chat Universitario:: \n::Digite su NICK a continuacion::");
		String nick = scan.nextLine();
		cliente = new Client(nick);
		System.out.println("::Ya puede comenzar a chatear::");
		}
		catch (Exception e) {
			
		}
		
	}

}
