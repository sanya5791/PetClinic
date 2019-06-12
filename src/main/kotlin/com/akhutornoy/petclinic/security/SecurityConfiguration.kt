package com.akhutornoy.petclinic.security

import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.DefaultRedirectStrategy
import org.springframework.security.web.RedirectStrategy
import org.springframework.security.web.authentication.AuthenticationSuccessHandler

@Configuration
class SecurityConfiguration {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    fun passwordEncryptor(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    fun authenticationSuccessHandler(
            redirectStrategy: RedirectStrategy): AuthenticationSuccessHandler = AuthSuccessHandler(redirectStrategy)

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    fun redirectStrategy(): RedirectStrategy = DefaultRedirectStrategy()

}