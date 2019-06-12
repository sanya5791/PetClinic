package com.akhutornoy.petclinic.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class AboutController {

    @GetMapping(END_POINT)
    fun showAbout(): String {
        return "about"
    }

    companion object {
        const val END_POINT = "/about"
    }

}