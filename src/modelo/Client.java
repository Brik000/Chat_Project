package modelo;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.file.Files;

import javax.imageio.ImageIO;

import interfaz.Principal;

public class Client {

	final static int ServerPort = 1234; 
	  
	public InetAddress ip; 
      
    public Socket s; 
      
    public DataInputStream dis; 
    public DataOutputStream dos; 

	
	
	public void sendMessage(String msg) {
        try { 
            dos.writeUTF(msg); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
	}
    @SuppressWarnings("deprecation")
    public Client() {
    	final Principal panel= new Principal();
    	panel.asignarMundo(this);
      	panel.show();
        //final Scanner scn = new Scanner(System.in); 
        
      	try {
            ip= InetAddress.getByName("localhost");
            s = new Socket(ip, ServerPort);
            
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
              
//            InetAddress ip = InetAddress.getByName("localhost"); 
//              
//            Socket s = new Socket(ip, ServerPort); 
//              
//            final DataInputStream dis = new DataInputStream(s.getInputStream()); 
//            final DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
      
//            Thread sendMessage = new Thread(new Runnable()  
//            { 
//                public void run() { 
//                    while (true) { 
    //  
//                        // read the message to deliver. 
//                        String msg = scn.nextLine(); 
//                          
//                        try { 
//                            // write on the output stream 
//                            dos.writeUTF(msg); 
//                        } catch (IOException e) { 
//                            e.printStackTrace(); 
//                        } 
//                    } 
//                } 
//            }); 
              
            Thread readMessage = new Thread(new Runnable()  
            { 
                public void run() { 
      
                    while (true) { 
                        try { 
                            String msg = dis.readUTF(); 
                            panel.agregarMensaje(msg);
                            //System.out.println(msg); 
                        } catch (IOException e) { 
      
                            e.printStackTrace(); 
                        } 
                    } 
                } 
            }); 
      
            //sendMessage.start(); 
            readMessage.start(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	public void sendImage(File file) {
        try { 
        	ByteArrayOutputStream byteArrayOutPutStream= new ByteArrayOutputStream();
        	BufferedImage image= ImageIO.read(file);
        	ImageIO.write(image, "jpg", byteArrayOutPutStream);
        	byte[] size= ByteBuffer.allocate(4).putInt(byteArrayOutPutStream.size()).array();
        	dos.write(byteArrayOutPutStream.toByteArray());
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
	} 
}
