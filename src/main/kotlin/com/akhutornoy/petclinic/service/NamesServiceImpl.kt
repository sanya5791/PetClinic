package com.akhutornoy.petclinic.service

import com.akhutornoy.petclinic.domain.mapper.UserEntityMapper
import com.akhutornoy.petclinic.domain.mapper.UserFormMapper
import com.akhutornoy.petclinic.domain.ui.UserForm
import com.akhutornoy.petclinic.repository.DbUser
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class NamesServiceImpl(
        private val db: DbUser,
        private val userFormMapper: UserFormMapper,
        private val userEntityMapper: UserEntityMapper)
: NamesService {
    override fun addName(userForm: UserForm) {
        db.save(userEntityMapper.map(userForm))
    }

    override fun getAllUsers(): List<UserForm> {
        return db.findAll().map(userFormMapper::map)
    }

    override fun getUserById(userId: Long): UserForm? {
        return  db.findByIdOrNull(userId)?.let (userFormMapper::map)
    }

}