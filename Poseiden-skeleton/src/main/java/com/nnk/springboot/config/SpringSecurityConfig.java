package com.nnk.springboot.config;

import com.nnk.springboot.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(myUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    /**
     * http filter
     *
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/bidList/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/curvePoint/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/rating/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/ruleName/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/trade/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/user/update").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/user/list").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/secure/article-details").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/user/add").permitAll()
                .antMatchers("/error").permitAll()
                .antMatchers("/user/validate").permitAll()
                .antMatchers("/swagger-ui/**", "/javainuse-openapi/**").permitAll()
                .antMatchers("/console/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/app/login").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/app/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/", "/home").anonymous()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()//.and().httpBasic()
                .and()
                .formLogin().loginPage("/app/login").permitAll()
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .and().exceptionHandling().accessDeniedPage("/app/error")
                .and().oauth2Login()
                .and()
                .csrf().disable()
                .logout()
                .deleteCookies("JSESSIONID")
                .and()
                .rememberMe();
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
