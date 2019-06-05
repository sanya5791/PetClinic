package com.akhutornoy.petclinic.domain.mapper

import com.akhutornoy.petclinic.domain.db.PetEntity
import com.akhutornoy.petclinic.domain.rest.PetDto
import com.akhutornoy.petclinic.domain.ui.PetForm

class PetEntityMapper {

    fun map(petForm: PetForm) = PetEntity(
            petForm.name,
            petForm.breed,
            petForm.hostId,
            petForm.id
    )

    fun map(name: String, breed: String, hostId: Long) = PetEntity(
            name,
            breed,
            hostId
    )

    fun map(source: PetDto) = PetEntity(
            source.name,
            source.breed,
            source.hostId,
            source.id
    )
}