package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase ProveedorDao para realizar operaciones CRUD sobre la tabla "proveedor".
 */
public class ProveedorDao {
    private Connection con;
    private final Conexion cn = new Conexion();
    private PreparedStatement ps;
    private ResultSet rs;

    /**
     * Método para registrar un proveedor en la base de datos.
     *
     * @param pr Objeto de tipo Proveedor con los datos a registrar.
     * @return true si el proveedor fue registrado correctamente, false en caso contrario.
     */
    public boolean RegistrarProveedor(Proveedor pr) {
        // Validaciones de los campos
        if (pr.getRuc() == null && pr.getNombre() == null && pr.getTelefono() == null && pr.getDireccion() == null) {
            System.out.println("Debe llenar todos los datos del proveedor.");
            return false;
        }

        if (pr.getRuc() != null && pr.getNombre() == null && pr.getTelefono() == null && pr.getDireccion() == null) {
            System.out.println("Debe llenar todos los datos del proveedor, no solo el RUC.");
            return false;
        }

        if (!esRucValido(pr.getRuc())) {
            System.out.println("RUC inválido: Debe tener exactamente 13 dígitos numéricos.");
            return false;
        }
        if (!esNombreValido(pr.getNombre())) {
            System.out.println("Nombre inválido: Solo se permiten letras.");
            return false;
        }
        if (!esTelefonoValido(pr.getTelefono())) {
            System.out.println("Teléfono inválido: Solo se permiten números.");
            return false;
        }
        if (!esDireccionValida(pr.getDireccion())) {
            System.out.println("Dirección inválida: Solo se permiten letras y números.");
            return false;
        }

        // SQL para insertar proveedor
        String sql = "INSERT INTO proveedor(ruc, nombre, telefono, direccion) VALUES (?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pr.getRuc());
            ps.setString(2, pr.getNombre());
            ps.setString(3, pr.getTelefono());
            ps.setString(4, pr.getDireccion());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    /**
     * Valida si el RUC ingresado cumple con el formato requerido.
     *
     * @param ruc RUC a validar.
     * @return true si el RUC es válido, false en caso contrario.
     */
    private boolean esRucValido(String ruc) {
        return ruc != null && ruc.matches("\\d{13}");
    }

    /**
     * Valida si el nombre ingresado cumple con el formato requerido.
     *
     * @param nombre Nombre a validar.
     * @return true si el nombre es válido, false en caso contrario.
     */
    private boolean esNombreValido(String nombre) {
        return nombre != null && nombre.matches("[a-zA-Z ]+");
    }

    /**
     * Valida si el teléfono ingresado cumple con el formato requerido.
     *
     * @param telefono Teléfono a validar.
     * @return true si el teléfono es válido, false en caso contrario.
     */
    private boolean esTelefonoValido(String telefono) {
        return telefono != null && telefono.matches("\\d+");
    }

    /**
     * Valida si la dirección ingresada cumple con el formato requerido.
     *
     * @param direccion Dirección a validar.
     * @return true si la dirección es válida, false en caso contrario.
     */
    private boolean esDireccionValida(String direccion) {
        return direccion != null && direccion.matches("[a-zA-Z0-9 ]+");
    }

    /**
     * Lista todos los proveedores registrados en la base de datos.
     *
     * @return Lista de objetos Proveedor.
     */
    public List<Proveedor> ListarProveedor() {
        List<Proveedor> Listapr = new ArrayList<>();
        String sql = "SELECT * FROM proveedor";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Proveedor pr = new Proveedor();
                pr.setId(rs.getInt("id"));
                pr.setRuc(rs.getString("ruc"));
                pr.setNombre(rs.getString("nombre"));
                pr.setTelefono(rs.getString("telefono"));
                pr.setDireccion(rs.getString("direccion"));
                Listapr.add(pr);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return Listapr;
    }

    /**
     * Elimina un proveedor de la base de datos.
     *
     * @param id Identificador del proveedor a eliminar.
     * @return true si el proveedor fue eliminado correctamente, false en caso contrario.
     */
    public boolean EliminarProveedor(int id) {
        String sql = "DELETE FROM proveedor WHERE id = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    /**
     * Modifica los datos de un proveedor existente en la base de datos.
     *
     * @param pr Objeto de tipo Proveedor con los datos modificados.
     * @return true si el proveedor fue modificado correctamente, false en caso contrario.
     */
    public boolean ModificarProveedor(Proveedor pr) {
        String sql = "UPDATE proveedor SET ruc=?, nombre=?, telefono=?, direccion=? WHERE id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pr.getRuc());
            ps.setString(2, pr.getNombre());
            ps.setString(3, pr.getTelefono());
            ps.setString(4, pr.getDireccion());
            ps.setInt(5, pr.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
}
