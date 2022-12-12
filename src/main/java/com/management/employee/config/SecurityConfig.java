package com.management.employee.config;

import com.management.employee.security.CustomerUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebSecurity

@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomerUserDetailService customerUserDetailService;

    @Autowired
    AuthenticationSuccessHandler successHandler;





    @Override
    protected void configure(HttpSecurity http) throws Exception {
       /* http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .anyRequest()
                //.antMatchers("/**").permitAll().and().formLogin().loginPage("/signin").loginProcessingUrl("/dologin")
              //  .successHandler(successHandler)
                .authenticated()
                .and()
                .httpBasic();*/
        http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/**").permitAll().and().formLogin().loginPage("/signin").loginProcessingUrl("/dologin")
                .successHandler(successHandler)

                .and().csrf().disable().httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
   auth.userDetailsService(this.customerUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
   public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
}
