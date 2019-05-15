package com.akhutornoy.petclinic.service

import com.akhutornoy.petclinic.domain.db.HostEntity
import com.akhutornoy.petclinic.domain.mapper.HostEntityMapper
import com.akhutornoy.petclinic.domain.mapper.HostFormMapper
import com.akhutornoy.petclinic.repository.DbHost
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class HostsServiceImplTest {

    val dbUser = Mockito.mock(DbHost::class.java)

    val namesService = HostsServiceImpl(dbUser, HostFormMapper(), HostEntityMapper())

    @Before
    fun setUp() {
        val users = listOf(
                HostEntity("firstName_1", "lastName_1", 1),
                HostEntity("firstName_2", "lastName_2", 2)
        )
        Mockito.`when`(dbUser.findAll()).thenReturn(users)
    }

    @Test
    fun getUserById() {

        assertEquals(2, dbUser.findAll().size)
    }

}