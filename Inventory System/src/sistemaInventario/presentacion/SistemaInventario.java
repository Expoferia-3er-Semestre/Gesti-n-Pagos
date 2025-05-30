/*
Sistema de inventario dividido en clases, funciones y métodos
Douglas Ojeda
*/

/*
1. Guardar productos (nuevos o modificados) individualmente para mejorar la optimizacion. -Ya no es necesario
2. Integrar funciones de busqueda por nombre, precio, categoria y fecha
3. Agregar metodos para exportar la lista de productos
4. Seguir el SRP (Single Responsibility Principle), diviendo el codigo en distintos modulos (buscar ejemplos). -En progreso :D
5. Realizar una base de datos propia (SQLite, MySQL o PostgreSQL). -Hechoo
6. Implementar un historial de cambios (agregar, modificar y eliminar)
7. Crear UI con Javafx, Swing o Spring Boot combinado con React
8. Implementar notificaciones sobre productos con mucho o bajo stock
9. Incorporar autenticacion de usuarios (admins podran gestionar los permisos y cajeros facturar)
9. Registrar qué usuario realizó tal accion
10. Crear un caché para busquedas frecuentes o costosas
11. Agregar métodos para realizar verificaciones periódicas y limpiezas automáticas del inventario
*/

import sistemaInventario.presentacion.Funciones;

public static void main(String[] ignoredArgs) {

    Funciones funciones=new Funciones();

    int menu=0;
    boolean listarFlag=false;
    System.out.println("\n*** Bienvenido al Sistema de Inventario ***");

    while (menu!=5) { //Bucle del sistema

        menu=funciones.menu();

        switch (menu) {

            case 1: //Lista
                listarFlag=funciones.listarProductos(listarFlag);
                break;

            case 2: //Agregar
                funciones.agregarProductos();
                break;

            case 3: //Modificar
                funciones.modificarProducto();
                break;

            case 4: //Eliminar
                funciones.eliminarProducto();
                break;

            case 5: //Salir del sistema
                System.out.println("\nSaliendo del sistema... ");
                break;

            default:
                System.out.println("\nIngrese una opcion dentro del menu (1-5)");
                break;
        }

    }
    System.out.println("\nGracias por usar el sistema. ¡Que tenga un excelente día!");

}