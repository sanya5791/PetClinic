package com.akhutornoy.petclinic.service

import com.akhutornoy.petclinic.domain.mapper.UserEntityMapper
import com.akhutornoy.petclinic.domain.mapper.UserFormMapper
import com.akhutornoy.petclinic.domain.ui.UserForm
import com.akhutornoy.petclinic.repository.DbDao
import org.springframework.stereotype.Service

@Service
class NamesServiceImpl(
        private val db: DbDao,
        private val userFormMapper: UserFormMapper,
        private val userEntityMapper: UserEntityMapper)
: NamesService {

    override fun addName(userForm: UserForm) {
        db.putUser(userEntityMapper.map(userForm))
    }

    override fun getAllUsers(): List<UserForm> {
        return db.getUsers().map(userFormMapper::map)
    }

}