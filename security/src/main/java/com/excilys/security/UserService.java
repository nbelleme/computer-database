package com.excilys.security;

import com.excilys.model.user.Role;
import com.excilys.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            logger.info("UserService ---- loadUserByUsername("+s+")");
            com.excilys.model.user.User user = userRepository.findByUsername(s);
            List<GrantedAuthority> authorities = buildUserAuthority(user.getRole());
            return buildUserForAuthentication(user, authorities);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    // Converts com.excilys.model.user.User user to
    // org.springframework.security.core.userdetails.User
    private User buildUserForAuthentication(com.excilys.model.user.User user,
                                            List<GrantedAuthority> authorities) {
        return new User(user.getUsername(),
                user.getPassword(), user.isEnabled(),
                true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<Role> roles) {

        Set<GrantedAuthority> setAuths = new HashSet<>();

        // Build user's authorities
        for (Role role : roles) {
            setAuths.add(new SimpleGrantedAuthority(role.getRole()));
        }

        List<GrantedAuthority> Result = new ArrayList<>(setAuths);

        return Result;
    }
}
