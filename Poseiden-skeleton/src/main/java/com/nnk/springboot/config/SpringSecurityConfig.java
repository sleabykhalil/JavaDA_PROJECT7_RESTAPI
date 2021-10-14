package com.nnk.springboot.config;

import com.nnk.springboot.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;


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

//        http.sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
//                .maximumSessions(2)
////                .expiredUrl("/sessionExpired.html").and()
////                .invalidSessionUrl("/invalidSession.html")
//        ;

        http
                .csrf().disable()
                .authorizeRequests()
                //.antMatchers("/admin/**").hasRole("ADMIN")
                //.antMatchers("/").anonymous()
                .antMatchers("/user/**").hasAuthority("USER")
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //.loginPage("/login").permitAll()
                .defaultSuccessUrl("/")
                .and()
                .logout()
                .deleteCookies("JSESSIONID")
                .and()
                .rememberMe();
       /* http.authorizeRequests()
                .antMatchers("/bidList/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/curvePoint/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/rating/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/ruleName/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/trade/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/user/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                //.antMatchers("/**","/home**").anonymous()
                .antMatchers("/home","/").permitAll()
                .antMatchers("/**").permitAll()
                .antMatchers("/user/add").permitAll()
                .antMatchers("/swagger-ui/**", "/javainuse-openapi/**").permitAll()
                .antMatchers("/console/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/resources/**").permitAll()
                .anyRequest().authenticated()
                //.antMatchers("/login").permitAll()
                //.antMatchers("/","/home**").anonymous()
                .and().httpBasic()
                .and()
                .formLogin()
                //.loginPage("/login").permitAll().usernameParameter("username").passwordParameter("password")
                //.defaultSuccessUrl("/")
                .and()
                .csrf().disable()
                .logout()
                .deleteCookies("JSESSIONID")
                .and()
                .rememberMe();*/
/*       http.authorizeRequests()
//                .antMatchers("/swagger-ui/**", "/javainuse-openapi/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic();

//        http.authorizeRequests().antMatchers("/").permitAll().and()
//                    .authorizeRequests().antMatchers("/console/**").permitAll();
//        http.csrf().disable();
//        http.headers().frameOptions().disable();
*/
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}
