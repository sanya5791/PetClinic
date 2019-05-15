package com.akhutornoy.petclinic.domain.mapper

import com.akhutornoy.petclinic.domain.db.PetEntity
import com.akhutornoy.petclinic.domain.ui.PetForm

class PetFormMapper {

    fun map(petEntity: PetEntity) = PetForm(
                petEntity.name,
                petEntity.breed,
                petEntity.hostId,
                petEntity.id
        )

}