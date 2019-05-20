package com.akhutornoy.petclinic.service

import com.akhutornoy.petclinic.domain.rest.HostDto
import com.akhutornoy.petclinic.domain.ui.HostForm

interface HostsRestService {
    fun getAll(): List<HostDto>
    fun getById(userId: Long): HostDto?
    fun add(host: HostDto): HostDto
}