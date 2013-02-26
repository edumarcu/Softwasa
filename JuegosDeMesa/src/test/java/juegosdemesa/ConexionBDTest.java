package juegosdemesa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ConexionBDTest  {

    private EntityManager em;

    @Before
    public void beforeTest()  {
//        String url = "jdbc:mysql://127.0.0.1/mydb";
//        String user = "root";
//        String pass = "password";
//        Connection conn = DriverManager.getConnection(url, user, pass);
//
//        Statement stmt1 = conn.createStatement();
//        stmt1.execute("TRUNCATE TABLE books");
//        stmt1.close();
//
//        Statement stmt2 = conn.createStatement();
//        stmt2.execute("TRUNCATE TABLE authors");
//        stmt2.close();
//
//        conn.close();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("juegosDeMesaPU");
        em = emf.createEntityManager();
    }

    @Test
    public void test_conexion() {
        String sql = "SELECT 1;";

        Query query = em.createNativeQuery(sql);

        assertEquals(1, query.getSingleResult());
    }
}
