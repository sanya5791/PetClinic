package com.akhutornoy.petclinic.repository

import com.akhutornoy.petclinic.domain.db.UserEntity

interface DbDao {
    fun getUsers(): List<UserEntity>
    fun putUser(vararg userEntity: UserEntity)
}