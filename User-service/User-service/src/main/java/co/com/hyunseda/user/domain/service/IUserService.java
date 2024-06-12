package co.com.hyunseda.user.domain.service;

import co.com.hyunseda.user.domain.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface IUserService {
    public Optional<UserEntity> getUser(String userName);
}
