//package com.example.productsalemanagement.config;
//
//import com.example.productsalemanagement.service.impl.UserDetailServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import javax.persistence.Access;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Autowired
//    private UserDetailServiceImpl userDetailService;
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf(csrf ->
//                        csrf
//                                .ignoringAntMatchers("/h2-console/**"))
//                .authorizeRequests(auth ->
//                        auth
//                                .antMatchers("/**").permitAll()
//                )
//                .formLogin(form -> form.permitAll())
//                .logout(logout -> logout.permitAll())
////                .userDetailsService(userDetailService)
//                .headers(headers ->
//                        headers
//                                .frameOptions()
//                                .sameOrigin())
//                .build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//}
