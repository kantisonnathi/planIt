
package in.ac.bitspilani.webapp.registration;


import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(value = 200 )
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
         httpSecurity
                 .antMatcher("/gmail/**").authorizeRequests()
                 .antMatchers("/").permitAll()
                 .anyRequest().authenticated()
                 .and()
                 .oauth2Login();
         

    }
}

