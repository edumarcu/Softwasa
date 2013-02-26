
package common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Ignacio González Granados
 */

@Entity
@Table(name="tb_productos")
public class Producto {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
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

    public int getId() {
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

    public void setId(int id) {
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
        int hash = 7;
        hash = 37 * hash + this.id;
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
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
