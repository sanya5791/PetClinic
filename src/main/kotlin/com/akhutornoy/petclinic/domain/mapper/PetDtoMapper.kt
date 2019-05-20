package com.akhutornoy.petclinic.domain.mapper

import com.akhutornoy.petclinic.domain.db.PetEntity
import com.akhutornoy.petclinic.domain.rest.PetDto

class PetDtoMapper {

    fun map(entity: PetEntity): PetDto {
        if(entity.id == null) throw IllegalArgumentException("PetId cannot be null")

        return PetDto(
                entity.id!!,
                entity.name,
                entity.breed,
                entity.hostId
        )
    }

}