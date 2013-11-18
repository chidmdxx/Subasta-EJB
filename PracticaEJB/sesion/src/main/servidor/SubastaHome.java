package servidor;

import java.rmi.RemoteException;
import javax.ejb.CreateException;

public interface SubastaHome extends javax.ejb.EJBHome {

    public SubastaRemote create()
        throws RemoteException, CreateException;

}
