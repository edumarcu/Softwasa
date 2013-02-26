/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package juegosdemesa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Usuario
 */

public class ProductoTest {
    
    private EntityManager em;
    private static Producto producto1;
    private static Producto producto2;
    private static Producto producto3;
    private static Producto producto4;
    private static Producto producto5;
    
    private static Categoria categoria1;
    private static Categoria categoria2;
    private static Categoria categoria3;
    private static Categoria categoria4;
    
    
    
    @Before
    public void AntesDelTest() throws SQLException{
        String url = "jdbc:mysql://127.0.0.1/test";
        String user = "root";
        String pass = "";
        Connection conn = DriverManager.getConnection(url, user, pass);
        Statement stmt1 = conn.createStatement();
        stmt1.execute("TRUNCATE TABLE tb_productos");
        stmt1.close();

        Statement stmt2 = conn.createStatement();
        stmt2.execute("TRUNCATE TABLE tb_categorias");
        stmt2.close();

        conn.close();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cursoDemoPU");
        em = emf.createEntityManager();

        //
        /*
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        */
        /*
        ago = new Author(1, "George Orwell", sdf.parse("1903-06-25"), "United Kingdom");
        ajkr = new Author(2, "J. K. Rowling", sdf.parse("1965-07-31"), "United Kingdom");
        ame = new Author(3, "Michael Ende", sdf.parse("1929-11-12"), "Germany");
        ada = new Author(4, "Douglas Adams", sdf.parse("1952-03-11"), "United Kingdom");

        b1984 = new Book("978 0 140 81774 4", "Nineteen eighty-four", 1949);
        b1984.setAuthor(ago);
        bhp1 = new Book("0 7475 5819 1", "Harry Potter and the philosopher's stone", 1997);
        bhp1.setAuthor(ajkr);
        bhp2 = new Book("0 7475 3848 4", "Harry Potter and the chamber of secrets", 1998);
        bhp2.setAuthor(ajkr);
        bhi = new Book("35 221 2800 1", "Die unendliche geschichte", 1979);
        bhi.setAuthor(ame);
        bga = new Book("0 345 39180 2", "The hitchhicker's guide to the galaxy", 1979);
        bga.setAuthor(ada);
        * */
    }
}
