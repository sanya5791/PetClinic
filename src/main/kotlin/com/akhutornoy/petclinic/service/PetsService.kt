package com.akhutornoy.petclinic.service

import com.akhutornoy.petclinic.domain.ui.PetForm

interface PetsService {
    fun addPet(petForm: PetForm)
    fun getAllPets(): List<PetForm>
    fun getPetsByHostId(hostId: Long): List<PetForm>
}