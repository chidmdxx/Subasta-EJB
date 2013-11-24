package cliente;

import java.rmi.RemoteException;

public interface ClienteRemote extends javax.ejb.EJBObject 
{
   public boolean mandarPrecioNuevo( String producto, float nuevoPrecio ) throws RemoteException;
   public boolean mandarProductoNuevo(Producto producto) throws RemoteException;
   public String getClave() throws RemoteException;
   public void setClave(String clave) throws RemoteException;
}
