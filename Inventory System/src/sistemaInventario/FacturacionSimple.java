import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Scanner;

static class Inventario {

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

    static LinkedHashMap<Integer, Producto> inventario=new LinkedHashMap<>();
    static String inventarioArchivo="inventario.json";
    //static ArrayList<Producto> factura=new ArrayList<>();
    static String factura="";
    static double total;
    static String customer="";
    static boolean customerFlag=false;
    static boolean productosFlag=false;

    static void cargarInventariojson(String inventarioArchivo) {
        Gson gson=new Gson();
        Type tipoMapa=new TypeToken<LinkedHashMap<Integer, Producto>>() {}.getType();

        try (FileReader reader=new FileReader(inventarioArchivo)) {
            inventario=gson.fromJson(reader, tipoMapa);
            System.out.println("\nInventario cargado exitosamente.");

            if (inventario==null) {
                inventario=new LinkedHashMap<>();
                System.out.println("El archivo Json estaba vacío, se inicializó un nuevo inventario.");
            }
        } catch (IOException e) {
            System.out.println("\nError al cargar inventario: "+e);
        }
    }
}

static class Funciones {
    static Scanner scan=new Scanner(System.in);

    static int menu() {
        boolean bucle=true;
        int menu=0;
        System.out.println("\n*** Bienvenido al Sistema de Facturación ***");
        System.out.println("""
                1. Registrar Cliente
                2. Seleccionar Productos
                3. Ver Factura Temporal
                4. Generar Factura
                5. Salir del Sistema""");

        while (bucle) {
            try {
                System.out.print("Ingrese su opcion: ");
                menu=scan.nextInt();
                if (menu>=1 && menu<=6) {
                    bucle=false;
                } else {
                    System.out.println("Por favor, ingrese un numero dentro del rango (1-5).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, solo ingrese numeros para elegir la opcion.");
                scan.nextLine();
            }
        }
        return menu;
    }

    static void seleccionar(){
        int retry=0;
            while (retry!=2) {
                System.out.println("\n--- Lista de productos --- ");
                Inventario.inventario.entrySet().stream().
                        forEach(entrada-> //Recorre el inventario
                                System.out.println("ID: "+entrada.getKey()+", Producto: "+entrada.getValue()));//En cada iteracion, imprime esto
                System.out.println("--------------------------------------------------.");
                try {
                    System.out.print("Ingrese el ID del producto: ");
                    int idProducto=scan.nextInt();

                    if (Inventario.inventario.containsKey(idProducto)) {

                        Inventario.factura+="\n"+Inventario.inventario.get(idProducto);
                        Inventario.total+=Inventario.inventario.get(idProducto).precio;
                        System.out.println("Has agregado: "+Inventario.inventario.get(idProducto));
                        System.out.print("\nDesea agregar otro producto?\n1. Si\n2. No: ");
                        System.out.print("\nIngrese su opcion: ");
                        retry=scan.nextInt();
                    } else {
                        System.out.println("\nEl ID ingresado no existe.");
                        scan.nextLine();
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\nError de entrada: "+e+". Ingrese numeros enteros.");
                }
            }
            Inventario.productosFlag=true;



    }

    static void registrar(){
        boolean registrar=true;
        while (registrar) {

                System.out.print("Ingrese nombre y apellido del cliente: ");
                String name=scan.nextLine().toUpperCase();
                Inventario.customer+="Usuario: "+name;
                try {
                    System.out.print("Ingrese cedula del cliente: ");
                    int cedula=scan.nextInt();
                    Inventario.customer+="\nCedula: "+cedula+"\n";
                    registrar=false;
                } catch (InputMismatchException e) {
                    System.out.println("\nError al ingresar la cedula, ingrese solo numeros y sin puntos.");
                    scan.nextLine();
                }

        }
        Inventario.customerFlag=true;
        System.out.println("Saliendo del registro de usuario...");
    }

    static void tempFactura(){
        System.out.println("\n----- Factura -----");
        System.out.println(Inventario.customer+"---- Lista Productos ----"+
                Inventario.factura+"\n"+"Monto Total: "+Inventario.total+"bs.");
    }

    static void genFactura(){
        if (Inventario.customerFlag && Inventario.productosFlag) {
            System.out.println("\n----- Factura -----");
            System.out.println(Inventario.customer+"---- Lista Productos ----"+
                    Inventario.factura+"\n"+"Monto Total: "+Inventario.total+"bs.");
        } else {
            System.out.println("No ha registrado al cliente o ingresado productos.");
        }

    }

    static void facturasGuardadas(){

    }
}

public static void main(String[] ignoreArgs) {
    Inventario.cargarInventariojson(Inventario.inventarioArchivo);
    int menu=0;

    while (menu!=5) {

        menu=Funciones.menu();
        Funciones.scan.nextLine();

        switch (menu) {
            case 1:
                Funciones.registrar();
                break;
            case 2:
                Funciones.seleccionar();
                break;
            case 3:
                Funciones.tempFactura();
                break;
            case 4:
                Funciones.genFactura();
                break;
            case 5:
                System.out.println("Saliendo del sistema, vuelva pronto!");
                break;
    }
}
}
