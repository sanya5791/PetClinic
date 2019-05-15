package com.akhutornoy.petclinic.controller

import com.akhutornoy.petclinic.service.HostsServiceImpl
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class HostsController(
        private val hostsService: HostsServiceImpl
) {

    @GetMapping(path = [END_POINT_ROOT, END_POINT_INDEX, END_POINT])
    fun getHosts(model: Model): String {
        val hosts = hostsService.getAll()
        model.addAttribute("hosts", hosts)

        return "hosts"
    }

    companion object {
        const val END_POINT = "/hosts"
        const val END_POINT_ROOT = "/"
        const val END_POINT_INDEX = "/index.html"
    }

}