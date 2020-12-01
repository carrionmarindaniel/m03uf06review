/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.m03uf06review.jdbc;

import java.sql.Connection;
import static java.sql.DriverManager.getConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * En aquesta secció cal accedir a una taula de MySQL amb un camp de cada tipus:
 *
 * int o long, double o float, boolean, char, String i java.sql.Date
 *
 * Recòrrer el result set i mostrar-lo per la consola.
 *
 *
 * @author pep
 */
public class JdbcMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        /* primero ha havido de agregar este codigo al pom.xml para que usar el driver de mysql
        <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>8.0.18</version>
                <exclusions>
                    <exclusion>
                        <groupId>com.google.protobuf</groupId>
                        <artifactId>protobuf-java</artifactId>
                    </exclusion>
                </exclusions> 
          </dependency>
         */
        //crear conection con la info de la base de datos,puerto, direccion ip, usuario y contraseña...
        Connection c = getConnection("jdbc:mysql://localhost:3306/empleados?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "1234");
        //la query
        String consulta = "select * from empleados.empleados";
        //prepara consulta
        PreparedStatement statement = c.prepareStatement(consulta);
        //hace consulta y guarda los resultados en esta misma clase
        ResultSet rs = statement.executeQuery();
        //con un bucle sacamos los resultados de la consulta
        while (rs.next()) {
            System.out.println("******************************************************************************************");
            System.out.print(rs.getString("nombre"));
            System.out.print(" | ");
            System.out.print(rs.getInt("edad"));
            System.out.print(" | ");
            System.out.print(rs.getDouble("sueldo"));
            System.out.print(" | ");
            System.out.print(rs.getBoolean("contratado_actualmente"));
            System.out.print(" | ");
            System.out.print(rs.getDate("fecha_inicio_contrato"));
            System.out.print(" | ");
            System.out.print(rs.getDate("fecha_fin_contrato"));
            System.out.print(" | ");
            System.out.print(rs.getLong("plus"));
            System.out.print(" | ");
            System.out.print(rs.getString("genero"));
            System.out.print("\n");
        }

    }

}
