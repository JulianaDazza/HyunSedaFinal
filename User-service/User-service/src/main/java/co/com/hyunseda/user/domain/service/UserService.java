package co.com.hyunseda.user.domain.service;

import co.com.hyunseda.user.access.UserRepository;
import co.com.hyunseda.user.domain.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService implements IUserService{
    @Autowired
    UserRepository userRepository;
    @Override
    public Optional<UserEntity> getUser(String userName) {
        Optional<UserEntity> userFounded = userRepository.findByUserName(userName);
        return userFounded;
    }
}
