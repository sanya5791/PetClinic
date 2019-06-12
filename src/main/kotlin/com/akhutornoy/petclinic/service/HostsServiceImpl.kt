package com.akhutornoy.petclinic.service

import com.akhutornoy.petclinic.domain.mapper.HostEntityMapper
import com.akhutornoy.petclinic.domain.mapper.HostFormMapper
import com.akhutornoy.petclinic.domain.ui.HostForm
import com.akhutornoy.petclinic.repository.DbHost
import com.akhutornoy.petclinic.repository.DbPet
import com.akhutornoy.petclinic.repository.DbUser
import com.akhutornoy.petclinic.security.exception.UserAlreadyExistsException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class HostsServiceImpl(
        private val dbHost: DbHost,
        private val dbUser: DbUser,
        private val dbPet: DbPet,
        private val passwordEncoder: PasswordEncoder,
        private val hostFormMapper: HostFormMapper,
        private val hostEntityMapper: HostEntityMapper)
: HostsService {

    override fun add(hostForm: HostForm): HostForm {
        if (dbUser.findById(hostForm.login).isPresent) {
            throw UserAlreadyExistsException(hostForm.login)
        }

        val hostFormEncrypted = hostForm.copy(password = passwordEncoder.encode(hostForm.password))
        val hostEntity = dbHost.save(hostEntityMapper.map(hostFormEncrypted))
        return hostFormMapper.map(hostEntity)
    }

    override fun delete(hostId: Long) {
        dbPet.deleteByHostId(hostId)
        dbHost.deleteById(hostId)
    }

    override fun getAll(): List<HostForm> {
        return dbHost.findAll().map(hostFormMapper::map)
    }

    override fun getById(userId: Long): HostForm? {
        return  dbHost.findByIdOrNull(userId)?.let (hostFormMapper::map)
    }

    override fun getByUserName(userName: String): HostForm? {
        return dbHost.findHostEntityByUserAuth_Name(userName)?.let (hostFormMapper::map)
    }

}