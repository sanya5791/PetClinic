package com.akhutornoy.petclinic.domain.rest

data class PetDto (
        val id: Long,
        val name: String,
        val breed: String,
        val hostId: Long
)