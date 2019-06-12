package com.akhutornoy.petclinic.controller

import com.akhutornoy.petclinic.controller.validator.AddHostValidator
import com.akhutornoy.petclinic.domain.db.Role
import com.akhutornoy.petclinic.domain.ui.HostForm
import com.akhutornoy.petclinic.service.HostsService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@Controller
@Validated
class RegistrationController(
        private val hostsService: HostsService,
        private val addHostValidator: AddHostValidator
) {

    @GetMapping(path = [REGISTRATION])
    fun showEmptyForm(model: Model): String {
        model.addAttribute(FORM_HOST, HostForm(role = Role.USER.name))
        return "registration"
    }

    @PostMapping(path = [REGISTRATION])
    fun addHost(@Valid @ModelAttribute(FORM_HOST) form: HostForm,
                bindingResult: BindingResult): String {

        if (addHostValidator.validate(form, bindingResult)) {
            return "registration"
        }

        try {
            hostsService.add(form)
        } catch (ex: Exception) {
            ex.printStackTrace()
            bindingResult.addError(ObjectError("LoginAlreadyExistsError", ex.message ?: "Something went wrong"))
            return "registration"
        }

        return "redirect:${LoginController.END_POINT}"
    }

    @ExceptionHandler(Throwable::class)
    fun handleError(error: Throwable, response: HttpServletResponse): String {
        error.printStackTrace()
        return "hosts"
    }

    companion object {
        const val REGISTRATION = "/registration"

        const val FORM_HOST = "hostForm"
    }

}