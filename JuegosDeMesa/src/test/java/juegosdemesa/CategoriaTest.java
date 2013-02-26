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
    public void test_create_categoriaNoExistente() {
        assertTrue(jRol.create(em));
        assertTrue(em.contains(jRol));
    }

    @Test
    public void test_create_categoriaExistente() {
        assertTrue(jRol.create(em));
        assertTrue(em.contains(jRol));
        assertFalse(jRol.create(em));
        assertTrue(em.contains(jRol));
    }

    @Test
    public void test_update_categoriaNoExistente() {
        assertFalse(em.contains(jRol));
        jRol.setDescripcion_categoria("Juegos para gente que le guste el Rol");
        assertFalse(jRol.update(em));
        assertFalse(em.contains(jRol));
    }

    @Test
    public void test_update_categoriaExistente() {
        assertFalse(em.contains(jRol));
        assertTrue(jRol.create(em));
        assertTrue(em.contains(jRol));

        String descripcion = "Juegos para gente que le guste el Rol";
        jRol.setDescripcion_categoria(descripcion);
        assertTrue(jRol.update(em));

        assertTrue(em.contains(jRol));
        assertEquals(descripcion, jRol.getDescripcion_categoria());

    }

}
