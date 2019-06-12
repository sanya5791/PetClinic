package com.akhutornoy.petclinic.domain.mapper

import com.akhutornoy.petclinic.domain.db.HostEntity
import com.akhutornoy.petclinic.domain.db.UserEntity
import com.akhutornoy.petclinic.domain.rest.HostDto

class HostDtoMapper {

    fun map(source: HostEntity): HostDto {
        if (source.id == null)
            throw IllegalArgumentException("Id must NOT be null")

        return HostDto(source.id!!, source.firstName, source.lastName, map(source.userAuth))
    }

    private fun map(source: UserEntity) =
            com.akhutornoy.petclinic.domain.rest.UserDto(
                    source.name, source.password, source.role.name)

}