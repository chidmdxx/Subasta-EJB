package servidor;

import javax.ejb.EntityContext;
import javax.ejb.CreateException;

public abstract class ProductoBean 
   implements javax.ejb.EntityBean 
{

   public String ejbCreate(String clave) throws CreateException
   {
      this.setClave(clave);
      return null;
   }
   public void ejbPostCreate(String clave)
   {
   }
   
   ////////////////////////////////////////////////
   /// A continuacion, incluimos los metodos abstractos
   /// de los setters y getters para cada uno de los
   /// atributos del bean.
   ////////////////////////////////////////////////
   public abstract void setClave(String clave);
   public abstract String getClave();

   public abstract void setNombre(String nombre);
   public abstract String getNombre();
 
   public abstract void setPrecioInicial(float precio);
   public abstract float getPrecioInicial();

   public abstract void setPrecioActual(float precio);
   public abstract float getPrecioActual();

   public abstract void setVendedor(String vendedor);
   public abstract String getVendedor();


   ////////////////////////////////////////////////////////
   // Debido a que utilizamos beans de entidad con
   // persistencia manejada por el contenedor, los
   // siguientes metodos no necesitan ser implementados
   ////////////////////////////////////////////////////////
   public void setEntityContext(EntityContext ctx) 
   {
   }
   public void unsetEntityContext() 
   {
   }
   public void ejbActivate() 
   {
   }
   public void ejbPassivate() 
   {
   }
   public void ejbLoad() 
   {
   }
   public void ejbStore() 
   {
   }
   public void ejbRemove() 
   {
   }
}
