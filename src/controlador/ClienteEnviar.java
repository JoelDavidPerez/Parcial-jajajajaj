package controlador;

import vista.InterfazClienteEnviar1;
import java.io.*;
import java.net.*;
import java.util.*;

public class ClienteEnviar {
    
 
    private InterfazClienteEnviar1 interfaz;
    private String nombreCliente;
    private String direccionCliente;
    private int puertoCliente;
    private String direccionServidor;
    private int puertoServidor;
    private Socket canal;
    private DataInputStream inCliente;
    private DataOutputStream outCliente;
    private String nomArchivo;
    private File archOrigen;
    private int tamanoArchivo;
    

    public ClienteEnviar(String archivoConfiguracion) throws Exception {
        cargarConfiguracion(archivoConfiguracion);
    }

    
    private void cargarConfiguracion(String archivo) throws Exception {
        FileInputStream fis = new FileInputStream(archivo);
        Properties configuracion = new Properties();
        configuracion.load(fis);
        fis.close();
        direccionServidor = configuracion.getProperty("servidor.direccion");
        direccionCliente = configuracion.getProperty("cliente.direccion");
        //interfaz.cambioRuta(direccionCliente);
        puertoServidor = Integer.parseInt(configuracion.getProperty("servidor.puerto"));
        nombreCliente = configuracion.getProperty("cliente.nombre");
        puertoCliente = Integer.parseInt(configuracion.getProperty("cliente.puerto"));
        canal = null;
        inCliente = null;
        outCliente = null;
    }

    public String darNombre() {
        return nombreCliente;
    }

    public String darDireccionIp() {
        return direccionCliente;
    }

    public int darPuerto() {
        return puertoCliente;
    }

    public boolean estaConectado() {
        return canal != null;
    }

    public void conectar(String archivo, InterfazClienteEnviar1 enviar) throws IOException {
        //se crea el canal de comunicacion
        canal = new Socket(direccionServidor, puertoServidor);
        //this.interfaz.labelStatus.setText("Conectando al servidor...");
        //datos del archivo a enviar
        //nombre del archivo
        nomArchivo = archivo;
        archOrigen = new File(nomArchivo);
        //archOrigen.setReadable(true);
        //archOrigen.setWritable(true);
        enviar.cambioRuta(nomArchivo);
        FileInputStream fis = new FileInputStream(nomArchivo);
        //tamano del archivo
        tamanoArchivo = (int) archOrigen.length();
        System.out.println("ClienteEnviar >> Archivo a Enviar: " + archOrigen.getName());
        System.out.println("ClienteEnviar >> Tamano del Archivo a enviar: " + tamanoArchivo);
        // Se crea el flujo de salida, este tipo de flujo  permite 
        // hacer la escritura de diferentes tipos de datos tales como
        // Strings, boolean, caracteres y la familia de enteros, etc.
        DataOutputStream dos = new DataOutputStream(canal.getOutputStream());
        // Enviamos el nombre del archivo 
        dos.writeUTF(archOrigen.getName());
        // Enviamos el tamano del archivo
        dos.writeInt(tamanoArchivo);

        //crear el flujo de entrada y salida
        //el flujo de entrada lee desde el archivo
        inCliente = new DataInputStream(fis);
        //el flujo de salida escribe al canal
        outCliente = new DataOutputStream(canal.getOutputStream());
        
        //Datos del Destino --> Archivo de Properties
        String datosDestino = "C:\\Users\\ssant\\OneDrive\\Documentos\\Info Programación\\TallerSockets1\\TallerSockets\\src\\data\\cliente2.properties";
        FileInputStream file = new FileInputStream(datosDestino);
        Properties configuracion = new Properties();
        configuracion.load(file);
        file.close();
        //interfaz.cambioIP(direccionCliente);
        byte[] inicio = datosDestino.getBytes();
        outCliente.writeUTF(datosDestino);
    }

    public void enviarPaquetes(vista.InterfazClienteEnviar1 interfaz) throws IOException {
        // Se crea un array de tipo byte con el tamano del archivo 
        interfaz.cambioIP(direccionCliente);
        byte[] buffer = new byte[2048]; //8x256
        int paq = 0;
        int bytesRead;
        while ((bytesRead = inCliente.read(buffer)) > 0) {
            interfaz.cambioStatus("Enviando paquetes...");
            outCliente.write(buffer, 0, bytesRead);
            paq++;
            System.out.println("ClienteEnviar >> Paquete: " + paq + " Bytes enviados: " + bytesRead);
            bytesRead = 0;
        } 
        interfaz.cambioStatus("Archivo correctamente enviado");
        System.out.println("ClienteEnviar >> Termino el envio del Archivo: " + archOrigen.getName());
        desconectar();
    }

    public void desconectar() throws IOException {
        if (canal != null) {
            inCliente.close();
            inCliente = null;

            outCliente.close();
            outCliente = null;

            canal.close();
            canal = null;
        }
    }
    
    public void dispose() {
        dispose();
        System.exit(0);
    }
    
    
                                           

}