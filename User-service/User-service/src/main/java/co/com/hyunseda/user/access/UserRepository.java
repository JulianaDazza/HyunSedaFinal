package co.com.hyunseda.user.access;

import co.com.hyunseda.user.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository <UserEntity, Long> {

    Optional<UserEntity> findByUserName (String userName);

}
