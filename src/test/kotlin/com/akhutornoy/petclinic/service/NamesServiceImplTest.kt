package com.akhutornoy.petclinic.service

import com.akhutornoy.petclinic.domain.db.UserEntity
import com.akhutornoy.petclinic.domain.mapper.UserEntityMapper
import com.akhutornoy.petclinic.domain.mapper.UserFormMapper
import com.akhutornoy.petclinic.repository.DbUser
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class NamesServiceImplTest {

    val dbUser = Mockito.mock(DbUser::class.java)

    val namesService = NamesServiceImpl(dbUser, UserFormMapper(), UserEntityMapper())

    @Before
    fun setUp() {
        val users = listOf(
                UserEntity("firstName_1", "lastName_1", 1),
                UserEntity("firstName_2", "lastName_2", 2)
        )
        Mockito.`when`(dbUser.findAll()).thenReturn(users)
    }

    @Test
    fun getUserById() {

        assertEquals(2, dbUser.findAll().size)
    }

}