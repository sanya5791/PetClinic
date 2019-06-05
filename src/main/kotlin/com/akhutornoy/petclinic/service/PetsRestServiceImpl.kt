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

    override fun addPet(petDto: PetDto): PetDto {
        val pet = petEntityMapper.map(petDto)
        val petEntity = dbPet.save(pet)
        return petDtoMapper.map(petEntity)
    }

    override fun getPetsByHostId(hostId: Long): List<PetDto> {
        return dbPet.findByHostId(hostId)
                .map(petDtoMapper::map)
    }

    override fun delete(petId: Long) {
        dbPet.deleteById(petId)
    }

}