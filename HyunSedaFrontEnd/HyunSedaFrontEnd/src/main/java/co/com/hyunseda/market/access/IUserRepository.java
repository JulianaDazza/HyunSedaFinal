package co.com.hyunseda.market.access;

import co.com.hyunseda.market.domain.User;
/**
 *
 * @author ACER
 */
public interface IUserRepository {
    
    boolean sign_up();
    
    boolean log_in(User user);
    
    User getUserByUserName(String prmName);
}
