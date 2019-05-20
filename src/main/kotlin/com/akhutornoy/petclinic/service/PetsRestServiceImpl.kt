package com.akhutornoy.petclinic.service

import com.akhutornoy.petclinic.domain.mapper.PetDtoMapper
import com.akhutornoy.petclinic.domain.mapper.PetEntityMapper
import com.akhutornoy.petclinic.domain.rest.PetDto
import com.akhutornoy.petclinic.repository.DbPet
import org.springframework.stereotype.Service

@Service
class PetsRestServiceImpl(
        private val dbPet: DbPet,
        private val petEntityMapper: PetEntityMapper,
        private val petDtoMapper: PetDtoMapper
) : PetsRestService {

    override fun addPet(name: String, breed: String, hostId: Long) {
        val pet = petEntityMapper.map(name, breed, hostId)
        dbPet.save(pet)
    }

    override fun getPetsByHostId(hostId: Long): List<PetDto> {
        return dbPet.findByHostId(hostId)
                .map(petDtoMapper::map)
    }

}