package com.akhutornoy.petclinic.domain.mapper

import com.akhutornoy.petclinic.domain.db.HostEntity
import com.akhutornoy.petclinic.domain.ui.HostForm

class HostEntityMapper {
    fun map(source: HostForm) =
            HostEntity(source.firstName, source.lastName)
}