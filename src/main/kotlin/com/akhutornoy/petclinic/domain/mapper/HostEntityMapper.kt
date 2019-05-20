package com.akhutornoy.petclinic.domain.mapper

import com.akhutornoy.petclinic.domain.db.HostEntity
import com.akhutornoy.petclinic.domain.rest.HostDto
import com.akhutornoy.petclinic.domain.ui.HostForm

class HostEntityMapper {
    fun map(source: HostForm) =
            HostEntity(source.firstName, source.lastName)

    fun map(source: HostDto) =
            HostEntity(source.firstName, source.lastName)

}