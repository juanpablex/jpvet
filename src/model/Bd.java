package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bd {

    private static Bd instancia = null;

    private final String usuario = "root";
    private final String contrasenia = "";
    private final String bd = "jpvet";
    private final String host = "localhost";
    private final String puerto = "3306";
    private final String url;
    private Statement sentencia = null;
    private Connection conexion = null;

    private Bd() {
        url = "jdbc:mysql://"+host+":"+puerto+"/" + bd + "?zeroDateTimeBehavior=convertToNull";
        Boolean error = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(url,usuario,contrasenia);
            if (conexion != null) {
                sentencia = conexion.createStatement();
                
            } else {
                error = true;
            }
        } catch (Exception ex) {
            error = true;
        } finally {
            if (error) {
                System.err.println("<Bd> No se logró conectar con el servidor "
                        + "de base de datos");
            } else {
                System.out.println("Conexión exitosa con: " + url);
            }
        }
    }
    public  void setAutoCommit(boolean b) throws SQLException{
        conexion.setAutoCommit(b);
    }
    public  void commit(){
        
        try {
            conexion.commit();
        } catch (SQLException ex) {
            Logger.getLogger(Bd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public  void rollback() {
        try {
            conexion.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(Bd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static void crearInstancia() {
        synchronized (Bd.class) {
            if (instancia == null) {
                instancia = new Bd();
            }
        }
    }
    public Connection getConn(){
        return conexion;
    }
    public static Bd getConexion() {
        if (instancia == null) {
            crearInstancia();
        }
        return instancia;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public ResultSet listar(String sql) {
        try {
            ResultSet res = sentencia.executeQuery(sql);
            System.out.println("<Bd> " + sql);
            return res;
        } catch (Exception ex) {
            System.err.println("<Bd> Hubo un problema al ejecutar una consulta");
            System.err.println("<Bd> CONSULTA: " + sql);
            System.err.println("<Bd> " + ex.getMessage());
            return null;
        }
    }

    private int ejecutarConsulta(String sql, Object... valores) {
        PreparedStatement ps = null;
        try {
            
            ps = conexion.prepareStatement(sql);
            for (int i = 0; i < valores.length; i++) {
                ps.setObject(i + 1, valores[i]);
            }
            int res = ps.executeUpdate();
            System.out.println("<Bd> " + ps.toString());
          
            return res;
        } catch (SQLException ex) {
           
            System.err.println("<Bd> Hubo un problema al ejecutar una consulta");
            if (ps != null) {
                System.err.println("<Bd> CONSULTA: " + ps.toString());
            }
            System.err.println("<Bd> " + ex.getMessage());
            return -1;
        }
    }
private int ejecutarBusqueda(String sql,String b, Object... valores) {
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, b);
            for (int i = 0; i < valores.length; i++) {
                ps.setObject(i + 1, valores[i]);
            }
            int res = ps.executeUpdate();
            System.out.println("<Bd> " + ps.toString());
            return res;
        } catch (SQLException ex) {
            System.err.println("<Bd> Hubo un problema al ejecutar una consulta");
            if (ps != null) {
                System.err.println("<Bd> CONSULTA: " + ps.toString());
            }
            System.err.println("<Bd> " + ex.getMessage());
            return -1;
        }
    }
    public int registrar(String tabla, String sql, Object... valores) {
        try {
            int res = ejecutarConsulta(sql, valores);
            if (res >= 0) {
                return res;
            }//else return -1;
           /* ResultSet rs = listar("SELECT ci FROM " + tabla + " ORDER BY ci DESC limit 1");
            if (rs == null) {
                return -1;
            }
            int idNuevo = 0;
            while (rs.next()) {
                idNuevo = rs.getInt("ci");
            }
            System.out.println("Inserción en " + tabla + " ci: " + idNuevo);
            return idNuevo;*/
        } catch (Exception ex) {
            System.err.println("<Bd.insert> " + ex.getMessage());
            return -1;
        }
        return -1;
    }

    public int modificar(String sql, Object... valores) {
        return ejecutarConsulta(sql, valores);
    }
    public int buscar(String sql, String b, Object... valores) {
        return ejecutarBusqueda(sql,b, valores);
    }
    public int eliminar(String sql, Object... valores) {
        return ejecutarConsulta(sql, valores);
    }

    public ResultSet getById(String sql) {
        try {
            ResultSet res = sentencia.executeQuery(sql);
            System.out.println("<Bd> " + sql);
            return res;
        } catch (Exception ex) {
            System.err.println("<Bd> Hubo un problema al ejecutar una consulta");
            System.err.println("<Bd> CONSULTA: " + sql);
            System.err.println("<Bd> " + ex.getMessage());
            return null;
        }
    }
}
