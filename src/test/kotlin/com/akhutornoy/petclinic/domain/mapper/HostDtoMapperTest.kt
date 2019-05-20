package com.akhutornoy.petclinic.domain.mapper

import com.akhutornoy.petclinic.domain.db.HostEntity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class HostDtoMapperTest {
    val mapper = HostDtoMapper()
    @Before
    fun setUp() {
    }

    @Test(expected = IllegalArgumentException::class)
    fun `fail on Entity Id is null`() {
        val entityStub = HostEntity("", "")
        mapper.map(entityStub)
    }

    @Test
    fun `successful Entity mapping`() {
        val expectedFirstName = "firstName"
        val expectedLastName = "lastName"
        val expectedId = 111L
        val entityStub = HostEntity(expectedFirstName, expectedLastName, expectedId)

        val mapped = mapper.map(entityStub)

        assertEquals(expectedFirstName, mapped.firstName)
        assertEquals(expectedLastName, mapped.lastName)
        assertEquals(expectedId, mapped.id)
    }

}