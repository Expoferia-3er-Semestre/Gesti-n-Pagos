package sistemaInventario.datos;

import java.util.LinkedHashMap;
import java.util.Optional;

import sistemaInventario.dominio.Producto;

public interface IProductosDAO {

    LinkedHashMap<Integer, Producto> listarProducto();

    Optional<Producto> buscarProductoPorID(int id);

    int agregarProducto(String nombre, double precio, int cantidad, String categoria);

    boolean modificarProducto(Optional<Producto> producto, int id, String nombre, double precio, int cantidad, String categoria);

    boolean eliminarProducto(int idDel);

}
