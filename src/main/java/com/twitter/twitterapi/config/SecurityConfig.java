    package com.twitter.twitterapi.config;
    
    import com.twitter.twitterapi.service.CustomUserDetailsService;
    import lombok.AllArgsConstructor;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.config.Customizer;
    import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.security.web.SecurityFilterChain;
    @AllArgsConstructor
    @Configuration
    @EnableWebSecurity
    public class SecurityConfig {
        
        private final CustomUserDetailsService customUserDetailsService;


        @Bean
        public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }


        @Bean
        public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
            AuthenticationManagerBuilder authenticationManagerBuilder =
                    http.getSharedObject(AuthenticationManagerBuilder.class);
            authenticationManagerBuilder.userDetailsService(customUserDetailsService)
                    .passwordEncoder(passwordEncoder());
            return authenticationManagerBuilder.build();
        }




        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/auth/login", "/auth/register").permitAll()   // register endpoint herkese açık
                            .anyRequest().authenticated()  // diğer tüm endpointlere kimlik doğrulama gerek
                    )
                    .httpBasic(Customizer.withDefaults());  //
    
            return http.build();
        }
    }
    
