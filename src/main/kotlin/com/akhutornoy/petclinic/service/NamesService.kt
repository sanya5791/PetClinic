package com.akhutornoy.petclinic.service

import com.akhutornoy.petclinic.domain.ui.UserForm

interface NamesService {
    fun addName(userForm: UserForm)
    fun getAllUsers(): List<UserForm>
}