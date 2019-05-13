package com.akhutornoy.petclinic.repository

import com.akhutornoy.petclinic.domain.db.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DbUser : JpaRepository<UserEntity, Long>