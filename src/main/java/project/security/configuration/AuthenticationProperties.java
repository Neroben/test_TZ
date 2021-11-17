package project.security.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource("classpath:application.yaml")
@ConfigurationProperties(prefix = "authentication")
class AuthenticationProperties {

    private String clientId;

    private String clientSecret;

    private int accessTokenValidityInSeconds;

    private int refreshTokenValidityInSeconds;

    private String[] authorizationGrantTypes;
}
