package cliente;

import servidor.ProductoHome;
import servidor.ProductoRemote;

import javax.naming.InitialContext;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import java.rmi.RemoteException;

public class Cliente11 {
    public static void main(String args[]) {
        try {
            Context jndiContext = new InitialContext();
            Object ref = jndiContext.lookup("ProductoEJB");

            ProductoHome home = (ProductoHome)
                PortableRemoteObject.narrow(ref, ProductoHome.class);

            ProductoRemote producto = home.findByPrimaryKey("prd1");
            System.out.println(producto.getNombre());
            System.out.println(producto.getPrecioInicial());
            System.out.println(producto.getPrecioActual());
            System.out.println(producto.getVendedor());
        } catch (Exception e) {
            System.out.println("Oops ... hubo una excepcion!\n"+e);
        }
    }
}
