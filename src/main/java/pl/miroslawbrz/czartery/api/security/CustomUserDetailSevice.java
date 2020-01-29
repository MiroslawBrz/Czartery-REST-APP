package pl.miroslawbrz.czartery.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pl.miroslawbrz.czartery.model.User;
import pl.miroslawbrz.czartery.model.UserRole;
import pl.miroslawbrz.czartery.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class CustomUserDetailSevice implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailSevice(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        User user = userRepository.findByUserMail(mail);
        if(user == null)
            throw new UsernameNotFoundException("User not found");
        return new org.springframework.security.core.userdetails.User(
                user.getUserMail(),
                user.getUserPassword(),
                convertAuthorities(user.getUserRoles()));
    }

    private Set<GrantedAuthority> convertAuthorities(Set<UserRole> roles) {

        Set<GrantedAuthority> authorities = new HashSet<>();
        for(UserRole userRole: roles){
            authorities.add(new SimpleGrantedAuthority(userRole.getRole()));
        }
        return authorities;
    }
}
