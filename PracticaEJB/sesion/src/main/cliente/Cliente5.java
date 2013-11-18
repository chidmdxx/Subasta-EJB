package cliente;

import servidor.SubastaHome;
import servidor.SubastaRemote;

import javax.naming.InitialContext;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import java.rmi.RemoteException;

public class Cliente5 {
    public static void main(String args[]) {
        try {
            Context jndiContext = new InitialContext();
            Object ref = jndiContext.lookup("SubastaEJB");

            SubastaHome home = (SubastaHome)
                PortableRemoteObject.narrow(ref,
					    SubastaHome.class);

            SubastaRemote sesion = home.create();

	    sesion.agregaProductoALaVenta("pdct1", "Enrique", "Auto", 100000f);
	    sesion.agregaProductoALaVenta("pdct2", "Beto", "Bici", 1000f);

        } catch (Exception e) {
            System.out.println("Oops ... hubo una excepcion!\n"+e);
        }
    }
}
