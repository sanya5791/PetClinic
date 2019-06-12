package com.akhutornoy.petclinic.security.exception

import java.lang.RuntimeException

class UserAlreadyExistsException(user: String) : RuntimeException("User '$user' already exists")