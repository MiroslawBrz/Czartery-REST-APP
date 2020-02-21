package pl.miroslawbrz.czartery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.miroslawbrz.czartery.model.User;
import pl.miroslawbrz.czartery.model.UserRole;
import pl.miroslawbrz.czartery.repository.UserRepository;
import pl.miroslawbrz.czartery.repository.UserRoleRepository;

import java.util.HashSet;
import java.util.Set;

@Component
class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserRoleRepository userRoleRepository;

    @Autowired
    public InitialDataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        UserRole adminRole = new UserRole("ROLE_ADMIN", "Administrator");
        UserRole charterAdminRole = new UserRole("ROLE_CHARTER_ADMIN", "Wlasciciel wypozyczalni");
        UserRole userRole = new UserRole("ROLE_USER", "Zwykly uzytkownik");
        Set<UserRole> roles = new HashSet<>();
        userRoleRepository.saveAll(roles);
        roles.add(adminRole);
        roles.add(charterAdminRole);
        roles.add(userRole);

        User user = new User.Builder()
                .userName("Miroslaw")
                .userPassword(passwordEncoder.encode("zxmo192jKl!"))
                .userMail("miroslawbrzezinski@gmail.com")
                .userLastName("Brzezinski").build();

        user.setUserRoles(roles);
        userRepository.save(user);

    }
}