package com.awanrpn.invenmanager.config.filter;

import com.awanrpn.invenmanager.model.entity.Token;
import com.awanrpn.invenmanager.model.entity.User;
import com.awanrpn.invenmanager.repository.UserRepository;
import jakarta.annotation.Nullable;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@Component
@RequiredArgsConstructor
@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        String authorization = request.getHeader("Authorization");

        /*b64(user).b64(token)*/
        if (securityContext.getAuthentication() == null && authorization != null) {

            String token = getBearer(authorization);
            UserTokenUUID tokenDetail = getTokenDetail(token);

            String userUUID = tokenDetail.userUUID;
            String tokenUUID = tokenDetail.tokenUUID;

            User user = userRepository.findById(userUUID)
                    .orElse(null);

            if (isTokenValid(user, tokenUUID)) {

                AbstractAuthenticationToken tokenAuth = new UsernamePasswordAuthenticationToken(
                        user.getId(), user.getPassword(), Set.of(new SimpleGrantedAuthority(user.getRole().toString()))
                );
                tokenAuth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                securityContext.setAuthentication(tokenAuth);

            }


        }

        filterChain.doFilter(request, response);
    }

    private String getBearer(String token) {

        if (Strings.isEmpty(token) || Strings.isBlank(token)) {
            return null;
        }

        if (!token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring("Bearer ".length());

    }

    private UserTokenUUID getTokenDetail(String bearerToken) {

        Pattern tokenDetailPart = Pattern.compile("^(.+)\\.(.+)$");
        Matcher resultMatch = tokenDetailPart.matcher(bearerToken);

        if (!resultMatch.matches()) {
            return null;
        }

        String userUUID = resultMatch.group(1);
        String tokenUUID = resultMatch.group(2);

        if (userUUID == null || tokenUUID == null) {
            return null;
        }

        return new UserTokenUUID(userUUID, tokenUUID);

    }

    @Nullable
    private record UserTokenUUID(
            @NotNull
            String userUUID,
            @NotNull
            String tokenUUID
    ) {
    }

    private Boolean isTokenValid(User user, String tokenUUID) {

        if (user == null || tokenUUID == null) {
            return false;
        }

        Set<Token> tokens = user.getTokens();
        Optional<Token> first = tokens.stream()
                .filter(token -> token.getToken().equals(tokenUUID))
                .findFirst();

        log.info("Token result {}", first.orElse(null));

        return first.isPresent()
                && !first.get().isBlacklisted();

    }

}
