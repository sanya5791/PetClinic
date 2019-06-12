package com.akhutornoy.petclinic.service

import com.akhutornoy.petclinic.domain.ui.HostForm

interface HostsService {
    fun add(hostForm: HostForm): HostForm
    fun getAll(): List<HostForm>
    fun getById(userId: Long): HostForm?
    fun getByUserName(userName: String): HostForm?
    fun delete(hostId: Long)
}