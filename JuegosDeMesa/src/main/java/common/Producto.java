
package common;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

/**
 *
 * @author Ignacio González Granados
 */

@Entity
@Table(name="tb_productos")
public class Producto {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Column(length=100, nullable=true)
    private String nombre_producto;
    
    @Column(nullable=true)
    private String descripcion_producto;
    
    @Column(length=100, nullable=true)
    private String enlace_producto;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="id_categoria", referencedColumnName="id")
    private Categoria categoria;
    
    public Producto(){
        
    }
    public Producto(String nombre_producto,String descripcion_producto,String enlace_producto){
        this.nombre_producto=nombre_producto;
        this.descripcion_producto=descripcion_producto;
        this.enlace_producto=enlace_producto;
    }
    public Producto(Long id,String nombre_producto,String descripcion_producto,String enlace_producto){
        this.id=id;
        this.nombre_producto=nombre_producto;
        this.descripcion_producto=descripcion_producto;
        this.enlace_producto=enlace_producto;
    }
    public Long getId() {
        return id;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public String getDescripcion_producto() {
        return descripcion_producto;
    }

    public String getEnlace_producto() {
        return enlace_producto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public void setDescripcion_producto(String descripcion_producto) {
        this.descripcion_producto = descripcion_producto;
    }

    public void setEnlace_producto(String enlace_producto) {
        this.enlace_producto = enlace_producto;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Producto other = (Producto) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    public Producto clone(){
        Producto producto=new Producto();
        producto.setId(this.getId());
        producto.setCategoria(this.getCategoria());
        producto.setDescripcion_producto(this.getDescripcion_producto());
        producto.setEnlace_producto(this.getEnlace_producto());
        producto.setNombre_producto(this.getNombre_producto());
        return producto;
    }
    
    public boolean insertarProducto(EntityManager em){
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            boolean res = insertarProductoNoTransaction(em);
            et.commit();
            return res;
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
//            throw new Exception("Error saving user");
            e.printStackTrace();
            return false;
        }
    }
    public boolean insertarProductoNoTransaction(EntityManager em){
         if (em.contains(this)) {
            return false;
        } else {
            em.persist(this);
            em.flush();
            return true;
        }
    }
    public boolean eliminarProducto(EntityManager em){
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            boolean res = eliminarProductoNoTransaction(em);
            et.commit();
            return res;
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
//            throw new Exception("Error saving user");
            e.printStackTrace();
            return false;
        }
    }
    public boolean eliminarProductoNoTransaction(EntityManager em){
         if (em.find(Producto.class, this.getId()) != null) {
            em.remove(this);
            em.flush();
            return true;
        } else {
            return false;
        }
    }
    public static boolean contieneProducto(EntityManager em, long id) {
        return em.find(Producto.class, id) != null;
    }
    public static List<Producto> listarProductos(EntityManager em){
        String sql = "SELECT x FROM Producto x ORDER BY x.title";
        TypedQuery<Producto> query = em.createQuery(sql, Producto.class);
        return query.getResultList();
    }
    public static Producto listarProductoPorId(EntityManager em,Long id){
         return em.find(Producto.class, id);
    }
    public static Producto listarProductoPorNombre(EntityManager em,String nombre){
        return em.find(Producto.class, nombre);
    }
    public static List<Producto> listarProductoPorCategoria(EntityManager em,Categoria categoria){
        String sql = "SELECT x FROM Producto x WHERE x.categoria = :categoria";
        TypedQuery<Producto> query = em.createQuery(sql, Producto.class);
        query.setParameter("categoria", categoria);
        return query.getResultList();
    }
    public static Long Contar(EntityManager em){
        String sql = "SELECT COUNT(x) FROM Producto x";
        TypedQuery<Long> query = em.createQuery(sql, Long.class);
        Long count = query.getSingleResult();
        return count;
    }
    
    
}
