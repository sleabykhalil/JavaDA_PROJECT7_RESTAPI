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
                .antMatchers("/bidList/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/curvePoint/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/rating/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/ruleName/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/trade/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/user/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/home").permitAll()
                .antMatchers("/user/add").permitAll()
                .antMatchers("/swagger-ui/**", "/javainuse-openapi/**").permitAll()
                .antMatchers("/console/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/app/login").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/app/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .anyRequest().authenticated()//.and().httpBasic()
                .and()
                .formLogin().loginPage("/app/login").permitAll()
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .and().exceptionHandling().accessDeniedPage("/app/error")
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
