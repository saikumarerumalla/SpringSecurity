package com.explore.SpringSecurity.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringConfig {

    @Autowired
    public UserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    // adding only this single bean will disable the security as there are no filters added
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.build();
//    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
//        http.authorizeHttpRequests(request -> request.anyRequest().authenticated()); //check authorization for all endpoints
        http.authorizeHttpRequests(request -> request
                .requestMatchers("register", "login")   //skip authorization for these endpoints
                .permitAll()
                .anyRequest().authenticated());
//        http.formLogin(Customizer.withDefaults()); //for UI
        http.httpBasic(Customizer.withDefaults()); //for Postman
        http.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        //making the session stateless, will not work with the login form as it will redirect to the login form again, if you it to work with UI also then comment the formlogin
        http.addFilterBefore( jwtFilter , UsernamePasswordAuthenticationFilter.class); //Ads JWT filter before the userpaswordauthentication filter
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(); //for DB
//        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance()); //when there is no encoding
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12)); //when there is encoding to the password
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }




//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails user1 = User
//                .withDefaultPasswordEncoder()
//                .username("sai")
//                .password("sai")
//                .roles("ADMIN")
//                .build();
//        UserDetails user2 = User
//                .withDefaultPasswordEncoder()
//                .username("aryan")
//                .password("aryan")
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user1, user2);
//    }


}
