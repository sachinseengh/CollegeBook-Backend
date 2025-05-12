package com.collegeBook.CollegeBook.config;
import com.collegeBook.CollegeBook.enums.RoleEnum;
import com.collegeBook.CollegeBook.filter.JwtFilter;
import com.collegeBook.CollegeBook.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/uploads/**").permitAll()
                .antMatchers("/auth/**").permitAll() // public endpoints like login/register
                .antMatchers("/health-check").permitAll()
                .antMatchers("/admin/**").hasRole(RoleEnum.ADMIN.name())
                .antMatchers("/moderator/**").hasAnyRole(RoleEnum.ADMIN.name(),RoleEnum.MODERATOR.name())// restrict admin routes
                .antMatchers("/user/**", "/post/**").authenticated() // only for logged-in users
                .anyRequest().authenticated() ;// everything else is open
//                .and()
//                .httpBasic() // basic authentication (or use .formLogin() for form-based login)
//                .and()
//                .csrf().disable(); // disable CSRF for API (optional)
        http.cors().configurationSource(corsConfigurationSource());
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable();
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }



    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        return request -> {
            CorsConfiguration config = new CorsConfiguration();
//            config.setAllowedOrigins(Collections.singletonList("http://localhost:5173"));
            config.setAllowedOrigins(Collections.singletonList("https://college-book.vercel.app/"));
            config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Specify allowed methods
            config.setAllowCredentials(true);
            config.setAllowedHeaders(Collections.singletonList("*"));
            config.setExposedHeaders(Collections.singletonList("Authorization")); // Removed extra colon
            config.setMaxAge(3600L);

            return config;
        };
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    //it is used for jwt
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
