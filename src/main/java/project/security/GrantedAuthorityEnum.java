package project.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum GrantedAuthorityEnum implements GrantedAuthority {

    ADMIN("ADMIN"),
    SUPPLIER("SUPPLIER"),
    CONSUMER("CONSUMER");

    private final String authority;

    @Override
    public String getAuthority() {
        return authority;
    }
}
