
package controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.ClientesVO;
import java.util.ArrayList;

public class ControlClientesDAO {


    private Connection con;
    private Statement st;
    private ResultSet rs;

    public ControlClientesDAO() {
        con = null;
        st = null;
        rs = null;
    }
    
    //consulta un estudiante
    public ClientesVO consultarClientes(String ip) {
        ClientesVO cliente = null;
        String consulta = "SELECT * FROM Clientes where ip='" +ip+"'";
        try {
            con = (Connection) Conexion.getConexion();
            st = con.createStatement();
            rs = st.executeQuery(consulta);
            if (rs.next()) {
                cliente = new ClientesVO();
                cliente.setIp(rs.getString("ip"));
                cliente.setNick(rs.getString("nickname"));
            }
            st.close();
            Conexion.desconectar();
        } catch (SQLException ex) {
            System.out.println("No se pudo realizar la consulta");
        }
        return cliente;
    }
    
    //todos los estudiantes
    public ArrayList<ClientesVO> listaDeClientes() {
        ArrayList<ClientesVO> misClientes = new ArrayList<ClientesVO>();
        String consulta = "SELECT * FROM Clientes";
        try {
            con = Conexion.getConexion();
            st = con.createStatement();
            rs = st.executeQuery(consulta);
            while (rs.next()) {
                ClientesVO estudiante = new ClientesVO();
                estudiante.setIp(rs.getString("ip"));
                estudiante.setNick(rs.getString("nombre"));
                misClientes.add(estudiante);
            }
            st.close();
            Conexion.desconectar();
        } catch (SQLException ex) {
            System.out.println("No se pudo realizar la consulta");
        }
        return misClientes;
    }
    
    public void insertarDatos(ClientesVO cliente){
        try{
            con=Conexion.getConexion();
            st=con.createStatement();
            String insercion = "INSERT INTO Clientes VALUES('"
                    +"','"+cliente.getNick()+"',"+cliente.getIp()+")";
            st.executeUpdate(insercion);
            st.close();
            Conexion.desconectar();
        } catch(SQLException ex){
            System.out.print("No se pudo realizar la insercion");
        }
    }
    
}
    

