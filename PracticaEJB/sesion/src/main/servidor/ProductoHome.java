package servidor;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.FinderException;

import java.util.Collection;

public interface ProductoHome extends javax.ejb.EJBHome 
{
   public ProductoRemote create(String clave)
      throws CreateException, RemoteException;

   public ProductoRemote findByPrimaryKey(String clave)
      throws FinderException, RemoteException;

   public Collection findByName(String nombre)
      throws FinderException, RemoteException;

    public Collection findAll()
      throws FinderException, RemoteException;
}
