package controlador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Properties;

public class ServidorTCP {

    private ServerSocket receptor;
    private Properties configuracion;
    private String dirServidor1;
    private String dirServidor;
    private int puertoServidor;
    private Socket clienteE;
    private InetAddress dirCliente;
    private int puertoCliente;
    private DataOutputStream outEmisor;
    private DataInputStream inEmisor;
    private Socket clienteR;
    private String datosDestino;
    private InetAddress dirDestino;
    private int puertoDestino;
    private DataOutputStream outReceptor;
    private DataInputStream inReceptor;
    private int tam;
    private String nombreArchivo;
    private File archivo;

    public ServidorTCP(String archivo) throws SQLException, Exception {
        // Primero indicamos la direccion IP local 
        dirServidor1 = InetAddress.getLocalHost().toString();
        System.out.println("Servidor >> Direccion IP: " + dirServidor1);
        cargarConfiguracion(archivo);
    }

    public String getDirServidor() {
        return dirServidor;
    }

    public int getPuertoServidor() {
        return puertoServidor;
    }

    private void cargarConfiguracion(String archivo) throws Exception {
        FileInputStream fis = new FileInputStream(archivo);
        configuracion = new Properties();
        configuracion.load(fis);
        fis.close();
        puertoServidor = Integer.parseInt(configuracion.getProperty("servidor.puerto"));
        dirServidor = configuracion.getProperty("servidor.direccion");
        clienteE = null;
        clienteR = null;
        inEmisor = null;
        outEmisor = null;
        inReceptor = null;
        outReceptor = null;
    }

    public void recibirConexiones() {
        try {
            receptor = new ServerSocket(puertoServidor);

            // Esperar una nueva conexion 
            Socket Clt1 = receptor.accept();
            System.out.println();
            System.out.println("Servidor >> Parametros de la Conexion Emisor: " + Clt1);
            CrearEmisor(Clt1);

            Socket Clt2 = receptor.accept();
            System.out.println();
            System.out.println("Servidor >> Parametros de la Conexion Receptor: " + Clt2);
            CrearReceptor(Clt2);

            System.out.println("Servidor >> Puerto Cliente Origen --> " + puertoCliente);
            System.out.println("Servidor >> Puerto Cliente Destino --> " + puertoDestino);

            //aca se cambia el tipo de transferencia que utiliza el servidor
            Transferencia();
        } catch (Exception e) {
            System.out.println("Servidor >> Error al recibir conexiones");
        } finally {
            try {
                receptor.close();
            } catch (IOException ex) {
                System.out.println("Servidor >> Error al cerrar conexiones");
            }
        }
    }

    synchronized private void CrearEmisor(Socket socketCliente) throws IOException {
        try {
            System.out.println("Servidor >> Creando Emisor de Archivo");
            //El socket del Cliente, por el cual se realiza la comunicacion con el servidor 
            clienteE = socketCliente;

            // Se extrae la informacion del socket 
            // No de puerto remoto 
            puertoCliente = clienteE.getPort();
            // Direccion de Internet remota 
            dirCliente = clienteE.getInetAddress();
            // Extraemos los Streams de entrada y de salida 
            inEmisor = new DataInputStream(clienteE.getInputStream());
            outEmisor = new DataOutputStream(clienteE.getOutputStream());

            configurarDestino();
            
        } catch (Exception e) {
            System.out.println("Servidor >> Error al crear el Emisor");
        }
       // socketCliente.close();
    }

    synchronized private void CrearReceptor(Socket socketCliente) throws IOException {
        try {
            System.out.println("Servidor >> Creando Receptor de Archivo");
            //El socket del Cliente, por el cual se realiza la comunicacion con el servidor 
            clienteR = socketCliente;
            // Se extrae la informacion del socket 
            // No de puerto remoto 
            puertoDestino = clienteR.getPort();
            // Direcci�n de Internet remota 
            dirDestino = clienteR.getInetAddress();
            // Extraemos los Streams de entrada y de salida 
            inReceptor = new DataInputStream(clienteR.getInputStream());
            outReceptor = new DataOutputStream(clienteR.getOutputStream());
            
        } catch (IOException e) {
            System.out.println("Servidor >> Error al crear el Receptor");
        }
       // socketCliente.close();
    }

    private void configurarDestino() throws Exception {
        //Creamos flujo de entrada para leer los datos que envia el cliente
        //Los datos iniciales del archivo a enviar
        DataInputStream dis = new DataInputStream(clienteE.getInputStream());
        // Obtenemos el nombre del archivo
        this.nombreArchivo = dis.readUTF().toString();
        // Obtenemos el tamano del archivo
        this.tam = (int) dis.readInt();

       System.out.println("Servidor >> Tranfiriendo el Archivo: " + nombreArchivo + " Ocupa: " + tam + " bytes");

        //se lee del canal el properties del destino
        datosDestino = inEmisor.readUTF();
    }

    /**
     * Metodo de Transferencia
     * S agrupa todos los datos que provienen de C1 hasta que el buffer de S se llena y no tiene mas espacio.
     * Entonces S transferir los datos desde su buffer hacia C2. Este ciclo se repite hasta que la totalidad del
     * archivo sea transferido.
     * @throws IOException Se lanza esta excepci�n si hay problemas desconectando al cliente
     */
    public void Transferencia() throws IOException {
        byte[] Buf1 = new byte[2048];
        byte[] Buf = new byte[8192];	//buffer for sockets transfers	
        int bytesRead = 0;
        int acum = 0;
        int paq = 0;
        System.out.println("Servidor >> Envio de Paquetes del Servidor --> TipoB");
        System.out.println("Tamaño a enviar --> " + tam);
        int falta;
        while (tam > acum) {
            falta = tam - acum;
            if (falta >= Buf.length) {
                inEmisor.readFully(Buf);
                outReceptor.write(Buf);
                paq++;
                acum = acum + Buf.length;
                System.out.println("Enviados --> " + acum + " de " + tam);
            } else {
                inEmisor.read(Buf, 0, falta);
                outReceptor.write(Buf, 0, falta);
                acum = acum + falta;
                System.out.println("Enviados --> " + acum + " de " + tam);
                paq++;
                System.out.println("Paquete No --> " + paq + " bytes: " + falta);
                bytesRead = 0;
            }
        }
        System.out.println("Servidor >> Termino la Transferencia");
        desconectar();
    }

    public void desconectar() throws IOException {
        if (clienteE != null) {
            inEmisor.close();
            inEmisor = null;

            outEmisor.close();
            outEmisor = null;

            clienteE.close();
            clienteE = null;
        }
        if (clienteR != null) {

            inReceptor.close();
            inReceptor = null;

            outReceptor.close();
            outReceptor = null;

            clienteR.close();
            clienteR = null;
        }
        receptor.close();
        System.exit(0);
    }
}