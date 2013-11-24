package cliente;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.FinderException;

import java.util.Collection;

public interface ClientHome extends javax.ejb.EJBHome 
{
   public ClienteRemote create(String clave)
      throws CreateException, RemoteException;

   public ClienteRemote findByPrimaryKey(String clave)
      throws FinderException, RemoteException;

   
}
