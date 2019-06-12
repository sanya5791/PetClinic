package com.akhutornoy.petclinic.controller

import com.akhutornoy.petclinic.repository.DbHost
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping

@Controller
class LoginController (
        private val dbHost: DbHost
) {

    @GetMapping(path = [END_POINT, END_POINT_ROOT])
    fun showLogin(model: Model): String {
        return "login"
    }

    companion object {
        const val END_POINT = "/login"
        const val END_POINT_ROOT = "/"
    }

}