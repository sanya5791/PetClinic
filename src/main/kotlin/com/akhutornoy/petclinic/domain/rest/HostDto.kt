package com.akhutornoy.petclinic.domain.rest

import com.fasterxml.jackson.annotation.JsonProperty

data class HostDto(
        val id: Long?,
        @JsonProperty("first_name") val firstName: String,
        @JsonProperty("last_name") val lastName: String
)