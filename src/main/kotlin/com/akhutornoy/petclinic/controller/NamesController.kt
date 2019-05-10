package com.akhutornoy.petclinic.controller

import com.akhutornoy.petclinic.service.NamesServiceImpl
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

inline class Name(val value: String)


@Controller
class NamesController(
        private val namesService: NamesServiceImpl
) {

    @GetMapping(path = [END_POINT_ROOT, END_POINT_INDEX, END_POINT])
    fun getNames(model: Model): String {
        val names = namesService.getAllUsers()
        model.addAttribute("names", names)

        return "names"
    }

    companion object {
        const val END_POINT = "/names"
        const val END_POINT_ROOT = "/"
        const val END_POINT_INDEX = "/index.html"
    }

}