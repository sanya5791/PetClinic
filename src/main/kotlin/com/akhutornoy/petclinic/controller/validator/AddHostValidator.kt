package com.akhutornoy.petclinic.controller.validator

import com.akhutornoy.petclinic.domain.ui.HostForm
import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError

class AddHostValidator {

    fun validate(form: HostForm, bindingResult: BindingResult): Boolean {
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

}