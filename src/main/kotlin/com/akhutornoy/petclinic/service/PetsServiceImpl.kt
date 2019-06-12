package com.akhutornoy.petclinic.service

import com.akhutornoy.petclinic.domain.mapper.PetEntityMapper
import com.akhutornoy.petclinic.domain.mapper.PetFormMapper
import com.akhutornoy.petclinic.domain.ui.PetForm
import com.akhutornoy.petclinic.repository.DbPet
import org.springframework.stereotype.Service

@Service
class PetsServiceImpl(
        private val db: DbPet,
        private val petFormMapper: PetFormMapper,
        private val petEntityMapper: PetEntityMapper
) : PetsService {

    override fun addPet(petForm: PetForm) {
        val petEntity = petEntityMapper.map(petForm)
        db.save(petEntity)
    }

    override fun deletePet(petId: Long) {
        db.deleteById(petId)
    }

    override fun getAllPets(): List<PetForm> {
        return db.findAll()
                .map(petFormMapper::map)
    }

    override fun getPetsByHostId(hostId: Long): List<PetForm> {
        return db.findByHostId(hostId)
                .map(petFormMapper::map)
    }

}