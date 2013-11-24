package servidor;

import java.rmi.RemoteException;
import javax.ejb.CreateException;

public interface SubastaHome extends javax.ejb.EJBHome {

    public SubastaRemote create(String clave)
        throws RemoteException, CreateException;

    public SubastaRemote findByPrimaryKey(String clave)
      throws FinderException, RemoteException;

}
