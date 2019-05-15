package com.akhutornoy.petclinic.service

import com.akhutornoy.petclinic.domain.mapper.HostEntityMapper
import com.akhutornoy.petclinic.domain.mapper.HostFormMapper
import com.akhutornoy.petclinic.domain.ui.HostForm
import com.akhutornoy.petclinic.repository.DbHost
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class HostsServiceImpl(
        private val db: DbHost,
        private val hostFormMapper: HostFormMapper,
        private val hostEntityMapper: HostEntityMapper)
: HostsService {
    override fun add(hostForm: HostForm) {
        db.save(hostEntityMapper.map(hostForm))
    }

    override fun getAll(): List<HostForm> {
        return db.findAll().map(hostFormMapper::map)
    }

    override fun getById(userId: Long): HostForm? {
        return  db.findByIdOrNull(userId)?.let (hostFormMapper::map)
    }

}