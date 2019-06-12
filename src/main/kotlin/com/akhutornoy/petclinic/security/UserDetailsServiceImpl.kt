package com.akhutornoy.petclinic.security

import com.akhutornoy.petclinic.repository.DbUser
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
        private val dbUser: DbUser
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val userEntity = dbUser.findById(username)
                .orElseGet { throw UsernameNotFoundException("User '$username' NOT found") }

        return UserDetailsDto(userEntity)
    }

}