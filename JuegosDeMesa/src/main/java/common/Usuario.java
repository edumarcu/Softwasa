
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
public class Usuario {
    
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
    private long id;
    private String loginUsuario;
    private String passwordUsuario;
    private String nombreUsuario;
    private String apellido1Usuario;
    private String apellido2Usuario;
    private String direccionUsuario;
    private String telefonoUsuario;
    private String emailUsuario;
    private String formaPago;

    
     /*
    CREATE  TABLE IF NOT EXISTS `mydb`.`tb_usuarios` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login_usuario`  VARCHAR(50) NOT NULL,
  `password_usuario`  VARCHAR(20) NOT NULL,
  `nombre_usuario`  VARCHAR(50) NOT NULL,
  `apellido1_usuario`  VARCHAR(50) NOT NULL,
  `apellido2_usuario`  VARCHAR(50) NOT NULL,
  `direccion_usuario`  VARCHAR(250) NOT NULL,
  `telefono_usuario`  VARCHAR(15) NOT NULL,
  `email_usuario` VARCHAR(100) NOT NULL,
  `forma_pago` ENUM('Tarjeta', 'Contra Reembolso', 'Pay-Pal'),
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
 
  */
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
    
    
    
   
  
}
