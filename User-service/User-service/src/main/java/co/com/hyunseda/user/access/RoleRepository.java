package co.com.hyunseda.user.access;

import co.com.hyunseda.user.domain.entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity,Long> {
    List<RoleEntity> findRoleEntitiesByNameIn(List<String> roleNames);

}
