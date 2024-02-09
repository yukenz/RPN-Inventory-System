package com.awanrpn.invenmanager.service;

import com.awanrpn.invenmanager.model.entity.User;
import com.awanrpn.invenmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import java.util.function.Function;

@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    private final Function<String, String> passwordEncoder = password
            -> NoOpPasswordEncoder.getInstance().encode(password);

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        User user = null;

        /* Coba find pakai UUID*/
        user = userRepository.findById(usernameOrEmail)
                .orElse(null);

        /* Kalau null coba pakai Email */
        if (user == null) {
            user = userRepository.findByEmail(usernameOrEmail)
                    .orElse(null);
        }

        /* Throw kalau masih null juga */
        if (user == null) {
            throw new UsernameNotFoundException("UUID atau Email tidak ditemukan");
        }


        return org.springframework.security.core.userdetails.User.builder()
                .passwordEncoder(passwordEncoder)
                .username(user.getId())
                .password(user.getPassword())
                .roles(user.getRole().toString())
                .build();
    }
}
