package cliente;

import servidor.ProductoHome;
import servidor.ProductoRemote;

import javax.naming.InitialContext;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import java.rmi.RemoteException;

public class Cliente1 {
    public static void main(String args[]) {
        try {
            Context jndiContext = new InitialContext();
            Object ref = jndiContext.lookup("ProductoEJB");

            ProductoHome home = (ProductoHome)
                PortableRemoteObject.narrow(ref, ProductoHome.class);

            ProductoRemote producto = home.create("prd1");
	    producto.setNombre("Auto");
            producto.setPrecioInicial(100000f);
            producto.setPrecioActual(100000f);
            producto.setVendedor("Juan");
        } catch (Exception e) {
            System.out.println("Oops ... hubo una excepcion!\n"+e);
        }
    }
}
