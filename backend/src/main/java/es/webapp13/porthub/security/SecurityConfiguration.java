package es.webapp13.porthub.security;

import java.security.SecureRandom;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    final
    RepositoryUserDetailsService userDetailsService;

    public SecurityConfiguration(RepositoryUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10, new SecureRandom());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Public pages
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/error").permitAll();
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().antMatchers("/loginerror").permitAll();
        http.authorizeRequests().antMatchers("/signup/**").permitAll();
        http.authorizeRequests().antMatchers("/logout").permitAll();
        http.authorizeRequests().antMatchers("/shop").permitAll();
        http.authorizeRequests().antMatchers("/search").permitAll();
        http.authorizeRequests().antMatchers("/search/**").permitAll();
        http.authorizeRequests().antMatchers("/reset/password").permitAll();
        http.authorizeRequests().antMatchers("/reset/password").permitAll();
        http.authorizeRequests().antMatchers("/templates/**").permitAll();

        // Private pages
        http.authorizeRequests().antMatchers("/settings/**").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/logout/confirmation").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/profile").hasAnyRole("USER");


        // Admin pages
        http.authorizeRequests().antMatchers("/admin").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/admin/**").hasAnyRole("ADMIN");

        // Login form
        http.formLogin().loginPage("/login");
        http.formLogin().usernameParameter("id");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/");
        http.formLogin().failureUrl("/loginerror");

        // Logout
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/");


    }
}


