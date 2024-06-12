/**
 * @ Clase que va a contener los usuarios
 */
package co.com.hyunseda.user.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder//Implementar el patrón builder para crear objetos de la clase
@Entity
@Table (name = "Users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    @Size(max = 80)
    private String email;
    @Column(unique = true)
    private String userName;
    private String password;

    @Column(name = "isEnabled")
    private boolean isEnabled;

    @Column(name = "accountNotExpired")
    private boolean accountNotExpired;

    @Column(name = "accountNotLocked")
    private boolean accountNotLocked;

    @Column(name = "credentialNoExpired")
    private boolean credentialNoExpired;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",//Creación de la tabla intermedia.
            joinColumns = @JoinColumn (name = "user_Id"), inverseJoinColumns = @JoinColumn (name = "role_Id"))
    private Set<RoleEntity> roles = new HashSet<>();/*Set no permite tener elementos duplicados*/


}
