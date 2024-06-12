package co.com.hyunseda.market.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)

/**
 *
 * @author Libardo Pantoja, Julio A. Hurtado
 */
public class User {
    @JsonProperty("id")
    private Long userId;
    @JsonProperty("email")
    private String email;
    @JsonProperty("userName")
    private String userName;
    @JsonProperty("password")
    private String password;
    @JsonProperty("isEnabled")
    private String isEnabled;
    @JsonProperty("accountNotExpired")
    private String accountNotExpired;
    @JsonProperty("accountNotLocked")
    private String accountNotLocked;
    @JsonProperty("credentialNoExpired")
    private String credentialNoExpired;
    @JsonProperty("roles")
    private Set<Rol> roles = new HashSet<>();
    
    //Para crear Usuario
    public User(String email, String userName, String password) {
        this.email = email;
        this.userName = userName;
        this.password = password;
    }
    //Para Login
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    
    

    public User() {
    }

    
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
    
    

}

