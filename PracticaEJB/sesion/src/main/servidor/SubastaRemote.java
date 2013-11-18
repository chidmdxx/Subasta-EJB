package servidor;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.EJBObject;
import javax.ejb.FinderException;

public interface SubastaRemote extends EJBObject {
    public boolean agregaProductoALaVenta(String clave,
                                          String vendedor,
					  String producto,
                                          float precioInicial)
	throws RemoteException;
    public Collection obtieneCatalogo()
	throws RemoteException;
}
