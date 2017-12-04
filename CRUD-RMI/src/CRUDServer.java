/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static java.lang.System.setSecurityManager;
import java.rmi.RMISecurityManager;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author megus
 */
public class CRUDServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException{       
        Registry reg = LocateRegistry.createRegistry(8080);
        CRUDImplementar crud = new CRUDImplementar();
        reg.rebind("crud", crud);
        
        System.out.println("El servidor se ha inicializado correctamente!");
    }
    
}
