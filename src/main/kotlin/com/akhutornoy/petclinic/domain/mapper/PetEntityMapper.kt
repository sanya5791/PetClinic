package com.akhutornoy.petclinic.domain.mapper

import com.akhutornoy.petclinic.domain.db.PetEntity
import com.akhutornoy.petclinic.domain.ui.PetForm

class PetEntityMapper {

    fun map(petForm: PetForm) = PetEntity(
            petForm.name,
            petForm.breed,
            petForm.hostId,
            petForm.id
    )

}