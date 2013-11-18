package servidor;

import java.rmi.RemoteException;

public interface ProductoRemote extends javax.ejb.EJBObject 
{
   public String getClave() throws RemoteException;
   public void setClave(String clave) throws RemoteException;
   public String getNombre() throws RemoteException;
   public void setNombre(String nombre) throws RemoteException;
   public float getPrecioInicial() throws RemoteException;
   public void setPrecioInicial(float precio) throws RemoteException; 
   public float getPrecioActual() throws RemoteException; 
   public void setPrecioActual(float precio) throws RemoteException;
   public String getVendedor() throws RemoteException;
   public void setVendedor(String vendedor) throws RemoteException;
}
