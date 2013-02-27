/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package juegosdemesa;

import common.Categoria;
import common.Producto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static org.junit.Assert.*;
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

        categoria1=new Categoria(1,"Juegos para niños","Juego simple");
        categoria2=new Categoria(2,"Juegos para adolescentes","Juego menos simple");
        categoria3=new Categoria(3,"Juegos para adultos","Juego complejo");
        categoria4=new Categoria(4,"Juegos para ancianos","Juego muy complejo");
        
        producto1=new Producto("Cartago contra Roma","Cartago lucha contra Roma","http://www.planetongames.com/sword-rome-2010-reprint-p-2403.html");
        producto1.setCategoria(categoria1);
        producto2=new Producto("The Havre","Juego de barcos y comida","http://www.planetongames.com/le-havre-p-1572.html");
        producto1.setCategoria(categoria1);
        producto3=new Producto("7 ages","Juego de fichitas","http://www.planetongames.com/7-wonders-p-2430.html");
        producto1.setCategoria(categoria2);
        producto4=new Producto("Agricola","Juego de gestión","http://www.planetongames.com/agricola-castellano-p-732.html");
        producto1.setCategoria(categoria3);
        producto5=new Producto("Here I Stand","Juego belico","http://www.planetongames.com/here-stand-2010-reprint-p-329.html");
        producto1.setCategoria(categoria4);
        
    }
    @Test
    @Ignore
    public void test_addProducto_ProductoNotExists() {
        boolean result = producto1.insertarProducto(em);
        result = result && producto1.insertarProducto(em);
        assertTrue(result);
        
        assertTrue(Producto.contieneProducto(em, producto1.getId()));
        
        assertEquals(1, (double)Producto.Contar(em));

        assertEquals(producto1, Producto.listarProductoPorId(em, producto1.getId()));
        
    }
    
    @Test
    @Ignore
    public void test_addProducto_ProductoExists() {
        producto1.insertarProducto(em);
       
        boolean result = producto1.insertarProducto(em);
        assertFalse(result);

        
        Producto producto6 = producto1.clone();
        producto6.setId((long)0);
        result = producto6.insertarProducto(em);
        
        assertFalse(result);

        assertEquals(1, (double)Producto.Contar(em));
    }
    
    @Test
    @Ignore
    public void test_EliminarProducto(){
        producto1.insertarProducto(em);
        boolean resultado=producto1.eliminarProducto(em);
        assertTrue(resultado);
        
        resultado=producto1.eliminarProducto(em);
        assertFalse(resultado);
        
        assertFalse(Producto.contieneProducto(em, producto1.getId()));
        assertEquals(0,(double)Producto.Contar(em));
    }
    @Test
    @Ignore
    public void test_listarProductos(){
        producto1.insertarProducto(em);
        producto2.insertarProducto(em);
        producto3.insertarProducto(em);
        producto4.insertarProducto(em);
        producto5.insertarProducto(em);
        
        List <Producto> esperado=new ArrayList<Producto>();
        esperado.add(producto1);
        esperado.add(producto2);
        esperado.add(producto3);
        esperado.add(producto4);
        esperado.add(producto5);
        
        assertEquals(esperado.size(),Producto.listarProductos(em).size());
        assertEquals(esperado,Producto.listarProductos(em));
        
    }
    public void test_listarProductosPorCategoria(){
        producto1.insertarProducto(em);
        producto2.insertarProducto(em);
        producto3.insertarProducto(em);
        producto4.insertarProducto(em);
        producto5.insertarProducto(em);
        
        List <Producto> esperado=new ArrayList<Producto>();
        esperado.add(producto1);
        esperado.add(producto2);
        
        assertEquals(esperado.size(),Producto.listarProductoPorCategoria(em, categoria1).size());
        assertEquals(esperado,Producto.listarProductoPorCategoria(em, categoria1));
    }
    @Test
    @Ignore
    public void test_EncontrarProductoPorId(){
        producto1.insertarProducto(em);
        producto2.insertarProducto(em);
        
        Producto resultado=Producto.listarProductoPorId(em, producto1.getId());
        assertEquals(producto1,resultado);
    }
    @Test
    @Ignore
    public void test_EncontrarProductoPorNombre(){
        producto1.insertarProducto(em);
        producto1.insertarProducto(em);
        
        Producto resultado=Producto.listarProductoPorNombre(em, producto1.getNombre_producto());
        assertEquals(producto1,resultado);
    }
 }
