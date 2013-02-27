package juegosdemesa;

import common.Categoria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.After;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test Categoria Productos
 * @author EM
 */
public class CategoriaTest {

    private EntityManagerFactory emf;
    private EntityManager em;

    private Categoria jRol, jEle, jPre, jDib, jCla;

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

        emf = Persistence.createEntityManagerFactory("juegosDeMesaPU");
        em = emf.createEntityManager();

        jRol = new Categoria( "Rol", "Hero Quest");
        jEle = new Categoria( "Electrónicos", "Simon");
        jPre = new Categoria("Preguntas", "Trivial");
        jDib = new Categoria( "Dibujar", "Pictionary");
        jCla = new Categoria("Clásicos", "Parchis, Oca");
    }

    @After
    public void afterTest() {
        emf.close();
    }

    /*++++++++++++++++++++++++++++++++++++++++++++++ R */
    @Test
    public void test_list() {
//        System.out.println("List Test");
        assertTrue(jRol.create(em));
        assertTrue(jEle.create(em));
        assertTrue(jPre.create(em));
        assertTrue(jDib.create(em));
        assertTrue(jCla.create(em));

        List<Categoria> expected = new ArrayList<Categoria>(){{add(jRol); add(jEle); add(jPre); add(jDib); add(jCla);}};
//        System.out.println(expected);
        List<Categoria> actual = Categoria.list(em);
//        System.out.println(actual);

        assertEquals(expected, actual);
    }

    /******************************************* Id */
    @Test
    public void test_findById_categoriaNoExistente() {
        assertFalse(em.contains(jRol));

        Categoria expected = null;
        Categoria actual = Categoria.findById(em, jRol.getId());
        assertEquals(expected, actual);
    }

    @Test
    public void test_findById_categoriaExistente() {
        assertTrue(jRol.create(em));
        assertTrue(em.contains(jRol));

        Categoria expected = jRol;
        Categoria actual = Categoria.findById(em, jRol.getId());
        assertEquals(expected, actual);
    }

        /******************************************* Nombre */
    @Test
    public void test_findByNombre_categoriaNoExistente() {
         assertFalse(em.contains(jRol));

        Categoria expected = null;
        Categoria actual = Categoria.findByNombre(em, jRol.getNombre_categoria());
        assertEquals(expected, actual);
    }

    @Test
    public void test_findByNombre_categoriaExistente() {
        assertTrue(jRol.create(em));
        assertTrue(em.contains(jRol));

        Categoria expected = jRol;
        Categoria actual = Categoria.findByNombre(em, jRol.getNombre_categoria());
        assertEquals(expected, actual);
    }

    @Test
    public void test_findLikeNombre_categoriaNoExistente() {
        assertFalse(em.contains(jRol));

        List<Categoria> expected = new ArrayList<Categoria>();
        List<Categoria> actual = Categoria.findLikeNombre(em, "i");
        assertEquals(expected, actual);
    }

    @Test
    public void test_findLikeNombre_1categoriaExistente() {
        assertTrue(jRol.create(em));
        assertTrue(em.contains(jRol));

        List<Categoria> expected = new ArrayList<Categoria>(){{add(jRol);}};
        List<Categoria> actual = Categoria.findLikeNombre(em, "o");
        assertEquals(expected, actual);

        actual = Categoria.findLikeNombre(em, "R");
        assertEquals(expected, actual);

        actual = Categoria.findLikeNombre(em, "ol");
        assertEquals(expected, actual);
    }

    @Test
    public void test_findLikeNombre_2categoriasExistentes() {
        assertTrue(jRol.create(em));
        assertTrue(em.contains(jRol));
        assertTrue(jEle.create(em));
        assertTrue(em.contains(jEle));


        List<Categoria> expected = new ArrayList<Categoria>(){{add(jRol); add(jEle);}};
        List<Categoria> actual = Categoria.findLikeNombre(em, "l");
        assertEquals(expected, actual);

        actual = Categoria.findLikeNombre(em, "o");
        assertEquals(expected, actual);

        actual = Categoria.findLikeNombre(em, "r");
        assertEquals(expected, actual);

        actual = Categoria.findLikeNombre(em, "i");
        assertNotEquals(expected, actual);
    }

    /******************************************* Descripcion */
    @Test
    public void test_findByDescripcion_categoriaNoExistente() {
         assertFalse(em.contains(jRol));

        Categoria expected = null;
        Categoria actual = Categoria.findByDescripcion(em, jRol.getDescripcion_categoria());
        assertEquals(expected, actual);
    }

    @Test
    public void test_findByDescripcion_categoriaExistente() {
        assertTrue(jRol.create(em));
        assertTrue(em.contains(jRol));

        Categoria expected = jRol;
        Categoria actual = Categoria.findByDescripcion(em, jRol.getDescripcion_categoria());
        assertEquals(expected, actual);
    }

    @Test
    public void test_findLikeDescripcion_categoriaNoExistente() {
        assertFalse(em.contains(jRol));

        List<Categoria> expected = new ArrayList<Categoria>();
        List<Categoria> actual = Categoria.findLikeDescripcion(em, "i");
        assertEquals(expected, actual);
    }

    @Test
    public void test_findLikeDEscripcion_1categoriaExistente() {
        assertTrue(jRol.create(em));
        assertTrue(em.contains(jRol));

        List<Categoria> expected = new ArrayList<Categoria>(){{add(jRol);}};
        List<Categoria> actual = Categoria.findLikeDescripcion(em, "o");
        assertEquals(expected, actual);

        actual = Categoria.findLikeDescripcion(em, "h");
        assertEquals(expected, actual);

        actual = Categoria.findLikeDescripcion(em, " quest");
        assertEquals(expected, actual);
    }

    @Test
    public void test_findLikeDescripcion_2categoriasExistentes() {
        assertTrue(jRol.create(em));
        assertTrue(em.contains(jRol));
        assertTrue(jEle.create(em));
        assertTrue(em.contains(jEle));


        List<Categoria> expected = new ArrayList<Categoria>(){{add(jRol); add(jEle);}};
        List<Categoria> actual = Categoria.findLikeDescripcion(em, "o");
        assertEquals(expected, actual);

        actual = Categoria.findLikeDescripcion(em, "s");
        assertEquals(expected, actual);

        actual = Categoria.findLikeDescripcion(em, "h");
        assertNotEquals(expected, actual);

        actual = Categoria.findLikeDescripcion(em, "Z");
        assertNotEquals(expected, actual);
    }

    /******************************************* Utils */
    @Test
    public void test_contains_categoriaNoExistente() {
        assertFalse(em.contains(jRol));

        assertFalse(Categoria.contains(em, jRol.getId()));
    }

    @Test
    public void test_contains_categoriaExistente() {
        assertTrue(jRol.create(em));
        assertTrue(em.contains(jRol));

        assertTrue(Categoria.contains(em, jRol.getId()));
    }

    @Test
    public void test_count_categoriaNoExistente() {
        assertFalse(em.contains(jRol));

        long expected = 0L;
        long actual = Categoria.count(em);
        assertEquals(expected, actual);
    }

    @Test
    public void test_findLikeDEscripcion_5categoriasExistentes() {
        assertTrue(jRol.create(em));
        assertTrue(jEle.create(em));
        assertTrue(jPre.create(em));
        assertTrue(jDib.create(em));
        assertTrue(jCla.create(em));

        long expected = 5L;
        long actual = Categoria.count(em);
        assertEquals(expected, actual);
    }

    @Test
    public void test_hash_toString() {
        assertTrue(jRol.create(em));
        System.out.println("jRol= " + jRol + "hashCode= " + jRol.hashCode());
    }

    @Test
    public void test_equals() {
        assertTrue(jRol.create(em));
        assertFalse(jRol.equals("Rol"));
        assertFalse(jRol.equals(null));
    }

    /*++++++++++++++++++++++++++++++++++++++++++++++ CUD */
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

    @Test
    public void test_remove_categoriaNoExistente() {
        assertFalse(em.contains(jRol));
        assertFalse(jRol.remove(em));

        assertFalse(em.contains(jRol));
    }

    @Test
    public void test_remove_categoriaExistente() {
        assertTrue(jRol.create(em));
        assertTrue(em.contains(jRol));

        assertTrue(jRol.remove(em));
        assertFalse(em.contains(jRol));
    }

    @Test
    public void test_create_exception() {
        EntityTransaction et = em.getTransaction();
        // al estar empezada dará erro en begin
        et.begin();
        assertFalse(jRol.create(em));
    }

    @Test
    public void test_update_exception() {
        EntityTransaction et = em.getTransaction();
        // al estar empezada dará erro en begin
        et.begin();
        assertFalse(jRol.update(em));
    }

    @Test
    public void test_remove_exception() {
        EntityTransaction et = em.getTransaction();
        // al estar empezada dará erro en begin
        et.begin();
        assertFalse(jRol.remove(em));
    }
}
