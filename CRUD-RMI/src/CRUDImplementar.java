/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author megus
 */
public class CRUDImplementar extends UnicastRemoteObject implements CRUDInterface{
    
    private static Connection connect;
    
    public CRUDImplementar() throws RemoteException{
        
    }

    @Override
    public int guardar(Persona p) throws RemoteException {
        int status = 0;
        
        try {
            Connection connect = Conexion.getConnection();
            
            String sql = "INSERT INTO persona (Nombre, Edad, Sexo) values (?, ?, ?)";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setInt(2, p.getEdad());
            ps.setString(3, String.valueOf(p.getSexo()));
            
            status = ps.executeUpdate();
            connect.close();
            
        } catch (Exception e) {
            System.out.println("Error al guardar a la persona: "+e);
        }
        
        return status;
    }

    @Override
    public int eliminar(String nombre) throws RemoteException {
        int status = 0;
        
        try {
            Connection connect = Conexion.getConnection();
            
            String sql = "DELETE FROM persona WHERE Nombre = ?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, nombre);
            
            status = ps.executeUpdate();
            connect.close();
            
        } catch (Exception e) {
            System.out.println("Error al eliminar a la persona: "+e);
        }
        
        return status;
    }

    @Override
    public int actualizar(Persona p) throws RemoteException {
        int status = 0;
        
        try {
            Connection connect = Conexion.getConnection();
            
            String sql = "UPDATE persona SET Nombre = ?, Edad = ?, Sexo = ? ";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setInt(2, p.getEdad());
            ps.setString(3, String.valueOf(p.getSexo()));
            
            status = ps.executeUpdate();
            connect.close();
            
        } catch (Exception e) {
            System.out.println("Error al actualizar a la persona: "+e);
        }
        
        return status;
    }

    @Override
    public List<Persona> conseguirTodos() throws RemoteException {
        List<Persona> lista = new ArrayList<Persona>();
        int status = 0;
        
        try {
            Connection connect = Conexion.getConnection();
            
            String sql = "SELECT * FROM persona";
            PreparedStatement ps = connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Persona p = new Persona();
                
                p.setNombre(rs.getString(1));
                p.setEdad(rs.getInt(2));
                p.setSexo(rs.getString(3).charAt(0));
                
                lista.add(p);
            }

            connect.close();
            
        } catch (Exception e) {
            System.out.println("Error al conseguir a las personas: "+e);
        }
        
        return lista;
    }

    @Override
    public Persona conseguirUno(String nombre) throws RemoteException {
        Persona p = new Persona();
        
        try {
            Connection connect = Conexion.getConnection();
            
            String sql = "SELECT * from persona WHERE nombre = ?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                p.setNombre(rs.getString(1));
                p.setEdad(rs.getInt(2));
                p.setSexo(rs.getString(3).charAt(0));
            }
            
            connect.close();
            
        } catch (Exception e) {
            System.out.println("Error al conseguir a la persona: "+e);
        }
        
        return p;
    }
    
}
