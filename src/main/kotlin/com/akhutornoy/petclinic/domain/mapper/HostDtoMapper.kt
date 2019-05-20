package com.akhutornoy.petclinic.domain.mapper

import com.akhutornoy.petclinic.domain.db.HostEntity
import com.akhutornoy.petclinic.domain.rest.HostDto

class HostDtoMapper {

    fun map(source: HostEntity): HostDto {
        if (source.id == null)
            throw IllegalArgumentException("Id must NOT be null")

        return HostDto(source.id!!, source.firstName, source.lastName)
    }

}