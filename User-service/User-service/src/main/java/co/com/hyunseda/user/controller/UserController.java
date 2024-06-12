package co.com.hyunseda.user.controller;

import co.com.hyunseda.user.access.UserRepository;
import co.com.hyunseda.user.domain.entity.UserEntity;
import co.com.hyunseda.user.domain.service.IUserService;
import co.com.hyunseda.user.domain.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("user")

public class UserController {
    @Autowired
    private IUserService userService;
    @RequestMapping(value = "{userName}",method = RequestMethod.GET, produces = "application/json")
    @ResponseBody//Va a ser una respuesta JSON
    public Optional<UserEntity> getUser(@PathVariable String userName)
    {
        return userService.getUser(userName);
    }

}
