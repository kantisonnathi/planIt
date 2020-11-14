package in.ac.bitspilani.webapp.springsecuritycutomlogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Order(value = 200 )
public class SecSecurityConfig extends WebSecurityConfigurerAdapter {
    @Qualifier("myUserDetailsServices")
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        // authentication manager (see below)
        //make it database oriented or whatever
        auth.userDetailsService(userDetailsService);
      /* auth.inMemoryAuthentication()
                .withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
                .and()
                .withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN");*/

    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // http builder configurations for authorize requests and form login (see below)
      /*  http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("USER","ADMIN")
                .antMatchers("/").permitAll()
                .and().formLogin();*/

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/*.js").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/registration/**").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/login*").permitAll()
                .antMatchers("/confirm-account").permitAll()
                .antMatchers("/change-password").permitAll()
                .antMatchers("/custom_login").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/perform_login").permitAll()
                .antMatchers("/forgot").permitAll()
                .antMatchers("/changepwd").permitAll()
                .antMatchers("/phoneverify").permitAll()
                .antMatchers("/dashboard").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/custom_login")
                .defaultSuccessUrl("/dashboard",true)
                .loginProcessingUrl("/perform_login")
                .failureUrl("/custom_login?error=true")
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/custom_login")
                .and().oauth2Login()
                .permitAll();

    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}

