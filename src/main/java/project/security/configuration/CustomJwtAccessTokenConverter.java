package project.security.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;
import project.persistence.entity.UserEntity;
import project.persistence.repository.UserRepository;
import project.security.ApplicationUser;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomJwtAccessTokenConverter extends JwtAccessTokenConverter {

    private final UserRepository userRepository;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> additionalInformationMap = new HashMap<>();
        ApplicationUser user = (ApplicationUser) authentication.getPrincipal();
        additionalInformationMap.put("email", user.getUsername());
        additionalInformationMap.put("userAuthorities", user.getAuthorities());
        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
        token.setAdditionalInformation(additionalInformationMap);
        return super.enhance(accessToken, authentication);
    }

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> claims) {
        OAuth2Authentication authentication = super.extractAuthentication(claims);
        authentication.setDetails(claims);
        Optional<UserEntity> user = userRepository.findByEmail(claims.get("user_name").toString());
        if (user.isEmpty()) {
            authentication.setAuthenticated(false);
            authentication.getUserAuthentication().setAuthenticated(false);
        }
        return authentication;
    }
}