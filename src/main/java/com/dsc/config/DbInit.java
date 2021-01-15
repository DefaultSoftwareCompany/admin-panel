package com.dsc.config;

import com.dsc.model.Roles;
import com.dsc.model.User;
import com.dsc.repository.RolesRepository;
import com.dsc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class DbInit {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolesRepository rolesRepository;

    @PostConstruct
    private void postConstruct() {
        Set<Roles> roles = new HashSet<>();
        Roles role = new Roles();
        role.setRoleName("CUSTOMER");
        roles.add(rolesRepository.save(role));
        role = new Roles();
        role.setRoleName("EDITOR");
        roles.add(rolesRepository.save(role));
        role = new Roles();
        role.setRoleName("ADMIN");
        roles.add(rolesRepository.save(role));
        role = new Roles();
        role.setRoleName("OWNER");
        roles.add(rolesRepository.save(role));
        User user = new User();
        user.setFirstName("Sunnatillo");
        user.setLastName("Sharipov");
        user.setUserName("sunnatillo");
        user.setPassword(new BCryptPasswordEncoder().encode("123456"));
        user.setPhoneNumber("+998997375427");
        user.setEmail("sharipovsunnatillo@gmail.com");
        user.setRoles(roles);
        userRepository.save(user);
    }
}
