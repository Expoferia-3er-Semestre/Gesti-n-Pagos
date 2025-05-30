package sistemaInventario.datos;

import sistemaInventario.dominio.Producto;

import java.sql.SQLException;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static sistemaInventario.conexion.Conexion.getConexion;

public class ProductosDAO implements IProductosDAO{
    
    @Override
    public LinkedHashMap<Integer, Producto> listarProducto() {
        LinkedHashMap<Integer, Producto> productos=new LinkedHashMap<>();

        var sql="SELECT * FROM productos ORDER BY id";
        try (Connection con= getConexion();
             PreparedStatement ps= con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()){

            while (rs.next()) {
                Producto producto=new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("cantidad"),
                        rs.getString("categoria")
                );
                productos.put(producto.getId(), producto);
            }
        } catch (Exception e) {
            System.out.println("Error al listar productos: "+e);
        }
        return productos;

    }

    @Override
    public Optional<Producto> buscarProductoPorID(int id) {

        String sql="SELECT * FROM productos WHERE id = ?"; //Aqui se guarda la instruccion para SQL, el signo de interrogacion es como una variable (x)

        //Se usan try-with-resources para inicializar y mantener los recursos durante el try
        try (Connection con=getConexion();
        PreparedStatement ps=con.prepareStatement(sql) ){

            ps.setInt(1, id); //El primer parametro representa la variable de la instruccion (en este caso la primera y unica)
            //La segunda, representa el valor de esa variable, en este caso buscar productos que coincidan con ese ID

            try (ResultSet rs=ps.executeQuery()){
                //Si en el siguiente row hay una coincidencia con el ID
                if (rs.next()) {

                    Producto producto=new Producto(id,
                            rs.getString("nombre"),
                            rs.getDouble("precio"),
                            rs.getInt("cantidad"),
                            rs.getString("categoria"));

                    System.out.println("Producto encontrado: "+producto);
                    return Optional.of(producto); //Retornamos el producto encontrado para ser modificado, eliminado o etc.

                } else {
                    System.out.println("EL producto no existe en la base de datos.");
                }
            } catch (SQLException e) {
                System.out.println("Error al recuperar el producto: "+e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Error al recuperar producto por ID: "+e);
        }
        return Optional.empty(); //Retorna un valor vacio si no hay ninguna coincidencia, para poder evitar el acceso a otros menus

    }

    @Override
    public int agregarProducto(String nombre, double precio, int cantidad, String categoria) {

        String sql="INSERT INTO productos(nombre, precio, cantidad, categoria) " +
                " VALUES(?, ?, ?, ?)"; //En este caso hay 4 variables

        try (Connection con=getConexion();
             PreparedStatement ps= con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){

            ps.setString(1, nombre);
            ps.setDouble(2, precio);
            ps.setInt(3, cantidad);
            ps.setString(4, categoria);
            int filasAfectadas=ps.executeUpdate();

            if (filasAfectadas>0) {
                try (ResultSet rs= ps.getGeneratedKeys()){
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error al agregar producto: "+e.getMessage());
        }
        return -1;

    }

    @Override
    public boolean modificarProducto(Optional<Producto> producto, int id, String nombre, double precio, int cantidad, String categoria) {

        var sql="UPDATE productos SET nombre=?, precio=?, cantidad=?, categoria=? "+
                " WHERE id = ?";

        try (Connection con=getConexion();
             PreparedStatement ps= con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setDouble(2, precio);
            ps.setInt(3, cantidad);
            ps.setString(4, categoria);
            ps.setInt(5, id);
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al modificar el cliente: "+e);
        }

        return false;
    }

    @Override
    public boolean eliminarProducto(int idDel) {

        String sql="DELETE FROM productos WHERE id = ?";

        try (Connection con=getConexion();
        PreparedStatement ps= con.prepareStatement(sql)){

            ps.setInt(1, idDel);
            ps.execute();
            return true;

        } catch (Exception e) {
            System.out.println("Error al eliminar producto: "+e);
        }

        return false;
    }

}
