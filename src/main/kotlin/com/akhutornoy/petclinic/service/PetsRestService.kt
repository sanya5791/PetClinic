package com.akhutornoy.petclinic.service

import com.akhutornoy.petclinic.domain.rest.PetDto

interface PetsRestService {
    fun addPet(petDto: PetDto): PetDto
    fun getPetsByHostId(hostId: Long): List<PetDto>
    fun delete(petId: Long)
}