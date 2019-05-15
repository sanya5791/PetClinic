package com.akhutornoy.petclinic.repository

import com.akhutornoy.petclinic.domain.db.HostEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DbHost : JpaRepository<HostEntity, Long>