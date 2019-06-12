package com.akhutornoy.petclinic.controller

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
        private val hostsService: HostsService
) {

    @GetMapping(path = [ADD_HOST, REGISTRATION])
    fun showEmptyForm(model: Model): String {
        model.addAttribute(FORM_HOST, HostForm(role = Role.USER.name))
        return "add_host"
    }

    @PostMapping(path = [ADD_HOST, REGISTRATION])
    fun addHost(@Valid @ModelAttribute(FORM_HOST) form: HostForm,
                bindingResult: BindingResult): String {

        if (handleFormError(form, bindingResult)) {
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

    private fun handleFormError(form: HostForm, bindingResult: BindingResult): Boolean {
        //manual error validation
        if(form.firstName.isBlank()
                || form.lastName.isBlank()
                || form.login.isBlank()
                || form.password.isBlank()
                || form.passwordConfirm.isBlank()
                || form.login.isBlank()
        ) {
            bindingResult.addError(ObjectError("error1", "All fields should be filled"))
            return true
        }

        if (form.password != form.passwordConfirm) {
            bindingResult.addError(ObjectError("error2", "Don't leave empty input fields"))
            return true
        }

//             TODO: fix validation
        if (bindingResult.hasErrors()) {
            return true
        }

        return false
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
        const val REGISTRATION = "/registration"
        const val DELETE_HOST = "/deletehost"
        const val FORM_HOST = "hostForm"
    }

}