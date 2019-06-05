package com.akhutornoy.petclinic.domain.rest

import com.fasterxml.jackson.annotation.JsonProperty

data class PetDto (
        @JsonProperty("id") val id: Long?,
        @JsonProperty("name", required = true) val name: String,
        @JsonProperty("breed", required = true) val breed: String,
        @JsonProperty("host_id", required = true) val hostId: Long
)