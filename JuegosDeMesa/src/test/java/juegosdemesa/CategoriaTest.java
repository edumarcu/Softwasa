package juegosdemesa;

import common.Categoria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test Categoria Productos
 * @author EM
 */
public class CategoriaTest {

    private EntityManager em;

    private Categoria jRol;

    @Before
    public void beforeTest() throws SQLException {
        String url = "jdbc:mysql://127.0.0.1/mydb";
        String user = "root";
        String pass = "password";
        Connection conn = DriverManager.getConnection(url, user, pass);

        Statement stmt = conn.createStatement();
        stmt.execute("TRUNCATE TABLE tb_categorias");
        stmt.close();

        conn.close();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("juegosDeMesaPU");
        em = emf.createEntityManager();

        jRol = new Categoria(1, "Juegos de Rol", "");
    }

    @Test
    public void test_create_categoria() {
        assertTrue(jRol.create(em));
    }

}
