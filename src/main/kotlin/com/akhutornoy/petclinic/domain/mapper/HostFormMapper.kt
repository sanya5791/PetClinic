package com.akhutornoy.petclinic.domain.mapper

import com.akhutornoy.petclinic.domain.db.HostEntity
import com.akhutornoy.petclinic.domain.ui.HostForm

class HostFormMapper {
    fun map(source: HostEntity) =
            HostForm(firstName = source.firstName, lastName = source.lastName, id = source.id)
}