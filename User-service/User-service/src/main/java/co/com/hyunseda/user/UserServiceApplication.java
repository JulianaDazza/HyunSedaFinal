package co.com.hyunseda.user;

import co.com.hyunseda.user.access.PermissionRepository;
import co.com.hyunseda.user.access.RoleRepository;
import co.com.hyunseda.user.access.UserRepository;
import co.com.hyunseda.user.domain.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PermissionRepository permissionRepository;

	@Autowired
	PasswordEncoder passwordEncoder;
	@Bean
	CommandLineRunner init() {
		return args -> {
			/**<!Crear Permisos*/
			PermissionsEntity createPermission = PermissionsEntity.builder()
					.name("CREATE").build();
			PermissionsEntity updatePermission = PermissionsEntity.builder()
					.name("UPDATE").build();
			PermissionsEntity deletePermission = PermissionsEntity.builder()
					.name("DELETE").build();
			PermissionsEntity readPermission = PermissionsEntity.builder()
					.name("READ").build();

			permissionRepository.save(createPermission);
			permissionRepository.save(updatePermission);
			permissionRepository.save(deletePermission);
			permissionRepository.save(readPermission);
			/**<!Crear Roles*/
			RoleEntity adminRol = RoleEntity.builder()
					.name(ERole.ADMIN)
					.permissions(Set.of(createPermission,updatePermission,readPermission))
					.build();
			RoleEntity superAdminRol = RoleEntity.builder()
					.name(ERole.SUPER_ADMIN)
					.permissions(Set.of(createPermission,updatePermission,deletePermission,readPermission))
					.build();
			RoleEntity editorRol = RoleEntity.builder()
					.name(ERole.EDITOR)
					.permissions(Set.of(updatePermission,readPermission))
					.build();
			RoleEntity invitedRole = RoleEntity.builder()
					.name(ERole.INVITED)
					.permissions(Set.of(readPermission))
					.build();
			roleRepository.save(superAdminRol);
			roleRepository.save(adminRol);
			roleRepository.save(editorRol);
			roleRepository.save(invitedRole);
			/**<!Crear Usuarios*/
			UserEntity user1 = UserEntity.builder()
					.email("santiago@gmail.com")
					.userName("Santiago")
					.password(passwordEncoder.encode("1234"))
					.isEnabled(true)
					.accountNotExpired(true)
					.accountNotLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(superAdminRol,adminRol))
					.build();
			UserEntity user2 = UserEntity.builder()
					.email("andrea@gmail.com")
					.userName("Andrea")
					.password(passwordEncoder.encode("1234"))
					.isEnabled(true)
					.accountNotExpired(true)
					.accountNotLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(editorRol))
					.build();
			UserEntity user3 = UserEntity.builder()
					.email("juliana@gmail.com")
					.userName("Juliana")
					.password(passwordEncoder.encode("1234"))
					.isEnabled(true)
					.accountNotExpired(true)
					.accountNotLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(invitedRole))
					.build();

			/**Guardar en DB*/
			userRepository.saveAll(List.of(user1,user2,user3));
		};

	}
}
