package juegosdemesa;

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

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("juegosDeMesaPU");
        em = emf.createEntityManager();
    }

    @Test
    public void test_conexion() {
        String sql = "SELECT 1;";

        Query query = em.createNativeQuery(sql);

        assertEquals(1L, query.getSingleResult());
    }
}
