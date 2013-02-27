package common;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
//import javax.persistence.Transient;

/**
 * Categoria de Juego
 * @author EM
 * `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `nombre_categoria` VARCHAR(100) NULL ,
  `descripcion_categoria` TEXT NULL ,
  PRIMARY KEY (`id`) )
 */
@Entity
@Table(name="tb_categorias")
public class Categoria implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(length=100, nullable=false, unique=true)
    private String nombre_categoria;

    @Column(nullable=false)
    private String descripcion_categoria;
//
//    @Transient
//    private Set<Producto> productos;

    /* Constructor */

    public Categoria() {
    }

    public Categoria(String nombre_categoria, String descripcion_categoria) {
        this.nombre_categoria = nombre_categoria;
        this.descripcion_categoria = descripcion_categoria;
    }

    public Categoria(long id, String nombre_categoria, String descripcion_categoria) {
        this.id = id;
        this.nombre_categoria = nombre_categoria;
        this.descripcion_categoria = descripcion_categoria;
//        this.productos = null;
    }

    /* Getters and Setters */

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre_categoria() {
        return nombre_categoria;
    }

    public void setNombre_categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }

    public String getDescripcion_categoria() {
        return descripcion_categoria;
    }

    public void setDescripcion_categoria(String descripcion_categoria) {
        this.descripcion_categoria = descripcion_categoria;
    }

//    public Set<Producto> getProductos() {
//        return productos;
//    }
//
//    public void setProductos(Set<Producto> productos) {
//        this.productos = productos;
//    }

    /* Utils */

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Categoria other = (Categoria) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Categoria: [id=" + id + ",nombre=" + nombre_categoria + ",desc=" + descripcion_categoria + "]";
    }

    /************************************************************************************ Active Record */
    /******************************************************************** R */
    public static List<Categoria> list(EntityManager em) {
        String sql = "SELECT c FROM Categoria c";
        TypedQuery<Categoria> query = em.createQuery(sql, Categoria.class);
        return query.getResultList();
    }

    public static Categoria findById(EntityManager em, long id) {
        return em.find(Categoria.class, id);
    }

    public static Categoria findByNombre(EntityManager em, String nombre) {
        String sql = "SELECT c FROM Categoria c WHERE c.nombre_categoria = :nombre";
        TypedQuery<Categoria> query = em.createQuery(sql, Categoria.class);
        query.setParameter("nombre", nombre);

        /* si no encuantra lanza exception */
        try {
            return query.getSingleResult();

        } catch (Exception e) {
            return null;
        }
    }

    public static  List<Categoria> findLikeNombre(EntityManager em, String nombre) {
        String sql = "SELECT c FROM Categoria c WHERE c.nombre_categoria LIKE :nombre";
        TypedQuery<Categoria> query = em.createQuery(sql, Categoria.class);
        query.setParameter("nombre", "%" + nombre + "%");

        return query.getResultList();
    }

    public static Categoria findByDescripcion(EntityManager em, String descripcion) {
        String sql = "SELECT c FROM Categoria c WHERE c.descripcion_categoria = :descripcion";
        TypedQuery<Categoria> query = em.createQuery(sql, Categoria.class);
        query.setParameter("descripcion", descripcion);

        if (query.getResultList().isEmpty()) {
            return null;
        }
        else {
            return query.getSingleResult();
        }
    }

    public static  List<Categoria> findLikeDescripcion(EntityManager em, String descripcion) {
        String sql = "SELECT c FROM Categoria c WHERE c.descripcion_categoria LIKE :descripcion";
        TypedQuery<Categoria> query = em.createQuery(sql, Categoria.class);
        query.setParameter("descripcion", "%" + descripcion + "%");

        return query.getResultList();
    }

    /************************************************ Utils */
    public static boolean contains(EntityManager em, long id) {
        return em.find(Categoria.class, id) != null;
    }

    public static long count(EntityManager em) {
        return list(em).size();
    }

    /****************************************************** CUD */
    public boolean create(EntityManager em) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            boolean res = createNoTransaction(em);
            et.commit();

            return res;

        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public boolean createNoTransaction(EntityManager em) {
        if (em.contains(this)) {
            return false;
        } else {
            em.persist(this);
            em.flush();
            return true;
        }
    }

     public boolean update(EntityManager em) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            boolean res = updateNoTransaction(em);
            et.commit();
            return res;
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }

            e.printStackTrace();
            return false;
        }
    }

    public boolean updateNoTransaction(EntityManager em) {
        if (em.find(Categoria.class, this.getId()) != null) {
            em.merge(this);
            em.flush();
            return true;
        } else {
            return false;
        }
    }

    public boolean remove(EntityManager em) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            boolean res = removeNoTransaction(em);
            et.commit();
            return res;
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }

            e.printStackTrace();
            return false;
        }
    }

    public boolean removeNoTransaction(EntityManager em) {
        if (em.find(Categoria.class, this.getId()) != null) {
            em.remove(this);
            em.flush();
            return true;
        } else {
            return false;
        }
    }

}
