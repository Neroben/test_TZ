package project.security.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import static project.web.DeliveryController.DELIVERY_URL;
import static project.web.ProductModificationController.PRODUCT_MODIFICATION_URL;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    public static final String BASE_API = "api/v1";
    public static final String SLASH_BASE_API = "/" + BASE_API;
    public static final String ANY_URL = "/**";
    public static final String AUTHORITY_SUPPLIER = "SUPPLIER";
    public static final String AUTHORITY_CONSUMER = "CONSUMER";
    public static final String OAUTH_URL = "/oauth/**";

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, OAUTH_URL).permitAll()
                .antMatchers(HttpMethod.POST, OAUTH_URL).permitAll()
                .antMatchers(HttpMethod.POST, SLASH_BASE_API + "/user/registration").permitAll()
                .antMatchers(HttpMethod.POST, SLASH_BASE_API + PRODUCT_MODIFICATION_URL).hasAuthority(AUTHORITY_SUPPLIER)
                .antMatchers(HttpMethod.PUT, SLASH_BASE_API + PRODUCT_MODIFICATION_URL).hasAuthority(AUTHORITY_SUPPLIER)
                .antMatchers(HttpMethod.DELETE, SLASH_BASE_API + PRODUCT_MODIFICATION_URL).hasAuthority(AUTHORITY_SUPPLIER)
                .antMatchers(HttpMethod.POST, SLASH_BASE_API + PRODUCT_MODIFICATION_URL + ANY_URL).hasAuthority(AUTHORITY_SUPPLIER)
                .antMatchers(HttpMethod.DELETE, SLASH_BASE_API + PRODUCT_MODIFICATION_URL + ANY_URL).hasAuthority(AUTHORITY_SUPPLIER)
                .antMatchers(HttpMethod.POST, SLASH_BASE_API + DELIVERY_URL).hasAuthority(AUTHORITY_CONSUMER)
                .antMatchers(HttpMethod.DELETE, SLASH_BASE_API + DELIVERY_URL + ANY_URL).hasAuthority(AUTHORITY_CONSUMER)
                .anyRequest().authenticated();
    }


}
