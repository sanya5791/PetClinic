package com.akhutornoy.petclinic.service

import com.akhutornoy.petclinic.domain.rest.PetDto

interface PetsRestService {
    fun addPet(name: String, breed: String, hostId: Long)
    fun getPetsByHostId(hostId: Long): List<PetDto>
}