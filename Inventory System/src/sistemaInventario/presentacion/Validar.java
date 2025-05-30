package sistemaInventario.presentacion;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Validar {

    Scanner scan=new Scanner(System.in);

    public String nombre(String mensaje){

        String nombre;
        while (true){

            System.out.print("\n"+mensaje);
            nombre=scan.nextLine().trim();

            if (nombre.isEmpty()) {
                System.out.println("\nEl nombre no puede estar vacío. Por favor, intenta de nuevo.");
            } else if (nombre.length() < 3 || nombre.length() > 45) {
                System.out.println("\nEl nombre debe tener entre 3 y 45 caracteres.");
            } else if (!nombre.matches("[a-zA-Z0-9 ]+")) {
                System.out.println("\nEl nombre solo puede tener letras, números y espacios.");
            } else if (nombre.contains("  ")) {
                System.out.println("\nEl nombre no puede contener múltiples espacios consecutivos.");
            } else if (nombre.matches("^\\d.*")) {
                System.out.println("\nEl nombre no puede comenzar con números.");
            } else {
                return nombre;
            }
        }

    } //Procesa el nombre ingresado para evitar errores

    public double precio(String mensaje){

        double precio;
        while (true) {
            try {
                System.out.print("\n"+mensaje);
                precio=scan.nextDouble();
                scan.nextLine();
                if (precio>=0) {
                    return precio;
                } else {
                    System.out.println("El precio debe ser mayor que cero. Por favor, intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: \""+e+"\". Ingrese solo numeros decimales o enteros (Ej: 10,50).");
                scan.nextLine();
            }

        }

    } //Procesa el precio ingresado para evitar errores

    public int cantidad(String mensaje){

        int cantidad;
        while (true) {
            try {
                System.out.print("\n"+mensaje);
                cantidad=scan.nextInt();
                scan.nextLine();
                if (cantidad>=0) {
                    return cantidad;
                } else {
                    System.out.println("La cantidad no puede ser menor que cero. Por favor, intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: \""+e+"\". Ingrese un numero entero (Ej: 5).");
                scan.nextLine();
            }

        }

    } //Procesa la cantidad ingresado para evitar errores

    public String categoria(String mensaje) {

        String categoria;
        while (true){
            System.out.print("\n"+mensaje);
            categoria=scan.nextLine().trim();

            if (categoria.isEmpty()) {
                System.out.println("\nLa categoria no puede estar vacía.");
            } else if (categoria.length() < 3 || categoria.length() > 45) {
                System.out.println("\nLa categoria debe tener entre 3 y 45 caracteres.");
            } else if (!categoria.matches("[a-zA-Z ]+")) {
                System.out.println("\nLa categoria solo puede tener letras y espacios.");
            } else if (categoria.contains("  ")) {
                System.out.println("\nLa categoria no puede contener múltiples espacios consecutivos.");
            } else {
                return categoria;
            }

        }
    } //Procesa la categoria ingresada para evitar errores

    public int id(String mensaje) {

        while (true) {
            try {
                System.out.print("\n"+mensaje);
                int id=scan.nextInt();
                if (id>=0) {
                    return id;
                } else {
                    System.out.println("No existen ID menores que 0, intente de nuevo.");
                    scan.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println("Solo se permiten numeros enteros, intente de nuevo.");
                scan.nextLine();
            }
        }

    } //Procesa el ID ingresado para evitar errores

}