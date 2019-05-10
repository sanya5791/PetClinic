package com.akhutornoy.petclinic.domain.db

inline class FirstName(val value: String)
inline class LastName(val value: String)


data class UserEntity(val firstName: FirstName, val lastName: LastName)