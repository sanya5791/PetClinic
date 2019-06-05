package com.akhutornoy.petclinic.service

import com.akhutornoy.petclinic.domain.mapper.HostDtoMapper
import com.akhutornoy.petclinic.domain.mapper.HostEntityMapper
import com.akhutornoy.petclinic.domain.rest.HostDto
import com.akhutornoy.petclinic.repository.DbHost
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class HostsRestServiceImpl(
        private val db: DbHost,
        private val hostDtoMapper: HostDtoMapper,
        private val hostEntityMapper: HostEntityMapper)
: HostsRestService {

    override fun getAll(): List<HostDto> {
        return db.findAll().map(hostDtoMapper::map)
    }

    override fun getById(userId: Long): HostDto? {
        return  db.findByIdOrNull(userId)?.let (hostDtoMapper::map)
    }

    override fun add(host: HostDto): HostDto {
        val hostEntity = db.save(hostEntityMapper.map(host))
        return hostDtoMapper.map(hostEntity)
    }

    override fun delete(hostId: Long) {
        db.deleteById(hostId)
    }

}