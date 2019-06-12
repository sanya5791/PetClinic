package com.akhutornoy.petclinic.security

import com.akhutornoy.petclinic.controller.*
import com.akhutornoy.petclinic.domain.db.Role
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.AuthenticationSuccessHandler

@Configuration
@EnableWebSecurity
class WebSecurityConfig(
        private val userDetailsService: UserDetailsService,
        private val passwordEncoder: PasswordEncoder,
        private val authSuccessHandler: AuthenticationSuccessHandler
) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
    }

    override fun configure(http: HttpSecurity) {
        http
                .authorizeRequests()
                    .antMatchers(
                            "/css/**",
                            AboutController.END_POINT,
                            RegistrationController.REGISTRATION).permitAll() //allow access to all users
                    .antMatchers("/h2-console/**").hasRole("ADMIN") //allow h2 console access to admins only
                    .antMatchers(HostsController.END_POINT, HostsController.END_POINT_INDEX)
                        .hasRole(Role.ADMIN.name)
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage(LoginController.END_POINT)
                    .successHandler(authSuccessHandler)
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