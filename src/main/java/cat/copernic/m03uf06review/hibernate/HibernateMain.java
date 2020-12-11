package cat.copernic.m03uf06review.hibernate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import clases.Empleados;
import java.sql.Date;
import java.util.Iterator;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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

    /*
    Para implementar la libreria hibernate he seguido el siguiente tutorial: https://www.discoduroderoer.es/instalacion-de-hibernate-en-netbeans/
    Al implementar hibernate ha salido el siguiente error: org.hibernate.boot.MappingNotFoundException: Mapping (RESOURCE) not found
    Lo he solucionado haciendo un 'clean and build' del proyecto.
    Tambien hay que añadir las dependencias en el pom.xml
    </dependency>
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-core</artifactId>
            <version>5.4.9.Final</version>
            <type>jar</type>
        </dependency>
    
    La calse query tambien estava obsoleta por lo que cambie el import de la libreria org.hibernate.Query ---> org.hibernate.query.Query
     */
    //luego tuve que declarar Session session como static, y mantener la sesion abierta durante todo
        //el programa para evitar org.hibernate.LazyInitializationException:
    //esta clase y los archivos de hibernate los hice inicialmente para una base de datos con una tabla,
    //pero tuve que cambiarlo para hacer la cuarta entrega, haciendo otra tabla con una configuracion one to many,
    //cambiando tambien los archivos de configuracion de hibernate
    /**
     * @param args the command line arguments
     */
    private static Empleados empleado;
    //creamos sesion
    private static Session session = createSession();

    public static void main(String[] args) {
        // TODO code application logic here
        showAll();
        /* INSERT 
        empleado = new Empleados(new Departamentos(1),0, "s", 5, true, Date.valueOf("2020-02-02"), Date.valueOf("2020-02-02"), "s", 'f');
        insert(empleado);
         */
 /*SELECT
        empleado = select(1);
        System.out.println(empleado.getDepartamentos().getNombre());
         */
 /* UPDATE
        empleado = select(1);
        empleado.setNombre("nuevo nombre");
        update(empleado);
        System.out.println(select(1));
         */
 /* DELETE
        delete(select(1));
         */
        session.close();
    }

    private static Session createSession() {
        SessionFactory instancia = HibernateUtil.buildSessionFactory();
        return instancia.openSession();

    }

    private static void showResults(Iterator<Empleados> it) {
        while (it.hasNext()) {
            empleado = it.next();
            System.out.println("+-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("+ " + empleado.toString() + " +");
        }
    }

    private static boolean insert(Empleados empleado) {
        try {
            //Save the employee in database
            //no es necesario hacer commit, se hace durante el .save()
            session.save(empleado);
            return true;
        } catch (Exception excepcionGeneral) {
            System.out.println(excepcionGeneral);
            return false;
        }
    }

    private static Empleados select(int id) {
        try {
            Query q = session.createQuery("from Empleados where id = " + id);
            Empleados empleado = (Empleados) q.getResultList().get(0);
            return empleado;
        } catch (Exception general) {
            System.out.println(general.toString());
            return null;
        }
    }

    private static boolean update(Empleados empleado) {
        try {
            Transaction tx = session.beginTransaction();
            session.update(empleado);
            tx.commit();
            return true;
        } catch (Exception general) {
            System.out.println(general.toString());
            return false;
        }
    }

    private static boolean delete(Empleados empleado) {
        try {
            Transaction tx = session.beginTransaction();
            empleado = select(empleado.getUid());
            session.delete(empleado);
            tx.commit();
            return true;
        } catch (Exception general) {
            System.out.println(general.toString());
            return false;
        }
    }

    private static void showAll() {
        //hacemos query
        Query q = session.createQuery("from Empleados");
        //creamos iterador los resultados de la query
        Iterator it = q.iterate();
        //impirmimos resultados
        showResults(it);
    }
}
