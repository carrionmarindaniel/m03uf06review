/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.m03uf06review.jdbc;

import java.sql.Connection;
import static java.sql.DriverManager.getConnection;
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
        Connection c = getConnection("jdbc:mysql://localhost:3306/mysql?"
                    + "useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "1234");
    }
    
}
