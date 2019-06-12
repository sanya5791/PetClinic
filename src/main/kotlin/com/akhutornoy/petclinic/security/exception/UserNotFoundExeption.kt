package com.akhutornoy.petclinic.security.exception

import java.lang.RuntimeException

class UserNotFoundException(user: String) : RuntimeException("User '$user' NOT found")
