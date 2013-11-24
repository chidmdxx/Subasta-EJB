package servidor;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.EJBObject;
import javax.ejb.FinderException;

public interface SubastaRemote extends EJBObject {
	public boolean registraUsuario( String nombre ) throws RemoteException;
    public boolean borrarUsuario(String nombre) throws RemoteException;
    public boolean agregaProductoALaVenta( String vendedor, String producto,float precioInicial )throws RemoteException;
    public boolean agregaOferta(String comprador, String producto, float monto)throws RemoteException;
    public Collection obtieneCatalogo() throws RemoteException;
    public String getClave() throws RemoteException;
    public void setClave(String clave) throws RemoteException;
}
