package com.akhutornoy.petclinic.domain.ui

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class HostForm(
        @NotBlank(message = "First Name Must Not be Blank")
        @Size(min = 5, max = 5, message = "too short 'First Name'")
        val firstName: String = "",

        @NotBlank(message = "Last Name Must Not be Blank")
        @Size(min = 5, max = 5, message = "too short 'Last Name'")
        val lastName: String = "",

        val login: String = "",
        val password: String = "",
        val passwordConfirm: String = "",
        val role: String = "",

        val id: Long? = null
)