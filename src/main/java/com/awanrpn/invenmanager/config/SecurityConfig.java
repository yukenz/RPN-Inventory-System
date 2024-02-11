package com.awanrpn.invenmanager.config;

import com.awanrpn.invenmanager.config.filter.CustomAuthorizationFilter;
import com.awanrpn.invenmanager.controller.AuthenticationEntryPointController;
import com.awanrpn.invenmanager.model.entity.User;
import com.awanrpn.invenmanager.repository.UserRepository;
import com.awanrpn.invenmanager.service.UserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserRepository userRepository;
    private final AuthenticationEntryPointController authenticationEntryPointController;
//    private final CustomAuthorizationFilter customAuthorizationFilter;

    private final String ADMIN = User.Role.ADMIN.toString();
    private final String USER = User.Role.USER.toString();

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http

                .securityMatcher("/api/**")
                .authorizeHttpRequests(set -> set
                        // User and Admin
                        .requestMatchers(
                                "/api/products/**")
                        .hasAnyAuthority(USER, ADMIN)
                        // Admin Only
                        .requestMatchers(
                                "/api/categories/**",
                                "/api/orders/**",
                                "/api/order-items/**")
                        .hasAnyAuthority(ADMIN)
                        // Public
                        .requestMatchers(
                                "/api/users/**",
                                "/api/auth/**"
                        ).permitAll()
                )
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .sessionManagement(make -> make.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(make -> make.authenticationEntryPoint(authenticationEntryPointController))
                .authenticationManager(authManager())
                .addFilterBefore(new CustomAuthorizationFilter(userRepository), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    UserDetailsService userDetailsService() {
        return new UserDetailServiceImpl(userRepository);
    }


    @Bean
    public AuthenticationManager authManager() {
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return new ProviderManager(authProvider);
    }

}
