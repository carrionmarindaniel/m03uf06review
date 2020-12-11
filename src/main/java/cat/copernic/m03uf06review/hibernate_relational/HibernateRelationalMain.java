/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.m03uf06review.hibernate_relational;

import clases.Departamentos;
import clases.Empleados;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * En aquesta secció cal accedir a dues taules de MySQL que formin una relació
 *
 * One to Many, com per exemple: Departament -> Empleat
 *
 * Cada departament té molts empleats, i un empleat només treballa en un
 * departament.
 *
 * Recòrrer la relaciçó i mostrar-la per la consola
 *
 * Cal usar la llibreria Hibernate.
 *
 * @author pep
 */
public class HibernateRelationalMain {

    // Haciendo click derecho en hibernate.cfg.xml se pueden hacer querys desde netbeans
    private static Empleados empleado;
    private static Departamentos departamento;
    //creamos sesion
    private static final Session session = createSession();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
/* VER TODOS LOS DATOS
        showAll();
         */
 /* INSERT 
        empleado = new Empleados(new Departamentos(1,"Informatica"),0, "s", 5, true, Date.valueOf("2020-02-02"), Date.valueOf("2020-02-02"), "s", 'f');
        insert(empleado);
         */
 /*SELECT
        empleado = select(1);
        System.out.println(empleado.toString());
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
 /*VER TODOS LOS EMPLEADOS ORDENADOR POR DEPARTAMENTO
        verTodosEmpleadosOrdenadosPorDepartamentos();
         */
 /*VER LA CANTIDAD DE EMPLEADOS QUE TIENE UN DEPARTAMENTO BUSCANDO POR ID DEL DEPARTAMENTO
        verCantidadEmpleadosEnDepartamento(1);
        session.close();
         */
 /* LA SUMA DEL SALARIO DE TODOS LOS EMPLEADOS DE CADA DEPARTAMENTO
        verGastoPorDepartamentos();
         */
 verGastoPorDepartamentos();
    }

    private static Session createSession() {
        SessionFactory instancia = cat.copernic.m03uf06review.hibernate.HibernateUtil.buildSessionFactory();
        return instancia.openSession();

    }

    private static boolean insert(clases.Empleados empleado) {
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

    private static clases.Empleados select(int id) {
        try {
            Query q = session.createQuery("from Empleados where id = " + id);
            clases.Empleados empleado = (clases.Empleados) q.getResultList().get(0);
            return empleado;
        } catch (Exception general) {
            System.out.println(general.toString());
            return null;
        }
    }

    private static boolean update(clases.Empleados empleado) {
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

    private static boolean delete(clases.Empleados empleado) {
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
        Iterator<Empleados> itEmpleados = q.iterate();
        q = session.createQuery("from Departamentos");
        Iterator<Departamentos> itDepartamentos = q.iterate();
        //impirmimos resultados
        showResults(itEmpleados, itDepartamentos);
    }

    private static void showResults(Iterator<Empleados> itEmpleados, Iterator<Departamentos> itDepartamentos) {
        System.out.println("--------------------------Empleados----------------------------");
        while (itEmpleados.hasNext()) {
            empleado = (Empleados) itEmpleados.next();
            System.out.println("+ " + empleado.toString() + " +");
        }
        System.out.println("--------------------------Departamentos----------------------------");
        while (itDepartamentos.hasNext()) {
            departamento = itDepartamentos.next();
            System.out.println("+ " + departamento.toString() + " +");
        }
    }

    private static void verTodosEmpleadosOrdenadosPorDepartamentos() {
        Query q = session.createQuery("from Empleados order by departamentos.uid");
        Iterator<Empleados> itEmpleados = q.iterate();
        System.out.println("--------------------------Empleados----------------------------");
        while (itEmpleados.hasNext()) {
            empleado = (Empleados) itEmpleados.next();
            System.out.println("+ " + empleado.toString() + " +");
        }
    }

    private static void verCantidadEmpleadosEnDepartamento(int uid) {
        try {
            Query q = session.createQuery("select count(departamentos.uid) from Empleados where departamentos.uid = " + uid + "group by departamentos.uid");
            System.out.println((long) q.getSingleResult());
        } catch (javax.persistence.NoResultException NoResultException) {
            System.out.println(0);
        } catch (Exception a) {
            a.printStackTrace();
        }
    }

    private static void verGastoPorDepartamentos() {
        Query q = session.createQuery("select departamentos.uid as uid , sum(sueldo) as gasto from Empleados group by departamentos.uid ");
        List list;
        //la query devuelve una lista con arrays de objetos que contienen los resultados de las columnas
        list = q.list();
        Object[] array;
        for (int cont = 0; cont < list.size(); cont++) {
            //recojemos el array de objetos de cada posicion de la lista
            array = (Object[]) list.get(cont);
            //en la posicion 0 de cada array esta el id del departamento
            int id_departamento = (int) array[0];
            //en la posicion 1 de cada array esta la suma del salario de todos los empleados de ese departamento
            double salario = (double) array[1];
            System.out.println("id departamento: " + id_departamento + " salario: " + salario);
        }
    }
}
