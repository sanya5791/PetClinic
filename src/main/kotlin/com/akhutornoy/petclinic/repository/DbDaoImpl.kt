package com.akhutornoy.petclinic.repository

import com.akhutornoy.petclinic.domain.db.UserEntity
import org.springframework.stereotype.Repository

@Repository
class DbDaoImpl : DbDao {
    val dbMock = mutableListOf<UserEntity>()

    override fun getUsers(): List<UserEntity> {
        return dbMock
    }

    override fun putUser(vararg userEntity: UserEntity) {
        dbMock.addAll(userEntity)
    }

}