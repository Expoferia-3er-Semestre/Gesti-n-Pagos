package sistemaInventario.presentacion;

import sistemaInventario.datos.IProductosDAO;
import sistemaInventario.datos.ProductosDAO;
import sistemaInventario.dominio.Producto;

import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.Scanner;

public class Funciones {

    LinkedHashMap<Integer, Producto> inventario=new LinkedHashMap<>();
    Validar validar=new Validar();
    Scanner scan=new Scanner(System.in);
    IProductosDAO productosDAO=new ProductosDAO();

    public int menu() {

        int menu;
        System.out.print("""
                
                1. Lista de Productos
                2. Agregar Producto
                3. Modificar Producto
                4. Eliminar Producto
                5. Salir del sistema
                """);

        while (true) {
                try {
                    System.out.print("\nIngrese su opcion: ");
                    menu=scan.nextInt();
                    return menu;
                } catch (InputMismatchException e) {
                    System.out.println("\nError: \""+e+"\". Solo se permiten numeros enteros.");
                    scan.nextLine();
                }

        }

    } //Imprime el menu y recibe la opcion elegida

    public boolean listarProductos(boolean listaFlag) {

        if (!listaFlag) {
            inventario=productosDAO.listarProducto();
            System.out.println("\n+-----------------------------------------------------------------------+");
            System.out.printf("| %-5s | %-20s | %-10s | %-8s | %-15s |\n",
                    "ID", "Nombre", "Precio", "Cantidad", "Categoría");
            System.out.println("+-----------------------------------------------------------------------+");

            inventario.forEach((id, producto) -> System.out.printf("| %-5d | %-20s | %-10.2f | %-8d | %-15s |\n", id, producto.getNombre(),
                    producto.getPrecio(), producto.getCantidad(), producto.getCategoria()));

            System.out.println("+-----------------------------------------------------------------------+");
            return true;
        } else {
            System.out.println("+-----------------------------------------------------------------------+");
            System.out.printf("| %-5s | %-20s | %-10s | %-8s | %-15s |\n",
                    "ID", "Nombre", "Precio", "Cantidad", "Categoría");
            System.out.println("+-----------------------------------------------------------------------+");

            inventario.forEach((id, producto) -> System.out.printf("| %-5d | %-20s | %-10.2f | %-8d | %-15s |\n", id, producto.getNombre(),
                    producto.getPrecio(), producto.getCantidad(), producto.getCategoria()));

            System.out.println("+-----------------------------------------------------------------------+");
        }
        return false;

    }

    public void agregarProductos() {

        String nombre=validar.nombre("Ingrese el nombre del producto: ");
        double precio=validar.precio("Ingrese el precio del producto: ");
        int cantidad=validar.cantidad("Ingrese la cantidad del producto: ");
        String categoria=validar.categoria("Ingrese la categoria del producto: ");
        int confirmacionAdd=productosDAO.agregarProducto(nombre, precio, cantidad, categoria);
        if (confirmacionAdd>0) {
            System.out.println("Producto agregado exitosamente.");
            inventario.put(confirmacionAdd, new Producto(confirmacionAdd, nombre, precio, cantidad, categoria));
        } else {
            System.out.println("Hubo un error al agregar el producto.");
        }

    }

    public void modificarProducto() {

        int idMod=validar.id("Ingrese el ID del producto a modificar: ");
        int menuMod=0;
        Optional<Producto> producto=productosDAO.buscarProductoPorID(idMod);

        if (producto.isPresent()) {
            Producto prod=producto.get();
                try {
                    while (menuMod!=6) {
                        System.out.print("""
                        Que desea hacer?
                        1. Cambiar Nombre
                        2. Cambiar Precio
                        3. Cambiar Cantidad
                        4. Cambiar Categoria
                        5. Guardar
                        6. Salir
                        
                        Ingrese su eleccion:
                        """);

                        menuMod=scan.nextInt();

                        switch (menuMod) {

                            case 1:
                                String nombreMod=validar.nombre("Ingrese el nuevo nombre del producto: ");
                                prod.setNombre(nombreMod);
                                break;

                            case 2:
                                double precioMod=validar.precio("Ingrese el nuevo precio del producto: ");
                                prod.setPrecio(precioMod);
                                break;

                            case 3:
                                int cantidadMod =validar.cantidad("Ingrese la nueva cantidad del producto: ");
                                prod.setCantidad(cantidadMod);
                                break;

                            case 4:
                                String categoriaMod =validar.categoria("Ingrese la nueva categoria del producto: ");
                                prod.setCategoria(categoriaMod);
                                break;

                            case 5:
                                boolean confirmacionMod=productosDAO.modificarProducto(Optional.of(prod),
                                        prod.getId(),
                                        prod.getNombre(),
                                        prod.getPrecio(),
                                        prod.getCantidad(),
                                        prod.getCategoria());

                                if (confirmacionMod) {
                                    System.out.println("Producto modificado exitosamente.");
                                    inventario.put(idMod, prod);
                                }
                                menuMod=6;
                                break;

                            case 6:
                                break;

                            default:
                                System.out.println("Ingrese un numero dentro del rango del menu (1-6)");
                                scan.nextLine();
                        }

                    }

                }catch (InputMismatchException e) {
                    System.out.println("Ingrese solo numeros enteros.");
                    scan.nextLine();
                }
        }
        System.out.println("Saliendo del modificador de productos...");

    }

    public void eliminarProducto() {

        int idDel=validar.id("Ingrese el ID del producto a eliminar: ");
        Optional<Producto> prodDel=productosDAO.buscarProductoPorID(idDel);

        if (prodDel.isPresent()) {

            boolean confirmacionDel=productosDAO.eliminarProducto(idDel);

            if (confirmacionDel) {
                System.out.println("Producto eliminado exitosamente");
                inventario.remove(idDel);
            }

        }
        System.out.println("Saliendo del eliminador de productos...");

    }

}
