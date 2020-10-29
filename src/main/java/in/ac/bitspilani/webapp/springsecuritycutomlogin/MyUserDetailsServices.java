package in.ac.bitspilani.webapp.springsecuritycutomlogin;

/*
import in.ac.bitspilani.webapp.model.UserEntity;
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
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserEntity> user=userRepository.findByUserName(username);
        user.orElseThrow(()-> new UsernameNotFoundException("Not found:"+username));
           return user.map(MyUserDetails::new).get();
    }
}*/
