package controlador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Properties;
import vista.InterfazClienteRecibir1;

public class ClienteRecibir {

    private InterfazClienteRecibir1 recibir;
    private String nombreCliente;
    private String direccionCliente;
    private int puertoCliente;
    private String direccionServidor;
    private int puertoServidor;
    private Socket canal;
    private DataInputStream inCliente;
    private DataOutputStream outCliente;
    private int tam;
    private File fileDestino;
    private String nombreArchivo;

    public ClienteRecibir(String archivoConfiguracion) throws Exception {
        cargarConfiguracion(archivoConfiguracion);
    }

    public Socket getCanal() {
        return canal;
    }

    private void cargarConfiguracion(String archivo) throws Exception {
        FileInputStream fis = new FileInputStream(archivo);
        Properties configuracion = new Properties();
        configuracion.load(fis);
        fis.close();
        direccionServidor = configuracion.getProperty("servidor.direccion");
        puertoServidor = Integer.parseInt(configuracion.getProperty("servidor.puerto"));
        direccionCliente = configuracion.getProperty("cliente.direccion");
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

    public void conectarRecibir(String nombre, InterfazClienteRecibir1 interfaz) throws IOException {
        canal = new Socket(direccionServidor, puertoServidor);

        // Se crea un flujo de salida, este flujo nos sirve para 
        // indicar donde guardaremos el archivo
        String nomArchivo = "src\\data\\" +nombre+ ".txt";
        interfaz.cambioRuta(nomArchivo);
        fileDestino = new File(nomArchivo);
        String datosDestino = "src\\data\\cliente1.properties";
        FileInputStream f = new FileInputStream(datosDestino);
        Properties configuracion = new Properties();
        configuracion.load(f);
        f.close();
        interfaz.cambioIP(configuracion.getProperty("cliente.direccion"));
        interfaz.cambioStatus("Conectando..");
        //fileDestino.setReadable(true);
        //fileDestino.setWritable(true);
        FileOutputStream fos = new FileOutputStream(nomArchivo);

        //se crean los flujose de entrada y salida
        //el flujo de salida escribe en el archivo
        outCliente = new DataOutputStream(fos);
        //el flujo de entrada lee desde el canal
        inCliente = new DataInputStream(canal.getInputStream());
    }

    public void recibirPaquetes(vista.InterfazClienteRecibir1 interfaz) throws IOException {
        System.out.println("ClienteRecibir >> Empieza la recepcion del archivo");
        // Se crea el array de bytes para leer los datos del archivo
        byte[] buffer = new byte[2048]; //8x256
        int paq = 1;
        int bytesRead;
        while ((bytesRead = inCliente.read(buffer)) > 0) {
            outCliente.write(buffer, 0, bytesRead);
            System.out.println("ClienteRecibir >> Paquete: " + paq + " --> Bytes recibidos: " + bytesRead);
            paq++;
            bytesRead = 0;
            interfaz.cambioStatus("Archivo Recibido");
        }
        System.out.println("ClienteRecibir >> Termino la Recepcion del Archivo");
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
}
