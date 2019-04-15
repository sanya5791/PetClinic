package com.akhutornoy.petclinic

import org.springframework.stereotype.Controller
import sun.security.x509.OIDMap.addAttribute
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.GetMapping

@Controller
class GreetingController {

    @GetMapping("/greeting")
    fun greeting(@RequestParam(name = "name", required = false, defaultValue = "World") name: String, model: Model): String {
        model.addAttribute("name", name.toUpperCase())
        return "greeting"
    }
}