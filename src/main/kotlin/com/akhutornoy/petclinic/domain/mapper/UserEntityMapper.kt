package com.akhutornoy.petclinic.domain.mapper

import com.akhutornoy.petclinic.domain.db.FirstName
import com.akhutornoy.petclinic.domain.db.LastName
import com.akhutornoy.petclinic.domain.db.UserEntity
import com.akhutornoy.petclinic.domain.ui.UserForm

class UserEntityMapper {
    fun map(source: UserForm) =
            UserEntity(FirstName(source.firstName), LastName(source.lastName))
}