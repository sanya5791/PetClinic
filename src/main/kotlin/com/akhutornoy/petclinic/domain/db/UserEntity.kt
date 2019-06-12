package com.akhutornoy.petclinic.domain.db

import javax.persistence.*

@Entity
class UserEntity(
        @Id
        @Column(name = "name", unique = true)
        val name: String,

        @Column(name = "password")
        val password: String,

        @Enumerated(EnumType.STRING)
        @Column(name = "role")
        val role: Role)

enum class Role {
    ADMIN, USER;

    fun withPrefixROLE() = "ROLE_$name"

}