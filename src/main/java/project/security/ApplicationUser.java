package project.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import project.persistence.entity.UserEntity;

import java.util.Collection;
import java.util.List;

public class ApplicationUser implements UserDetails {

    private static final long serialVersionUID = -321918075049269332L;

    private final String username;

    private final String password;

    private final GrantedAuthorityEnum authority;

    public ApplicationUser(UserEntity user) {
        username = user.getEmail();
        password = user.getPassword();
        authority = user.getAuthority();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
