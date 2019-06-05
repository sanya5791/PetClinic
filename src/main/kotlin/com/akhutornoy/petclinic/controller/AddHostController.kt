package com.akhutornoy.petclinic.controller

import com.akhutornoy.petclinic.controller.AddHostController.Companion.END_POINT
import com.akhutornoy.petclinic.domain.ui.HostForm
import com.akhutornoy.petclinic.service.HostsService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@Controller
@RequestMapping(END_POINT)
@Validated
class AddHostController(
        private val hostsService: HostsService
) {

    @GetMapping
    fun showEmptyForm(model: Model): String {
        model.addAttribute(FORM_HOST, HostForm(firstName = ""))
        return "add_host"
    }

    @PostMapping
    fun addHost(@Valid @ModelAttribute(FORM_HOST) form: HostForm,
                bindingResult: BindingResult): String {

//             TODO: fix validation
        if (bindingResult.hasErrors()) {
            return "add_host"
        }

        hostsService.add(form)
        return "redirect:" + HostsController.END_POINT
    }

    @ExceptionHandler(Throwable::class)
    fun handleError(error: Throwable, response: HttpServletResponse): String {
        error.printStackTrace()
        return "hosts"
    }

    companion object {
        const val END_POINT = "/addhost"
        const val DELETE_HOST = "/deletehost"
        const val FORM_HOST = "hostForm"
    }

}