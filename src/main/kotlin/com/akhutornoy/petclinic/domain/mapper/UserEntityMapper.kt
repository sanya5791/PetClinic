package com.akhutornoy.petclinic.domain.mapper

import com.akhutornoy.petclinic.domain.db.Role
import com.akhutornoy.petclinic.domain.db.UserEntity
import com.akhutornoy.petclinic.domain.rest.UserDto
import com.akhutornoy.petclinic.domain.ui.HostForm

class UserEntityMapper {
    fun map(source: HostForm) =
            UserEntity(source.login, source.password, Role.valueOf(source.role))

    fun map(source: UserDto) = UserEntity(
            source.userLogin, source.userPassword, Role.valueOf(source.userRole))

}