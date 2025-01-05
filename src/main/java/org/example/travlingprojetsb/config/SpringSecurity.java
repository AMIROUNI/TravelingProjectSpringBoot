package org.example.travlingprojetsb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); //From Spring6 no need to set user details , it will automatically set user details, service and password encoded objects to auntication.
    }

    // configure SecurityFilterChain

    @Configuration
    public class SecurityConfig {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .authorizeHttpRequests(auth -> auth
                            // Permit static resources (CSS, JS, Images)
                            .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()

                            // Publicly accessible endpoints
                            .requestMatchers("/register/**", "/").permitAll()

                            // Role-based access control
                            .requestMatchers("/hotel", "/hotel/list").hasAnyRole("ADMIN_HOTEL", "ADMIN")
                            .requestMatchers("/ville/list").hasAnyRole("ADMIN_VOL", "ADMIN")
                            .requestMatchers("/aeroport").hasAnyRole("ADMIN_HOTEL", "ADMIN")
                            .requestMatchers("/clients").hasRole("ADMIN")
                            .requestMatchers("/vols/allVol").hasAnyRole("ADMIN_VOL", "ADMIN")
                            .requestMatchers("/reservations/all").hasAnyRole("ADMIN_HOTEL", "ADMIN_VOL", "ADMIN")
                            .requestMatchers("/packe/list").hasAnyRole("ADMIN_HOTEL", "ADMIN_VOL", "ADMIN")

                            // All other requests require authentication
                            .anyRequest().authenticated()
                    )
                    // Login configuration
                    .formLogin(form -> form
                            .loginPage("/login")
                            .loginProcessingUrl("/login")
                            .defaultSuccessUrl("/")
                            .permitAll()
                    )
                    // Logout configuration
                    .logout(logout -> logout
                            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                            .logoutSuccessUrl("/")
                            .permitAll()
                    );

            return http.build();
        }
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());      //Just passwordEncoder bean and UserDetailsService bean must be there then no need of this configuration setting as sping6 will automatically set user details, service and password encoded objects to auntication.
    }
}
