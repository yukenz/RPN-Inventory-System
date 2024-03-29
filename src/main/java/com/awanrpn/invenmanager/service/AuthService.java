package com.awanrpn.invenmanager.service;

import com.awanrpn.invenmanager.model.dto.auth.AuthRequest;
import com.awanrpn.invenmanager.model.entity.Token;
import com.awanrpn.invenmanager.model.entity.User;
import com.awanrpn.invenmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {


    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public Set<Token>
    login(AuthRequest authRequest) {

        User user = null;
        String idOrEmail = authRequest.id();
        String password = authRequest.password();

        /* Coba find pakai UUID*/
        user = userRepository.findById(idOrEmail)
                .orElse(null);

        /* Kalau null coba pakai Email */
        if (user == null) {
            user = userRepository.findByEmail(idOrEmail)
                    .orElse(null);
        }

        /* Throw kalau masih null juga */
        if (user == null) {
            throw new IllegalArgumentException("UUID atau Email tidak ditemukan untuk id ini");
        }

        /* Cek Password */
        boolean passMatch = passwordEncoder.matches(
                passwordEncoder.encode(user.getPassword()),
                passwordEncoder.encode(password)
        );

        /* Password Salah*/
        if (!passMatch) {
            throw new IllegalArgumentException("Password Salah");
        }

        /* Buatkan Token */
        user.getTokens()
                .add(generateToken());

        User savedUserWithToken = userRepository.save(user);
        return savedUserWithToken.getTokens();

    }

    private static Token generateToken() {
        LocalDateTime now = LocalDateTime.now();
        return new Token(UUID.randomUUID().toString(),
                "UUID",
                now.plus(Duration.ofMinutes(30)),
                false,
                now);
    }

    public Set<Token>
    logout(
            String userUUID,
            String tokenUUID
    ) {


        User user = userRepository.findById(userUUID)
                .orElseThrow(() -> new IllegalArgumentException("User ID Tidak Ditemukan"));

        /* Remove Token Yang Cocok */
        Token token = user.getTokens().stream()
                .filter(tokenFiltering -> tokenFiltering.getToken().equals(tokenUUID))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Token Tidak Ditemukan"));
        user.getTokens().remove(token);

        userRepository.save(user);
        return user.getTokens();

    }


}
