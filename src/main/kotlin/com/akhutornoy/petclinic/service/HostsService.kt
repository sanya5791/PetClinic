package com.akhutornoy.petclinic.service

import com.akhutornoy.petclinic.domain.ui.HostForm

interface HostsService {
    fun add(hostForm: HostForm)
    fun getAll(): List<HostForm>
    fun getById(userId: Long): HostForm?
}