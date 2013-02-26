
package common;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
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
 * @author Jose Miguel
 */
@Entity
@Table(name="tb_usuarios")
public class Usuario {
    
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
    private long id;
    
    @Column(name="login_usuario", length=50, nullable=false)
    private String loginUsuario;
    @Column(name="password_usuario", length=20, nullable=false)
    private String passwordUsuario;
    @Column(name="nombre_usuario", length=50, nullable=false)   
    private String nombreUsuario;
    @Column(name="apellido1_usuario", length=50, nullable=false)  
    private String apellido1Usuario;
    @Column(name="apellido2_usuario", length=50, nullable=false)  
    private String apellido2Usuario;
    @Column(name="direccion_usuario", length=250, nullable=false)
    private String direccionUsuario;
    @Column(name="telefono_usuario", length=15, nullable=false)
    private String telefonoUsuario;
    @Column(name="direccion_usuario", length=100, nullable=false)
    private String emailUsuario;
    @Column(name="forma_Pago", nullable=false)
    private String formaPago;


    public Usuario() {
    }

    public Usuario(long id, String loginUsuario, String passwordUsuario, String nombreUsuario, String apellido1Usuario, String apellido2Usuario, String direccionUsuario, String telefonoUsuario, String emailUsuario, String formaPago) {
        this.id = id;
        this.loginUsuario = loginUsuario;
        this.passwordUsuario = passwordUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apellido1Usuario = apellido1Usuario;
        this.apellido2Usuario = apellido2Usuario;
        this.direccionUsuario = direccionUsuario;
        this.telefonoUsuario = telefonoUsuario;
        this.emailUsuario = emailUsuario;
        this.formaPago = formaPago;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public String getPasswordUsuario() {
        return passwordUsuario;
    }

    public void setPasswordUsuario(String passwordUsuario) {
        this.passwordUsuario = passwordUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellido1Usuario() {
        return apellido1Usuario;
    }

    public void setApellido1Usuario(String apellido1Usuario) {
        this.apellido1Usuario = apellido1Usuario;
    }

    public String getApellido2Usuario() {
        return apellido2Usuario;
    }

    public void setApellido2Usuario(String apellido2Usuario) {
        this.apellido2Usuario = apellido2Usuario;
    }

    public String getDireccionUsuario() {
        return direccionUsuario;
    }

    public void setDireccionUsuario(String direccionUsuario) {
        this.direccionUsuario = direccionUsuario;
    }

    public String getTelefonoUsuario() {
        return telefonoUsuario;
    }

    public void setTelefonoUsuario(String telefonoUsuario) {
        this.telefonoUsuario = telefonoUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 89 * hash + (this.loginUsuario != null ? this.loginUsuario.hashCode() : 0);
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
        final Usuario other = (Usuario) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.loginUsuario == null) ? (other.loginUsuario != null) : !this.loginUsuario.equals(other.loginUsuario)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", loginUsuario=" + loginUsuario + ", passwordUsuario=" + passwordUsuario + ", nombreUsuario=" + nombreUsuario + ", apellido1Usuario=" + apellido1Usuario + ", apellido2Usuario=" + apellido2Usuario + ", direccionUsuario=" + direccionUsuario + ", telefonoUsuario=" + telefonoUsuario + ", emailUsuario=" + emailUsuario + ", formaPago=" + formaPago + '}';
    }
    
      public static Usuario findById(EntityManager em, long id) {
        return em.find(Usuario.class, id);
    }

    public static boolean containsAuthor(EntityManager em, long id) {
        return em.find(Usuario.class, id) != null;
    }

    public static long count(EntityManager em) {
        String sql = "SELECT COUNT(x) FROM Usuario x";
        TypedQuery<Long> query = em.createQuery(sql, Long.class);
        Long count = query.getSingleResult();
        return count;
    }

    public static Usuario findByLoginUsuario(EntityManager em, String loginUsuario) {
        String sql = "SELECT x FROM Usuario x WHERE x.loginUsuario = :loginUsuario";
        TypedQuery<Usuario> query = em.createQuery(sql, Usuario.class);
        query.setParameter("loginUsuario", loginUsuario);
        return query.getSingleResult();
    }
    
       public static Usuario findByNombreUsuario(EntityManager em, String nombreUsuario) {
        String sql = "SELECT x FROM Usuario x WHERE x.nombreUsuario = :nombreUsuario";
        TypedQuery<Usuario> query = em.createQuery(sql, Usuario.class);
        query.setParameter("nombreUsuario", nombreUsuario);
        return query.getSingleResult();
    }
    
      public static Usuario findByApellido1Usuario(EntityManager em, String apellido1Usuario) {
        String sql = "SELECT x FROM Usuario x WHERE x.apellido1Usuario = :apellido1Usuario";
        TypedQuery<Usuario> query = em.createQuery(sql, Usuario.class);
        query.setParameter("apellido1Usuario", apellido1Usuario);
        return query.getSingleResult();
    }
           
    
    // Modifying

    public boolean create(EntityManager em) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            createNoTransaction(em);
            et.commit();
            return true;
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
//            throw new Exception("Error saving user");
            e.printStackTrace();
            return false;
        }
    }

    public void createNoTransaction(EntityManager em) {
        em.persist(this);
        em.flush();
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
//            throw new Exception("Error saving user");
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeNoTransaction(EntityManager em) {
        if (em.find(Usuario.class, this.getId()) != null) {
            em.remove(this);
            em.flush();
            return true;
        } else {
            return false;
        }
    }
    
}
