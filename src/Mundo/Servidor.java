package Mundo;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import javax.swing.JOptionPane;

import Interfaz.FramePrincipal;

/**
 * Clase en la que se maneja la comunicaci�n del lado del servidor.
 * @author Erick Navarro
 */
public class Servidor extends Thread{    
    /**
     * Socket servidor que tiene como principal funci�n escuchar cuando los clientes
     * se conectan para incluirlos en el chat.
     */
    private ServerSocket serverSocket;
    /**
     * Lista de todos los hilos de comunicaci�n, para cada cliente se instancia uno 
     * de estos hilos ya que cada hilo esta escuchando permanentemente lo que dicho 
     * cliente env�a al servidor. 
     */
    LinkedList<HiloCliente> clientes;
    /**
     * Variable que almacena la ventana que gestiona la interfaz gr�fica del servidor.
     */
    private final FramePrincipal ventana;
    /**
     * Variable que almacena el puerto que el servidor usar� para escuchar. 
     */
    private final String puerto;
    /**
     * Correlativo para diferenciar a los m�ltiples clientes que se conectan, si se 
     * conectaran, por ejemplo, dos usuarios con el mismo nombre, se podr�an diferenciar
     * por este correlativo.
     */
    static int correlativo;
    /**
     * Constructor del servidor.
     * @param puerto
     * @param ventana 
     */
    public Servidor(String puerto, FramePrincipal ventana) {
        correlativo=0;
        this.puerto=puerto;
        this.ventana=ventana;
        clientes=new LinkedList<>();
        this.start();
    }
    /**
     * M�todo sobre el que corre el bucle infinito que tiene como funci�n escuchar
     * permenentemente en espera de conexiones de nuevos clientes.
     */    
    public void run() {
        try {
            serverSocket = new ServerSocket(Integer.valueOf(puerto));
            ventana.addServidorIniciado();
            while (true) {
                HiloCliente h;
                Socket socket;
                socket = serverSocket.accept();
                System.out.println("Nueva conexion entrante: "+socket);
                h=new HiloCliente(socket, this);               
                h.start();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(ventana, "El servidor no se ha podido iniciar,\n"
                                                 + "puede que haya ingresado un puerto incorrecto.\n"
                                                 + "Esta aplicaci�n se cerrar�.");
            System.exit(0);
        }                
    }        
    /**
     * Ciclo que devuelve una lista con los identificadores de todos los clientes
     * conectados.
     * @return 
     */
    LinkedList<String> getUsuariosConectados() {
        LinkedList<String>usuariosConectados=new LinkedList<>();
        clientes.stream().forEach(c -> usuariosConectados.add(c.getIdentificador()));
        return usuariosConectados;
    }
    /**
     * M�todo que agrega una linea al log de la interfaz gr�fica del servidor.
     * @param texto 
     */
    void agregarLog(String texto) {
        ventana.agregarLog(texto);
    }
}
