package in.ac.bitspilani.webapp.springsecuritycutomlogin;

import in.ac.bitspilani.webapp.model.UserEntity;
import in.ac.bitspilani.webapp.user.AuthenticationProvider;
import in.ac.bitspilani.webapp.user.User;
import in.ac.bitspilani.webapp.user.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component

public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    UserRepository userRepository;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        customOauth2user oauth2user=(customOauth2user)authentication.getPrincipal();
        String email=oauth2user.getEmail();
        User user=new User();



        if(userRepository.findByEmail(email)==null)
        {
            user.setAuthProvider(AuthenticationProvider.GOOGLE);
            user.setEmail(email);
            user.setName(oauth2user.getName());
           userRepository.save(user);
        }

        else {
            System.out.println("Customer's email: " + email);
        }
        super.onAuthenticationSuccess(request,response,authentication);
    }
}
