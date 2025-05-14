/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Modelo;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JOELABRAHAMPLUASNUÑE
 */
public class ProveedorDaoTest {

    private ProveedorDao proveedorDao;

    @Before
    public void setUp() {
        proveedorDao = new ProveedorDao();
    }
    
    //Este metodo de de prueba exitosa, si el proveedor se registra correctamente la prueba pasa
    @Test
    public void testRegistrarProveedor() {
        Proveedor proveedor = new Proveedor();
        proveedor.setRuc("1234567890123");
        proveedor.setNombre("Joel Pluas");
        proveedor.setTelefono("0987897621");
        proveedor.setDireccion("Fertiza");

        boolean resultado = proveedorDao.RegistrarProveedor(proveedor);
        assertTrue("El proveedor debería registrarse correctamente", resultado);
    }
    
    //En esta prueba lo que se espera es un fallido, ya que le proveedor no se registro correctamente.
    @Test
    public void testRegistrarProveedorFallido() {
    Proveedor proveedor = new Proveedor();
    proveedor.setRuc("1111");
    proveedor.setNombre("Proveedor Test Fallido");
    proveedor.setTelefono("0999999999");
    proveedor.setDireccion("Dirección Fallida");

    boolean resultado = proveedorDao.RegistrarProveedor(proveedor);
    assertTrue("El proveedor no debería registrarse con un RUC vacío", resultado);
}
    //Prueba de error, tratara de eliminar un proveedor inexistente, en este caso con su ID /999s
    @Test
    public void testEliminarProveedorInexistente() {
        int idInexistente = 999s; 
        boolean resultado = proveedorDao.EliminarProveedor(idInexistente);
        assertFalse("No se debería poder eliminar un proveedor inexistente", resultado);
    }

    @Test
    public void testListarProveedorCorrecto() {
    List<Proveedor> proveedores = proveedorDao.ListarProveedor();
    assertNotNull("La lista de proveedores no debería ser nula", proveedores);
    assertTrue("La lista de proveedores debería tener elementos", proveedores.size() > 0);
}

    @Test
    public void testEliminarProveedor() {
        // Primero, registramos un proveedor para eliminar
        Proveedor proveedor = new Proveedor();
        proveedor.setRuc("098765432");
        proveedor.setNombre("Proveedor a Eliminar");
        proveedor.setTelefono("0988888888");
        proveedor.setDireccion("sdas 121");
        proveedorDao.RegistrarProveedor(proveedor);

        // Obtenemos la lista para encontrar el ID del último proveedor
        List<Proveedor> lista = proveedorDao.ListarProveedor();
        int id = lista.get(lista.size() - 1).getId();

        boolean resultado = proveedorDao.EliminarProveedor(id);
        assertTrue("El proveedor debería eliminarse correctamente", resultado);
    }

    @Test
    public void testModificarProveedor() {
        // Registramos un proveedor para modificar
        Proveedor proveedor = new Proveedor();
        proveedor.setRuc("1122334455");
        proveedor.setNombre("Proveedor a Modificar");
        proveedor.setTelefono("0977777777");
        proveedor.setDireccion("Dirección Original");
        proveedorDao.RegistrarProveedor(proveedor);

        // Obtenemos la lista para encontrar el ID del último proveedor
        List<Proveedor> lista = proveedorDao.ListarProveedor();
        Proveedor ultimoProveedor = lista.get(lista.size() - 1);

        // Modificamos los datos del proveedor
        ultimoProveedor.setNombre("Proveedor Modificado");
        ultimoProveedor.setDireccion("Nueva Dirección");

        boolean resultado = proveedorDao.ModificarProveedor(ultimoProveedor);
        assertTrue("El proveedor debería modificarse correctamente", resultado);
    }
}