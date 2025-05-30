/*import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.LinkedHashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.io.IOException;

public static class Usuarios {

    static class Cajero {
        String numCaja;
        String nombreCajero;
        String contrasena;

        public Cajero(String numCaja, String nombreCajero, String contrasena) {
            this.numCaja = numCaja;
            this.nombreCajero = nombreCajero;
            this.contrasena = contrasena;
        }

        @Override
        public String toString() {
            return String.format("Caja: %s, Cajero: %s, Contraseña: %s");
        }
    }

    public static class Admin {
        String nombreAdmin;
        String adContrasena;

        public Admin(String nombreAdmin, String adContrasena) {
            this.nombreAdmin = nombreAdmin;
            this.adContrasena = adContrasena;
        }

        public String toString() {
            return String.format("Admin: %s, Contraseña: %s");
        }
    }

}//ADMINS y Cajeros

static class Json {

    static void cargarInventariojson(String inventarioArchivo) {
        Gson gson=new Gson();
        Type tipoMapa=new TypeToken<LinkedHashMap<Integer, Global.Producto>>() {}.getType();
        try (FileReader reader=new FileReader(inventarioArchivo)) {
            Global.inventario=gson.fromJson(reader, tipoMapa);
            System.out.println("\nInventario cargado exitosamente.");

            if (Global.inventario==null) {
                Global.inventario=new LinkedHashMap<>();
                System.out.println("El archivo Json estaba vacío, se inicializó un nuevo inventario.");
            }
        } catch (IOException e) {
            System.out.println("\nError al cargar inventario: "+e);
    }
    }

    static void cargarDatosUsuarios(String usuariosArchivo) {
        Gson gson=new Gson();
        Type tipoMapa=new TypeToken<LinkedHashMap<Integer, Usuarios.Cajero>>() {}.getType();
        try (FileReader reader=new FileReader(usuariosArchivo)){
            Global.cajero=gson.fromJson(reader, tipoMapa);
        } catch (IOException e) {
            System.out.println("Error al cargar datos de usuarios.");
        }
    }

    static void guardarDatosUsuarios(String usuariosArchivo) {

    }

    static void guardarFactura(String facturaArchivo){
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer=new FileWriter(facturaArchivo)){
            gson.toJson(Global.factura, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar la factura: "+e);
        }
    }
}

static class Global {

    static class Producto {
        String nombre;
        double precio;
        int cantidad;

        public Producto( String nombre, double precio, int cantidad){

            this.nombre=nombre;
            this.precio=precio;
            this.cantidad=cantidad;

        }
        @Override
        public String toString() {
            return String.format("Nombre: %s - Precio: %.2f - Cantidad: %d", nombre, precio, cantidad);
        }
    }

    static Scanner scan=new Scanner(System.in);
    static String inventarioArchivo="inventario.json";
    static String facturaArchivo="factura.json";
    static String usuariosArchivo="usuarios.json";
    static LinkedHashMap<Integer, Producto> inventario=new LinkedHashMap<>();
    static LinkedHashMap<Integer, Usuarios.Cajero> cajero=new LinkedHashMap<>();
    static LinkedHashMap<Integer, Usuarios.Admin> admin=new LinkedHashMap<>();
    static String factura;
}

public static void main(String[] ignoreArgs) {

    boolean bucle=true;

    while () {

        switch () {
            case :
            case :
            case :
            case :
            case :
        }
    }
}
*/