package com.akhutornoy.petclinic.controller.validator

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ValidatorConfiguration {

    @Bean
    fun addHostValidator() = AddHostValidator()

}