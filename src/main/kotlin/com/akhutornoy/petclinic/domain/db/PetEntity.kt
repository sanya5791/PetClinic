package com.akhutornoy.petclinic.domain.db

import javax.persistence.*

@Entity
class PetEntity (
        @Column(name = "name")
        var name: String,

        @Column(name = "breed")
        var breed: String,

        @Column(name = "host_id")
        var hostId: Long,

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null
)