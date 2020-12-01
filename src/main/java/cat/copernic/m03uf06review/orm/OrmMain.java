/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.m03uf06review.orm;

import java.sql.Connection;
import java.sql.Date;
import static java.sql.DriverManager.getConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * En aquesta secció cal accedir a una taula de MySQL amb un camp de cada tipus:
 *
 * int o long, double o float, boolean, char, String i java.sql.Date
 *
 * Recòrrer el result set i mostrar-lo per la consola com a instancies de la
 * classe Registre, que tindrà l'estructura de la teva taula.
 *
 *
 * @author pep
 */
public class OrmMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here 
        Connection c = createConection();
        ResultSet rs = doQuery(c);
        //las variables del objeto
        int uid;
        int edad;
        String nombre;
        double sueldo;
        boolean contratado_actualmente;
        Date fecha_inicio_contrato;
        Date fecha_fin_contrato;
        long plus;
        char genero;
        empleado empleado = null;
        //en esta coleccion guardaremos todas las instancias de la clase
        ArrayList<empleado> ArrayList = new ArrayList<>();
        //rellenamos la coleccion
        while (rs.next()) {
            uid = rs.getInt("uid");
            nombre = rs.getString("nombre");
            edad = rs.getInt("edad");
            sueldo = rs.getDouble("sueldo");
            contratado_actualmente = rs.getBoolean("contratado_actualmente");
            fecha_inicio_contrato = rs.getDate("fecha_inicio_contrato");
            fecha_fin_contrato = rs.getDate("fecha_fin_contrato");
            plus = rs.getLong("plus");
            genero = rs.getString("genero").charAt(0);
            //creamos la instancia de la clase empleado
            empleado = new empleado(uid, edad, nombre, sueldo, contratado_actualmente, fecha_inicio_contrato, fecha_fin_contrato, plus, genero);
            //añadimos la instancia de la clase empleado a una lista
            ArrayList.add(empleado);
        }
        //imprimimos arraylist de todos los empleados
        for (int cont = 0; cont < ArrayList.size(); cont++) {
            empleado = ArrayList.get(cont);
            uid = empleado.getUid();
            nombre = empleado.getNombre();
            edad = empleado.getEdad();
            sueldo = empleado.getSueldo();
            contratado_actualmente = empleado.isContratado_actualmente();
            fecha_inicio_contrato = empleado.getFecha_inicio_contrato();
            fecha_fin_contrato = empleado.getFecha_fin_contrato();
            plus = empleado.getPlus();
            genero = empleado.getGenero();
            System.out.println("nombre: " + nombre + "| uid: " + uid + "| edad: " + edad + "| sueldo: " + sueldo + "| contrato vigente: " + contratado_actualmente
                    + "| fecha contratacion: " + fecha_inicio_contrato + "| fecha fin contrato: " + fecha_fin_contrato + "| plus: " + plus
                    + "| genero: " + genero);
        }

        //otra forma de hacerlo imprimir todas las instancias de la clase
        /*
        for (int cont = 0; cont < ArrayList.size(); cont++) {
            System.out.println(ArrayList.get(cont).toString());
        }
         */
    }

    private static Connection createConection() throws SQLException {
        //crear conection con la info de la base de datos,puerto, direccion ip, usuario y contraseña...
        Connection c = getConnection("jdbc:mysql://localhost:3306/empleados?"
                + "useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "1234");
        return c;
    }

    private static ResultSet doQuery(Connection c) throws SQLException {
        //la query
        String consulta = "select * from empleados.empleados";
        //prepara consulta
        PreparedStatement statement = c.prepareStatement(consulta);
        //hace consulta y guarda los resultados en esta misma clase
        ResultSet rs = statement.executeQuery();
        return rs;

    }
}

//creamos clase empleado
class empleado {

    int uid;
    int edad;
    String nombre;
    double sueldo;
    boolean contratado_actualmente;
    Date fecha_inicio_contrato;
    Date fecha_fin_contrato;
    long plus;
    char genero;
//constructor

    public empleado(int uid, int edad, String nombre, double sueldo, boolean contratado_actualmente, Date fecha_inicio_contrato, Date fecha_fin_contrato, long plus, char genero) {
        this.uid = uid;
        this.edad = edad;
        this.nombre = nombre;
        this.sueldo = sueldo;
        this.contratado_actualmente = contratado_actualmente;
        this.fecha_inicio_contrato = fecha_inicio_contrato;
        this.fecha_fin_contrato = fecha_fin_contrato;
        this.plus = plus;
        this.genero = genero;
    }
//getters y setters

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public boolean isContratado_actualmente() {
        return contratado_actualmente;
    }

    public void setContratado_actualmente(boolean contratado_actualmente) {
        this.contratado_actualmente = contratado_actualmente;
    }

    public Date getFecha_inicio_contrato() {
        return fecha_inicio_contrato;
    }

    public void setFecha_inicio_contrato(Date fecha_inicio_contrato) {
        this.fecha_inicio_contrato = fecha_inicio_contrato;
    }

    public Date getFecha_fin_contrato() {
        return fecha_fin_contrato;
    }

    public void setFecha_fin_contrato(Date fecha_fin_contrato) {
        this.fecha_fin_contrato = fecha_fin_contrato;
    }

    public long getPlus() {
        return plus;
    }

    public void setPlus(long plus) {
        this.plus = plus;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }
//to string

    @Override
    public String toString() {
        return "empleado{" + "uid=" + uid + ", edad=" + edad + ", nombre=" + nombre + ", sueldo=" + sueldo + ", contratado_actualmente=" + contratado_actualmente + ", fecha_inicio_contrato=" + fecha_inicio_contrato + ", fecha_fin_contrato=" + fecha_fin_contrato + ", plus=" + plus + ", genero=" + genero + '}';
    }

}
