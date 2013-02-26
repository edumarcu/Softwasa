package common;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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
public class Categoria {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(length=100, nullable=false, unique=true)
    private String nombre_categoria;

    @Column(nullable=false)
    private String descripcion_categoria;

    @Transient
    private Set<Producto> productos;

    /* Constructor */

    public Categoria() {
    }

    public Categoria(long id, String nombre_categoria, String descripcion_categoria) {
        this.id = id;
        this.nombre_categoria = nombre_categoria;
        this.descripcion_categoria = descripcion_categoria;
        this.productos = null;
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

    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }

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



}
