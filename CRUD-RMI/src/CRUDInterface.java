/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author megus
 */
public interface CRUDInterface extends Remote{
    public int guardar(Persona p) throws RemoteException;
    public int eliminar(String nombre) throws RemoteException;
    public int actualizar(Persona p) throws RemoteException;
    public List<Persona> conseguirTodos() throws RemoteException;
    public Persona conseguirUno(String nombre) throws RemoteException;
}
