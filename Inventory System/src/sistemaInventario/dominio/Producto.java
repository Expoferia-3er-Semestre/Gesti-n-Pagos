package sistemaInventario.dominio;

import java.util.Objects;

public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int cantidad;
    private String categoria;

    public Producto() {

    }
    
    public Producto(int id) {
        this.id=id;
    }

    public Producto(String nombre, double precio, int cantidad, String categoria) {
        this.nombre=nombre;
        this.precio=precio;
        this.cantidad=cantidad;
        this.categoria=categoria;
    }

    public Producto(int id, String nombre, double precio, int cantidad, String categoria) {
        this(nombre, precio, cantidad, categoria);
        this.id=id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return String.format("| %-5d | %-20s | %-10.2f | %-8d | %-15s |",
                id, nombre, precio, cantidad, categoria);
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return id == producto.id && Double.compare(precio, producto.precio) == 0 && cantidad == producto.cantidad && Objects.equals(nombre, producto.nombre) && Objects.equals(categoria, producto.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, precio, cantidad, categoria);
    }

}
