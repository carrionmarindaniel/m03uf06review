package cat.copernic.m03uf06review.hibernate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Iterator;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * En aquesta secció cal accedir a una taula de MySQL amb un camp de cada tipus:
 *
 * int o long, double o float, boolean, char, String i java.sql.Date
 *
 * Recòrrer el result set i mostrar-lo per la consola com a instancies de la
 * classe Registre, que tindrà l'estructura de la teva taula.
 *
 * Cal usar la llibreria Hibernate.
 *
 *
 * @author pep
 */
public class HibernateMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SessionFactory instancia = HibernateUtil.buildSessionFactory();
        Session session = instancia.openSession();
        Query q = session.createQuery("from Empleados");
        Iterator<Empleados> it = q.iterate();

        Empleados e;
        while (it.hasNext()) {
            e = it.next();
            System.out.println(e.getNombre());
        }

        session.close();
    }

}
