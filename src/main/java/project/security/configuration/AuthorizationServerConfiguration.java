package project.security.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    private final ClientDetailsService clientDetailsService;

    private final AuthenticationManager authenticationManager;

    private final AuthorizationServerProperties authorization;

    private final CustomJwtAccessTokenConverter converter;

    private final ApplicationContext context;

    private final JwtAccessTokenConverter accessTokenConverter;

    private final DataSource dataSource;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("user")
                .secret(passwordEncoder().encode("secret"))
                .scopes("read", "write");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .tokenStore(tokenStore())
                .tokenEnhancer(accessTokenConverter)
                .accessTokenConverter(accessTokenConverter)
                .authenticationManager(authenticationManager)
                .tokenServices(tokenServices());
    }

    @Bean
    TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer.checkTokenAccess("permitAll()");
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices(TokenStore jwtTokenStore) {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(jwtTokenStore);
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setAccessTokenValiditySeconds(5454545);
        defaultTokenServices.setTokenEnhancer(accessTokenConverter());
        defaultTokenServices.setClientDetailsService(clientDetailsService);
        addUserDetailsService(defaultTokenServices, userDetailsService);
        return defaultTokenServices;
    }

    private void addUserDetailsService(DefaultTokenServices tokenServices, UserDetailsService userDetailsService) {
        if (userDetailsService != null) {
            PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
            provider.setPreAuthenticatedUserDetailsService(new UserDetailsByNameServiceWrapper<>(
                    userDetailsService));
            tokenServices
                    .setAuthenticationManager(new ProviderManager(Collections.singletonList(provider)));
        }
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        Assert.notNull(authorization.getJwt().getKeyStore(), "keyStore cannot be null");
        Assert.notNull(authorization.getJwt().getKeyStorePassword(), "keyStorePassword cannot be null");
        Assert.notNull(authorization.getJwt().getKeyAlias(), "keyAlias cannot be null");
        Resource keyStore = context.getResource(authorization.getJwt().getKeyStore());
        char[] keyStorePassword = authorization.getJwt().getKeyStorePassword().toCharArray();
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(keyStore, keyStorePassword);

        String keyAlias = authorization.getJwt().getKeyAlias();
        char[] keyPassword = Optional.ofNullable(
                        authorization.getJwt().getKeyPassword())
                .map(String::toCharArray).orElse(keyStorePassword);
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair(keyAlias, keyPassword));
        return converter;
    }

    @Bean
    AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setTokenStore(tokenStore());
        return tokenServices;
    }
}
