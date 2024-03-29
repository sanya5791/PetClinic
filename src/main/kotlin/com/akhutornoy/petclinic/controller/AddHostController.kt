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
class AddHostController(
        private val hostsService: HostsService,
        private val addHostValidator: AddHostValidator
) {

    @GetMapping(path = [ADD_HOST])
    fun showEmptyForm(model: Model): String {
        model.addAttribute(FORM_HOST, HostForm(role = Role.USER.name))
        return "add_host"
    }

    @PostMapping(path = [ADD_HOST])
    fun addHost(@Valid @ModelAttribute(FORM_HOST) form: HostForm,
                bindingResult: BindingResult): String {

        if (addHostValidator.validate(form, bindingResult)) {
            return "add_host"
        }

        var formSaved: HostForm? = null
        try {
            formSaved = hostsService.add(form)
        } catch (ex: Exception) {
            ex.printStackTrace()
            bindingResult.addError(ObjectError("LoginAlreadyExistsError", ex.message ?: "Something went wrong"))
            return "add_host"
        }

        val role = Role.valueOf(formSaved.role)
        return when (role) {
            Role.USER -> "redirect:${PetsController.END_POINT}?${PetsController.PARAM_HOST_ID}=${formSaved.id}"
            Role.ADMIN -> "redirect:${HostsController.END_POINT}"
        }
    }

    @GetMapping(path = [DELETE_HOST])
    fun deleteHost(model: Model,
                   @RequestParam(value = PARAM_HOST_ID) hostId: Long): String {
        hostsService.delete(hostId)
        return "redirect:${HostsController.END_POINT}"
    }

    @ExceptionHandler(Throwable::class)
    fun handleError(error: Throwable, response: HttpServletResponse): String {
        error.printStackTrace()
        return "hosts"
    }

    companion object {
        const val PARAM_HOST_ID = "host_id"
        const val ADD_HOST = "/addhost"
        const val DELETE_HOST = "/deletehost"
        const val FORM_HOST = "hostForm"
    }

}