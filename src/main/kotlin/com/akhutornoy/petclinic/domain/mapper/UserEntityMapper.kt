package com.akhutornoy.petclinic.domain.mapper

import com.akhutornoy.petclinic.domain.db.UserEntity
import com.akhutornoy.petclinic.domain.ui.UserForm

class UserEntityMapper {
    fun map(source: UserForm) =
            UserEntity(source.firstName, source.lastName)
}