package com.akhutornoy.petclinic.controller

import com.akhutornoy.petclinic.controller.AddNameController.Companion.END_POINT
import com.akhutornoy.petclinic.domain.ui.UserForm
import com.akhutornoy.petclinic.service.NamesServiceImpl
import org.springframework.beans.factory.annotation.Autowired
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
class AddNameController(
        private val namesService: NamesServiceImpl
) {

    @GetMapping
    fun showEmptyForm(model: Model): String {
        model.addAttribute(FORM_NAME, UserForm(firstName = ""))
        return "add_name"
    }

    @PostMapping
    fun addName(@Valid @ModelAttribute(FORM_NAME) form: UserForm,
                bindingResult: BindingResult): String {

//             TODO: fix validation
        if (bindingResult.hasErrors()) {
            return "add_name"
        }

        namesService.addName(form)
        return "redirect:" + NamesController.END_POINT
    }

    @ExceptionHandler(Throwable::class)
    fun handleError(error: Throwable, response: HttpServletResponse): String {
        error.printStackTrace()
        return "names"
    }

    companion object {
        const val END_POINT = "/addname"
        const val FORM_NAME = "userForm"
    }

}