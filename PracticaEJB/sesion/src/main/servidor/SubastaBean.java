package servidor;

import java.rmi.RemoteException;
import javax.naming.InitialContext;
import javax.naming.Context;
import javax.ejb.EJBException;

import java.util.Collection;

import java.util.Hashtable;
import java.util.Vector;

public class SubastaBean implements javax.ejb.SessionBean {
    public void ejbCreate(String clave) {

		this.setClave(clave);

    	productos = new Hashtable<>();
        ofertas = new Hashtable<>();
        usuarios=new Hashtable<>();
        try {
            registry = LocateRegistry.getRegistry();
        } catch (RemoteException ex) {
            Logger.getLogger(Tienda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean agregaProductoALaVenta(String vendedor, String producto, float precioInicial) {
	try {
	    javax.naming.Context jndiContext = new InitialContext();
	    Object ref = 
		jndiContext.lookup("java:comp/env/ejb/ProductoHomeRemote");

	    ProductoHome home = (ProductoHome)
		javax.rmi.PortableRemoteObject.narrow(ref,
						      ProductoHome.class);
	    
	    ProductoRemote prod = home.create(producto);
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

	public synchronized boolean agregaOferta(String comprador, String producto, float monto) throws RemoteException {
		try {
	    javax.naming.Context jndiContext = new InitialContext();
	    Object ref = 
		jndiContext.lookup("java:comp/env/ejb/ProductoHomeRemote");

	    ProductoHome home = (ProductoHome)
		javax.rmi.PortableRemoteObject.narrow(ref,
						      ProductoHome.class);
	    
	    ProductoRemote prod = home.findByPrimaryKey(producto);
	    prod.setPrecioActual(monto);

	    System.out.println("El producto ha sido agregado " + producto);
	    return true;

	} catch (Exception e) {
	    System.out.println("El producto no ha podido ser agregado\n"+ e);
            return false;
	}


        if (productos.containsKey(producto)) {

            Producto infoProd;
            infoProd = (Producto) productos.get(producto);
            Oferta nueva=new Oferta(comprador, producto, monto);
            if (infoProd.actualizaPrecio(monto)) {
                ofertas.put(producto + comprador, nueva );
                mandarOfertaAClientes(nueva);
                return true;

            } else {
                return false;
            }
        } else {
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

    private void mandarOfertaAClientes(Oferta nuevo)
    {
        boolean success;
        ClienteInterface stub;
        for(String key: usuarios.keySet())
        {
            stub=usuarios.get(key);
            success=false;
            for(int i=0;i<2;i++)
            {
                try {
                    success=stub.mandarPrecioNuevo(nuevo.producto, nuevo.monto);
                } catch (RemoteException ex) {
                    success=false;
                }
                if(success)
                {
                    break;
                }
            }
            if(!success)
            {
                try {
                    this.borrarUsuario(key);
                } catch (RemoteException ex) {
                    Logger.getLogger(Tienda.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
    }

     private void mandarProductoAClientes(Producto nuevo)
    {
        boolean success;
        ClienteInterface stub;
        for (String key:usuarios.keySet())
        {
            stub=usuarios.get(key);
            success=false;
            for(int i=0;i<2;i++)
            {
                try {
                    success=stub.mandarProductoNuevo(nuevo);
                } catch (RemoteException ex) {
                    success=false;
                }
                if(success)
                {
                    break;
                }
            }
            if(!success)
            {
                try {
                    this.borrarUsuario(key);
                } catch (RemoteException ex) {
                    //Logger.getLogger(Tienda.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
    }


	public static void main(String[] args) {
        try {
            Context jndiContext = new InitialContext();
            Object ref = jndiContext.lookup("SubastaEJB");

            SubastaHome home = (SubastaHome)
                PortableRemoteObject.narrow(ref,
					    SubastaHome.class);

            SubastaRemote sesion = home.create("Tienda");

	    

        } catch (Exception e) {
            System.out.println("Oops ... hubo una excepcion!\n"+e);
        }
    
    }    

    // Debido a que este es un Bean de sesion stateless, no necesitamos
    // agregar algun codigo especial para los siguientes metodos
    public void ejbRemove(){}
    public void ejbActivate(){}
    public void ejbPassivate(){}
    public void setSessionContext(javax.ejb.SessionContext cntx){}
    public abstract void setClave(String clave);
    public abstract String getClave();
}
