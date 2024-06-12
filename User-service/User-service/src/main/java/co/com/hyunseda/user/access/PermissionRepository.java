package co.com.hyunseda.user.access;

import co.com.hyunseda.user.domain.entity.PermissionsEntity;
import org.springframework.data.repository.CrudRepository;

public interface PermissionRepository extends CrudRepository<PermissionsEntity,Long> {
}
