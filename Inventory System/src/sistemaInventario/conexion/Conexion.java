package sistemaInventario.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class Conexion {

    static Connection conexion=null;

    public static Connection getConexion() {

        String url="jdbc:mysql://localhost:3306/sys";
        String usuario="root";
        String password="admin";
        if (conexion==null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexion= DriverManager.getConnection(url, usuario, password);
                System.out.println("Conexion exitosa.");
            } catch (Exception e) {
                System.out.println("Error al conectarse a la base de datos: "+e);
            }
        } else {
            System.out.println("Ya hay una conexión existente.");
        }
        return conexion;
    }

    public static Connection closeConexion() {
        if (conexion!=null) {
            try {
                conexion.close();
                System.out.println("Conexion cerrada.");
                conexion=null;
            } catch (Exception e) {
                System.out.println("Error: "+e);
            }
        } else {
            System.out.println("No existe conexión alguna.");
        }
        return conexion;
    }
}

class TipoPago {

    private int id;
    private String concepto;
    private String categoria;
    private double costo;

    public TipoPago() {
    }

    public TipoPago(int id, String concepto, String categoria, double costo) {
        this.id = id;
        this.concepto = concepto;
        this.categoria = categoria;
        this.costo = costo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    @Override
    public String toString() {
        return "TipoPago{" +
                "id=" + id +
                ", concepto='" + concepto + '\'' +
                ", categoria='" + categoria + '\'' +
                ", costo=" + costo +
                '}';
    }

    public static void main(String[] args) {

        List<TipoPago> listaTipoPago=new ArrayList<>();
        TipoPago tipoPago1= new TipoPago(1, "Mensualidad Mayo", "Mensualidad", 40);
        TipoPago tipoPago2= new TipoPago(2, "Costura", "Curso", 20);

        listaTipoPago.add(tipoPago1);
        listaTipoPago.add(tipoPago2);

        for (TipoPago tipoPago:listaTipoPago) {
            System.out.println(tipoPago);
        }
    }

}