package com.akhutornoy.petclinic.service

import com.akhutornoy.petclinic.domain.db.Role
import com.akhutornoy.petclinic.domain.db.UserEntity
import com.akhutornoy.petclinic.repository.DbHost
import com.akhutornoy.petclinic.repository.DbUser
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class HostsServiceImplTest {

    val dbUser = Mockito.mock(DbUser::class.java)
    val dbHost = Mockito.mock(DbHost::class.java)

    @Before
    fun setUp() {
        val users = mutableListOf(
                UserEntity("firstName_1", "lastName_1", Role.ADMIN),
                UserEntity("firstName_2", "lastName_2", Role.USER)
        )
        Mockito.`when`(dbUser.findAll()).thenReturn(users)
    }

    @Test
    fun getUserById() {

        assertEquals(2, dbUser.findAll().size)
    }

}