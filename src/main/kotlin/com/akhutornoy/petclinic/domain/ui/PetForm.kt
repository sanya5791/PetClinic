package com.akhutornoy.petclinic.domain.ui

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.validation.constraints.NotBlank

data class PetForm (
        @NotBlank
        var name: String,
        @NotBlank
        var breed: String,
        var hostId: Long = 0,
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null
)