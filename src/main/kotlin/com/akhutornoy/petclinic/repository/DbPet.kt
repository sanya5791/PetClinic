package com.akhutornoy.petclinic.repository

import com.akhutornoy.petclinic.domain.db.PetEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DbPet : JpaRepository<PetEntity, Long> {
    fun findByHostId(hostId: Long): List<PetEntity>
}