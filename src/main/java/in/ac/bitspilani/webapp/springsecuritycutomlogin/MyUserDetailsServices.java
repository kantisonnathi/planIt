package in.ac.bitspilani.webapp.springsecuritycutomlogin;


import in.ac.bitspilani.webapp.model.UserEntity;
import in.ac.bitspilani.webapp.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsServices implements UserDetailsService {
    @Autowired
    JPAUserRepository userRepository;
    @Override
    public MyUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<UserEntity> user=userRepository.findByEmail(email);
        user.orElseThrow(()-> new UsernameNotFoundException("Not found:"+email));
           return user.map(MyUserDetails::new).get();
    }
}
