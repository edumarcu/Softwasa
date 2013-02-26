package juegosdemesa;

/**
 *
 * @author jose Miguel
 */
import common.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;


public class UsuarioTest {
    private EntityManager em;

    private static Usuario user1;
    private static Usuario user2;
    private static Usuario user3;
    private static Usuario user4;
    
     @Before
    public void beforeTest() throws SQLException, ParseException {
        String url = "jdbc:mysql://127.0.0.1/mydb";
        String user = "root";
        String pass = "password";
        Connection conn = DriverManager.getConnection(url, user, pass);
        Statement stmt1 = conn.createStatement();
        stmt1.execute("TRUNCATE TABLE tb_usuarios");
        stmt1.close();
        conn.close();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("juegosDeMesaPU");
        em = emf.createEntityManager();
        //
        
        user1 = new Usuario(1,"user1","clave1","usu1","user1ape1","user1ape2","dire1","11111","uno@gmail.com","Contra Reembolso");
        user2 = new Usuario(2,"user2","clave2","usu2","user2ape1","user2ape2","dire2","22222","dos@gmail.com","Pay-Pal");
        user3 = new Usuario(3,"user3","clave3","usu3","user3ape1","user3ape2","dire3","33333","tres@gmail.com","Contra Reembolso");
        user4 = new Usuario(4,"user4","clave4","usu4","user4ape1","user4ape2","dire4","44444","cuatro@gmail.com","Tarjetao");
    }
    
        @Test
  
    public void test_addUsuario_UsuarioNotExists() {
        boolean result = user1.create(em);
        result = result &&user2.create(em);
        assertTrue(result);

        assertTrue(Usuario.containsUsuario(em, user2.getId()));
        assertEquals(2, Usuario.count(em));

        assertEquals(user2, Usuario.findById(em, user2.getId()));
    }

    @Test

    public void test_addUsuario_UsuarioExists() {     
        user1.create(em);            
        boolean result = user1.create(em);
        assertFalse(result);
        
        Usuario usuario = user1.clone();
        usuario.setId(0);
        
        result = usuario.create(em);
        assertFalse(result);

        assertEquals(1, Usuario.count(em));
         
    }
     
      @Test
    public void test_removeBook() {
        user1.create(em);
        boolean result = user1.remove(em);
        assertTrue(result);

        result = user1.remove(em);
        assertFalse(result);

        assertFalse(Usuario.containsUsuario(em, user1.getId()));
        assertEquals(0, Usuario.count(em));
    }
    
       @Test
    public void test_list() {

        user1.create(em);
        user2.create(em);
        user3.create(em);

        Usuario[] expected = new Usuario[]{user1, user2, user3};

        Usuario[] usuarios = Usuario.findAll(em).toArray(new Usuario[0]);
        assertEquals(3, usuarios.length);
        assertArrayEquals(expected, usuarios);
    }
    
         @Test
    public void test_findByid() {
        user1.create(em);
        user2.create(em);
        user3.create(em);

        Usuario result = Usuario.findById(em, user2.getId());

        assertEquals(user2, result);
    }
         
       
       
}
