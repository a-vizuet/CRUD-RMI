/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author megus
 */
public class Conexion {
    
    private static Connection connect;
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String user = "sql9205680";
    private static final String password = "aCsbwbR97K";
    private static final String url = "jdbc:mysql://sql9.freemysqlhosting.net:3306/sql9205680";
    
    public Conexion() throws SQLException{
        connect = null;
        
        try {
            Class.forName(driver);
            connect = DriverManager.getConnection(url, user, password);
            
            if(connect != null) System.out.println("Conexión a la BD establecida...");
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al conectar a la BD: "+e);
        }
        
    }
    
    public static Connection getConnection(){
        Connection connection = null;
        
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("Error al regresar la conexión de la BD: "+e);
        }
        
        return connection;   
    }
    
    public static void desconectar(){
        connect = null;
        if(connect == null) System.out.println("Conexión a la BD terminada...");
    }
    
}
