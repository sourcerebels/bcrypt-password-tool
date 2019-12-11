package com.sourcerebels.tools.bcryptpassword;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfiguration.class);

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.info("configure");
        http
            .authorizeRequests(authorizeRequests ->
                authorizeRequests
                    .antMatchers("/", "/encode", "/decode").permitAll()
                    .anyRequest().authenticated()
            )
            .formLogin(withDefaults())
            .logout()
            .permitAll();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/encode", "/decode");
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
            User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
