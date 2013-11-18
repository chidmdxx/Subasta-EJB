package servidor;

import java.rmi.RemoteException;
import javax.naming.InitialContext;
import javax.naming.Context;
import javax.ejb.EJBException;

import java.util.Collection;

public class SubastaBean implements javax.ejb.SessionBean {
    public void ejbCreate() {
    }

    public boolean agregaProductoALaVenta(String clave,
					  String vendedor,
					  String producto,
                                          float precioInicial) {
	try {
	    javax.naming.Context jndiContext = new InitialContext();
	    Object ref = 
		jndiContext.lookup("java:comp/env/ejb/ProductoHomeRemote");

	    ProductoHome home = (ProductoHome)
		javax.rmi.PortableRemoteObject.narrow(ref,
						      ProductoHome.class);
	    
	    ProductoRemote prod = home.create(clave);
            prod.setNombre(producto);
	    prod.setPrecioInicial(precioInicial);
	    prod.setPrecioActual(precioInicial);
	    prod.setVendedor(vendedor);

	    System.out.println("El producto ha sido agregado " + producto);
	    return true;

	} catch (Exception e) {
	    System.out.println("El producto no ha podido ser agregado\n"+ e);
            return false;
	}
    }

    public Collection obtieneCatalogo() {
	try {
	    javax.naming.Context jndiContext = new InitialContext();

	    Object ref =
		jndiContext.lookup("java:comp/env/ejb/ProductoHome");

	    ProductoHome home = (ProductoHome)
		javax.rmi.PortableRemoteObject.narrow(ref,
						      ProductoHome.class);
	    return home.findAll();
	} catch (Exception e) {
	    System.out.println("No se ha podido accesar al catalogo\n"+ e);
            return null;
	}
    }


    // Debido a que este es un Bean de sesion stateless, no necesitamos
    // agregar algun codigo especial para los siguientes metodos
    public void ejbRemove(){}
    public void ejbActivate(){}
    public void ejbPassivate(){}
    public void setSessionContext(javax.ejb.SessionContext cntx){}
}
