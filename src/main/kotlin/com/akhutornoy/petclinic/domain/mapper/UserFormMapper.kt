package com.akhutornoy.petclinic.domain.mapper

import com.akhutornoy.petclinic.domain.db.UserEntity
import com.akhutornoy.petclinic.domain.ui.UserForm

class UserFormMapper {
    fun map(source: UserEntity) =
            UserForm(firstName = source.firstName, lastName = source.lastName, id = source.id)
}