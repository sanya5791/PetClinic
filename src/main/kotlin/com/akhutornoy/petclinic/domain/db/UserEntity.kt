package com.akhutornoy.petclinic.domain.db

import javax.persistence.*

@Entity
class UserEntity(
        @Column(name = "first_name")
        var firstName: String,

        @Column(name = "last_name")
        var lastName: String,

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null
)