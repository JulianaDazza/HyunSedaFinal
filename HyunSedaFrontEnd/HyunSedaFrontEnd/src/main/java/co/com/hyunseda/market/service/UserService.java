package co.com.hyunseda.market.service;

import co.com.hyunseda.market.access.IUserRepository;
import co.com.hyunseda.market.domain.User;

/**
 *
 * @author ACER
 */
public class UserService {
    IUserRepository UserRepository;

    /**
     * @param iUserRepository
     *@brief Constructor de la clase
     */
    public UserService(IUserRepository iUserRepository) {
        this.UserRepository = iUserRepository;
    }
    
    public boolean sing_up(){
        return UserRepository.sign_up();
    }
    
    public boolean log_in(User prmUser)
    {
        return UserRepository.log_in(prmUser);
    }
    
    public User getUserByUserName(String userName)
    {
        return UserRepository.getUserByUserName(userName);
    }
    
    
    
}
