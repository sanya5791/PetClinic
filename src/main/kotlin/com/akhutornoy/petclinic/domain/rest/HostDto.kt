package com.akhutornoy.petclinic.domain.rest

import com.fasterxml.jackson.annotation.JsonProperty

data class HostDto(
        val id: Long?,
        @JsonProperty("first_name") val firstName: String,
        @JsonProperty("last_name") val lastName: String,
        @JsonProperty("last_name") val userDto: UserDto
)

data class UserDto(
        @JsonProperty("user_login") val userLogin: String,
        @JsonProperty("user_password") val userPassword: String,
        @JsonProperty("user_role") val userRole: String
)