/**
 * @ Clase que va a contener los roles
 */

package co.com.hyunseda.user.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private ERole name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "roles_permissions",//Creación de la tabla intermedia.
            joinColumns = @JoinColumn (name = "role_Id"), inverseJoinColumns = @JoinColumn (name = "permission_Id"))
    private Set<PermissionsEntity> permissions = new HashSet<>();
}
