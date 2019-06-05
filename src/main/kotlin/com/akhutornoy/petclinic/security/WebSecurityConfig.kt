package com.akhutornoy.petclinic.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
class WebSecurityConfig(
        private val passwordEncoder: PasswordEncoder
) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth
                .inMemoryAuthentication()
                    .passwordEncoder(passwordEncoder)
                    .withUser("admin")
                    .password(BCryptPasswordEncoder().encode("password"))
                    .roles("ADMIN")
    }

    override fun configure(http: HttpSecurity) {
        http
                .authorizeRequests()
                    .antMatchers("/about").permitAll() //allow access to all users
                    .antMatchers("/h2-console/**").hasRole("ADMIN") //allow h2 console access to admins only
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll()
                    .and()
                //to enable H2 console
                .csrf().ignoringAntMatchers("/h2-console/**")
                .and().headers().frameOptions().sameOrigin()
    }

}